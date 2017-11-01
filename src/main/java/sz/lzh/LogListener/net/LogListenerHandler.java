package sz.lzh.LogListener.net;

import com.alibaba.fastjson.JSON;import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.bytes.ByteArrayEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import sz.lzh.LogController.Infos.InfosController;
import sz.lzh.LogController.bean.InfosHeart;
import sz.lzh.LogController.bean.LogLogin;
import sz.lzh.LogController.net.coder.LogDecoder;
import sz.lzh.LogController.server.NettyHandler;
import sz.lzh.LogListener.view.frame.MainFrame;

public class LogListenerHandler extends NettyHandler{
	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		super.channelRegistered(ctx);
		ctx.pipeline().addFirst(new IdleStateHandler(TIMEOUT,TIMEOUT,TIMEOUT));
		ctx.pipeline().addFirst(new ByteArrayEncoder());
//		ctx.pipeline().addLast( new LoggingHandler(LogLevel.INFO));
		ctx.pipeline().addLast(new LineBasedFrameDecoder(1000*1024));
		ctx.pipeline().addLast(new LogListenerDecoder());
	}
	
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		super.channelActive(ctx);
//		System.out.println("与["+ctx.channel().remoteAddress()+"]建立连接");
		MainFrame.getInstance().append("与["+ctx.channel().remoteAddress()+"]建立连接");
		LogConnect.getInstance().setChannel(ctx.channel());
		LogConnect.getInstance().login();
	}
	
	@Override
	public void setAllIdleState(ChannelHandlerContext ctx) {
		// TODO Auto-generated method stub
		super.setAllIdleState(ctx);
		ctx.channel().writeAndFlush((JSON.toJSONString(new InfosHeart())+"\r\n").getBytes());
	}
	
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		super.channelInactive(ctx);
	}
}
