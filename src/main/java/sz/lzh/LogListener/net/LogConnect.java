package sz.lzh.LogListener.net;


import com.alibaba.fastjson.JSON;

import io.netty.channel.Channel;
import sz.lzh.LogController.bean.Infos;
import sz.lzh.LogController.bean.LogLogin;
import sz.lzh.LogController.config.ActionConfig;
import sz.lzh.LogController.server.NettyConnector;
import sz.lzh.LogListener.view.frame.MainFrame;
import sz.lzh.LogListener.view.frame.ViewConfig;
import sz.lzh.LogListener.view.utils.MD5;

public class LogConnect {

	private static NettyConnector nettyConnector ;
	private static String ip = "127.0.0.1";
	private static int port = -1;
	private String username;
	private String password;
	private Channel channel;
	
	
	public boolean  connect(String ip,int port,String username,String password){
		
		if(nettyConnector!=null && nettyConnector.isConnected() && this.ip.equals(ip))
				return false;
		this.ip = ip;
		this.port = port;
		this.username = ViewConfig.getUserName();
		this.password = ViewConfig.getPassWord();
		
		nettyConnector = new NettyConnector().create().setHandler(new LogListenerHandler().setTIMEOUT(10)).setAUTO(true).setAddress(ip).setPort(port).connect();
		
		return true;
	}
	
	
	public boolean connect( String ip,int port){
		if(username==null || password == null)
			return false;
		connect(ip, port, ViewConfig.getUserName(), ViewConfig.getPassWord());
		return true;
	}
	
	public boolean connect(String username,String password){
		String[] address = ViewConfig.currentAdrress.split(":");
		if(address==null || address.length<2)
			return false;
		connect(address[0], Integer.valueOf(address[1]), username, password);
		return true;
	}
	


	private static LogConnect logConnect;

	public static LogConnect getInstance(){
		if(logConnect == null)
			logConnect = new LogConnect();
		return logConnect;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}
	
	public void login(String username,String password){
		if(channel !=null ){
			LogLogin login = new LogLogin();
			login.setUsername(username);
			login.setPassword(MD5.getMd5(password));
			login.setPid(ActionConfig.LOGIN_PID);
			channel.writeAndFlush((JSON.toJSONString(login)+"\r\n").getBytes());
		}
	}
	public void login(){
		if(channel !=null ){
			LogLogin login = new LogLogin();
			login.setUsername(username);
			login.setPassword(MD5.getMd5(password));
			login.setPid(ActionConfig.LOGIN_PID);
			channel.writeAndFlush((JSON.toJSONString(login)+"\r\n").getBytes());
		}
	}

	public void startListener(){
		if(channel !=null){
			Infos infos = new Infos(ActionConfig.CONTROLLER_PID,true);
			channel.writeAndFlush((JSON.toJSONString(infos)+"\r\n").getBytes());
		}
	}
	public void stopListener(){
		if(channel !=null){
			Infos infos = new Infos(ActionConfig.CONTROLLER_PID,false);
			channel.writeAndFlush((JSON.toJSONString(infos)+"\r\n").getBytes());
			
		}
	}

	
}
