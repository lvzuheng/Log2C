package sz.lzh.LogController.server;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

@Sharable
public class NettyHandler extends ChannelDuplexHandler{
	public int TIMEOUT = 20;


	/*接收到消息*/
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		// TODO Auto-generated method stub 
		super.channelRead(ctx, msg);	
	}
	
	/*连接已建立*/
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
	// TODO Auto-generated method stub
		super.channelActive(ctx);
	}
	

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub 
		super.channelInactive(ctx);
		ctx.close();
	}
	
	
	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
	// TODO Auto-generated method stub 
		super.userEventTriggered(ctx, evt);
		if(evt instanceof IdleStateEvent){
			IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
			if (idleStateEvent.state() == IdleState.ALL_IDLE) {
				setAllIdleState(ctx);
			}else if(idleStateEvent.state() == IdleState.READER_IDLE){
				setReadIdleState(ctx);
			}else if(idleStateEvent.state() == IdleState.WRITER_IDLE){
				setWriteIdleState(ctx);
			}
		}
	}
	
	@Override
	public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub	通道解除注册
		super.channelUnregistered(ctx);	
	}

	//通道全空闲
	public void setAllIdleState(ChannelHandlerContext ctx) {}
	//通道读空闲
	public void setReadIdleState(ChannelHandlerContext ctx) {}
	//通道写空闲
	public void setWriteIdleState(ChannelHandlerContext ctx) {}

	public int getTIMEOUT() {
		return TIMEOUT;
	}

	public NettyHandler setTIMEOUT(int tIMEOUT) {
		TIMEOUT = tIMEOUT;
		return this;
	}
}
