package sz.lzh.LogController.Action;

import com.alibaba.fastjson.JSONObject;

import io.netty.channel.Channel;
import sz.lzh.LogController.bean.LogLogin;

public class InfosLoginAction {
	private static  InfosLogin infosLogin;

	public static void excute(String msg,Channel channel){
		LogLogin login = new LogLogin();
		login.setPid(110);
		System.out.println("110msg:"+msg);
		if(infosLogin == null ||infosLogin.excute(msg)){
			login.setStatus(true);
		}else
			login.setStatus(false);
		channel.writeAndFlush(JSONObject.toJSONString(login));
	}

//	public static void excute(String msg,Channel channel,boolean isWebSocket){
//		LogLogin login = new LogLogin();
//		login.setPid(110);
//		if(infosLogin.excute(msg)){
//			login.setStatus(true);
//		}else
//			login.setStatus(false);
//
//		if(isWebSocket){
//			channel.writeAndFlush(WebSocketEncoder.encode(channel.remoteAddress(), JSON.toJSONString(login).toString()));
//		}else {
//			channel.writeAndFlush((JSONObject.toJSONString(login)+"\r\n").getBytes());
//		}
//	}
	public static void setInfosLogin(InfosLogin infos){
		infosLogin = infos;
	}

}
