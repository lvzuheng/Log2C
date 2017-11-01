package sz.lzh.LogController.server;


import io.netty.bootstrap.Bootstrap;
import io.netty.channel.AdaptiveRecvByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.FixedRecvByteBufAllocator;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

public class NettyConnector {
	private int port = -1;
	private String address = null;
	private NettyHandler handler = null;
	private Bootstrap bootstrap ;
	private boolean AUTO= false;
	private Channel channel;
	
	public NettyConnector() {
		// TODO Auto-generated constructor stub
	}
	
	public NettyConnector create() {
		bootstrap = new Bootstrap();
		EventLoopGroup eventLoopGroup= new NioEventLoopGroup();
		bootstrap.group(eventLoopGroup);
		bootstrap.channel( NioSocketChannel.class);
		bootstrap.option(ChannelOption.TCP_NODELAY, true);//降低延迟
//		bootstrap.option(ChannelOption.RCVBUF_ALLOCATOR,new AdaptiveRecvByteBufAllocator(64,10*1024,65536));
		bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
		return this;
	}
	public NettyConnector create(Class<? extends Channel> channelClass) {
		bootstrap = new Bootstrap();
		EventLoopGroup eventLoopGroup= new NioEventLoopGroup();
		bootstrap.group(eventLoopGroup);
		bootstrap.channel(channelClass);
		bootstrap.option(ChannelOption.TCP_NODELAY, true);//降低延迟
//		bootstrap.option(ChannelOption.RCVBUF_ALLOCATOR,new AdaptiveRecvByteBufAllocator(64,10*1024,65536));
		bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
		return this;
	}
	
	public NettyConnector connect() {
		if(Situation()){
			try {
				this.channel = bootstrap.connect(address, port).channel();
				System.out.println(channel.isActive());
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(AUTO){
				AutoConnectManager.getInstance().offer(this);
				if(!AutoConnectManager.getInstance().isAlive())
					AutoConnectManager.getInstance().start();
			}
		}
		return this;
	}
	
	public void reConnect(){
		if(AUTO && Situation()){
			connect();
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
			}
		}
	}
	
	private void disconnect() {
		// TODO Auto-generated method stub
		getChannel().close();
	}

	public boolean Situation() {
		if(port!=-1 && address!=null && handler !=null )
			return true;
		return false;
	}

	public int getPort() {
		return port;
	}


	public NettyConnector setPort(int port) {
		this.port = port;
		return this;
	}


	public String getAddress() {
		return address;
	}


	public NettyConnector setAddress(String address) {
		this.address = address;
		return this;
	}


	public ChannelHandler getHandler() {
		return handler;
	}


	public NettyConnector setHandler(NettyHandler handler) {
		this.handler = handler;
		bootstrap.handler(handler);
		return this;
	}

	public boolean isAUTO() {
		return AUTO;
	}

	public NettyConnector setAUTO(boolean AUTO) {
		this.AUTO = AUTO;

		return this;
	}
	
	public boolean isConnected() {
		return getChannel().isActive();
	}

	public Channel getChannel() {
		return channel;
	}

	
}
