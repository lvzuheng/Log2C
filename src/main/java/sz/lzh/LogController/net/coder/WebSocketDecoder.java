package sz.lzh.LogController.net.coder;

import java.lang.reflect.Method;
import java.util.List;

import com.alibaba.fastjson.JSON;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PingWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PongWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;
import io.netty.util.CharsetUtil;
import sz.lzh.LogController.Action.InfosControllerAction;
import sz.lzh.LogController.Action.InfosLoginAction;
import sz.lzh.LogController.bean.Infos;
import sz.lzh.LogController.config.ActionConfig;

public class WebSocketDecoder extends ByteToMessageDecoder{
	private WebSocketServerHandshaker handshaker;  
	
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf buf, List<Object> arg2) throws Exception {
		// TODO Auto-generated method stub
	}    
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object buf) throws Exception {
		// TODO Auto-generated method stub
		
		if(buf instanceof HttpRequest){  
			excute(ctx.channel(), ((HttpRequest) buf));  
		}else if(buf instanceof WebSocketFrame){  
			excute(ctx.channel(), (WebSocketFrame) buf); 
		}
		
	}
	public void excute(Channel channel,WebSocketFrame frame){
		if(frame instanceof CloseWebSocketFrame){  
			handshaker.close(channel, (CloseWebSocketFrame)frame.retain());  
		}  
		if(frame instanceof PingWebSocketFrame){  
			channel.write(new PongWebSocketFrame(frame.content().retain()));  
		}  
		
		if(!(frame instanceof TextWebSocketFrame)){  
//			throw new UnsupportedOperationException(String.format("%s frame types not supported", frame.getClass().getName()));  
			System.err.println("检测到连接异常关闭");
		}  

		String request = ((TextWebSocketFrame)frame).text(); 
		excute(channel,request);
	}

	public void excute(Channel channel,HttpRequest req){
		try {
			if(!req.decoderResult().isSuccess() ||  !("websocket".equals(req.headers().get("Upgrade")))){  
				sendHttpResponse(channel, req, new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));  
				return;  
			}  
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory("ws://localhost:7890/websocket", null, false);  
		handshaker = wsFactory.newHandshaker(req);  
		if(null == handshaker){  
			WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(channel);  
		}else{  
			handshaker.handshake(channel, req);  
		}  

	}
	private void excute(Channel channel,String msg){
		
		int pid = JSON.parseObject(msg).getInteger("pid");
		switch (pid) {
		case ActionConfig.LOGIN_PID:
				InfosLoginAction.excute(msg,channel);
			break;
		case ActionConfig.CONTROLLER_PID:
			System.out.println(msg);
				new InfosControllerAction().excute(msg, channel);
			break;

		default:
			break;
		}
	}

	private void sendHttpResponse(Channel channel,HttpRequest req, DefaultFullHttpResponse res) {  
		if(res.status().code() != 200){  

			ByteBuf buf = Unpooled.copiedBuffer(res.status().toString(), CharsetUtil.UTF_8);  
			res.content().writeBytes(buf);  
			buf.release();  
		}  
		ChannelFuture future = channel.writeAndFlush(res);  
		if (!isKeepAlive(req) || res.status().code() != 200) {  
			future.addListener(ChannelFutureListener.CLOSE);  
		}  

	}  
	private static boolean isKeepAlive(HttpRequest req){  
		return false;  
	}  
	
     


}
