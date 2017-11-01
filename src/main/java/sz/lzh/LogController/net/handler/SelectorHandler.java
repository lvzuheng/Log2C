package sz.lzh.LogController.net.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.bytes.ByteArrayEncoder;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;
import sz.lzh.LogController.net.coder.LogDecoder;
import sz.lzh.LogController.net.coder.LogEncoder;
import sz.lzh.LogController.net.coder.WebSocketDecoder;
import sz.lzh.LogController.net.coder.WebSocketEncoder;
import sz.lzh.LogController.server.NettyHandler;
/**
 * 
 * 选择过滤器
 * 通过注册信息选择解码器
 * 选择完解码器以后移除选择过滤器，避免多次选择
 * 
 * */
public class SelectorHandler extends NettyHandler{


	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		// TODO Auto-generated method stub
		ByteBuf buf = (ByteBuf)msg;
		byte[] by = new byte[buf.readableBytes()];
		buf.readBytes(by,0,1);//读取注册信息的第一个字节
		if(new String(by).startsWith("{")){//判断是否是json，是的话进入tcp解码
			ctx.pipeline().addFirst(new ByteArrayEncoder());
			ctx.pipeline().addLast(new LineBasedFrameDecoder(1000*1024));
			ctx.pipeline().addLast("log-encoder",new LogEncoder());
			ctx.pipeline().addLast(new LogDecoder());
		}else{//否的话进入websocket解码
			ctx.pipeline().addLast("http-chunked",new ChunkedWriteHandler());  
			ctx.pipeline().addLast("aggregator",new HttpObjectAggregator(65535));  
			ctx.pipeline().addLast("http-codec", new HttpServerCodec());  
			ctx.pipeline().addLast("webSocket-encoder",new WebSocketEncoder());
			ctx.pipeline().addLast("http-websocket",new WebSocketDecoder());
		}
		buf.resetReaderIndex();//重置bytebuf指针
		ctx.pipeline().remove(this); //移除选择过滤器
		super.channelRead(ctx, msg);//向下传递消息
	}

}
