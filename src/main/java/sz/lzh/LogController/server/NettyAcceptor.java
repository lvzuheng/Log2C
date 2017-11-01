package sz.lzh.LogController.server;



import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.AdaptiveRecvByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.ServerChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 
 * 
 * */

public class NettyAcceptor {

	private ServerBootstrap     bootstrap = null; 
	private EventLoopGroup      bossGroup ;
	private EventLoopGroup      workerGroup;
	

	/** 自定义参数 **/
	private int					port			= 9123;							// 定义监听端口
	public int					receiveBuffer	= -1;
	public int					sendBuffer		= -1;
	private ChannelHandler	    handler;
	
	
	public NettyAcceptor() {
		// TODO Auto-generated constructor stub
	}
	
	public NettyAcceptor create() {
	  bossGroup = new NioEventLoopGroup();
   	  workerGroup = new NioEventLoopGroup();
   	  bootstrap = new ServerBootstrap();
   	  bootstrap.option(ChannelOption.RCVBUF_ALLOCATOR,new AdaptiveRecvByteBufAllocator(64,64*1024,100*1024));
   	  bootstrap.group(bossGroup,workerGroup);
   	  bootstrap.option(ChannelOption.TCP_NODELAY, true);//降低延迟
   	  bootstrap.channel(NioServerSocketChannel.class);//设置服务端通道
   	  return this;
	}
	
	public NettyAcceptor create(Class<? extends ServerChannel> channelClass) {
		bossGroup = new NioEventLoopGroup();
		workerGroup = new NioEventLoopGroup();
		bootstrap = new ServerBootstrap();
		bootstrap.option(ChannelOption.RCVBUF_ALLOCATOR,new AdaptiveRecvByteBufAllocator(64,64*1024,100*1024));
		bootstrap.group(bossGroup,workerGroup);
		bootstrap.option(ChannelOption.TCP_NODELAY, true);//降低延迟
		bootstrap.channel(channelClass);//设置服务端通道
		return this;
	}
	
	/**开启接口*/
	public void open() {
	      if(Situation()){
		      ChannelFuture future;
			try {
				future = bootstrap.bind(port).sync();
				if (future.isSuccess()){
					System.out.println("端口【"+port+"】开始监听");
				} 
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("accepter:连接不正常关闭，无法打开端口【"+port+"】,原因："+e.getMessage());
			}
	      }
	}
	
	/**判断条件是否足够*/
	public boolean Situation() {
		if(port!=0 && handler !=null )
			return true;
		
		return false;
	}
	
	public int getPort() {
		return port;
	}
	public NettyAcceptor setPort(int port) {
		this.port = port;
		return this;
	}
	public ChannelHandler getHandler() {
		return handler;
	}
	public NettyAcceptor setHandler(ChannelHandler handler) {
		this.handler = handler;
		bootstrap.childHandler(handler);
		return this;
	}
}
