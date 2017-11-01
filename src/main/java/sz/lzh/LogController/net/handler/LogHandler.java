package sz.lzh.LogController.net.handler;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.bytes.ByteArrayEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import sz.lzh.LogController.Action.InfosLogin;
import sz.lzh.LogController.Infos.InfosController;
import sz.lzh.LogController.Infos.SystemInfos;
import sz.lzh.LogController.server.NettyHandler;

public class LogHandler extends NettyHandler{
	
	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		super.channelRegistered(ctx);
		ctx.pipeline().addFirst(new IdleStateHandler(TIMEOUT,TIMEOUT,TIMEOUT));
//		ctx.pipeline().addLast( new LoggingHandler(LogLevel.INFO));
		ctx.pipeline().addLast(new SelectorHandler());
	}
	
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("与"+ctx.channel().remoteAddress()+" 建立连接");
		super.channelActive(ctx);
	}
	
	@Override
	public void setAllIdleState(ChannelHandlerContext ctx) {
		// TODO Auto-generated method stub
		super.setAllIdleState(ctx);
		ctx.close();
	}
	
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		super.channelInactive(ctx);
		System.out.println("与消息 断开连接");
		InfosController.stopcommit(ctx.channel());
	}

}
