package sz.lzh.LogController.net;

import sz.lzh.LogController.net.handler.LogHandler;
import sz.lzh.LogController.server.NettyAcceptor;

public class LogServer {

	private int port =1100;
	public LogServer(int port) {
		// TODO Auto-generated constructor stub
		this.port = port;
	}
	public LogServer() {
		// TODO Auto-generated constructor stub
	}
	public void bootUp(){
		NettyAcceptor tcpAcceptor = new NettyAcceptor();
		tcpAcceptor.create().setHandler(new LogHandler().setTIMEOUT(20)).setPort(port).open();
	}

}
