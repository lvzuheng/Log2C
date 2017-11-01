package sz.lzh.LogController.Action;

import org.omg.CORBA.CTX_RESTRICT_SCOPE;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import sz.lzh.LogController.Infos.InfosController;
import sz.lzh.LogController.bean.Infos;
import sz.lzh.LogController.config.ActionConfig;
import sz.lzh.LogController.net.coder.WebSocketEncoder;

public class InfosControllerAction {
	
	public void excute(String msg,Channel channel) {
		Infos infos = JSONObject.parseObject(msg, sz.lzh.LogController.bean.Infos.class);
		Infos infosReply;
		if(infos.isStatus()){
			infosReply = new Infos(ActionConfig.CONTROLLER_PID, true);
			InfosController.startCommit(channel);
		}
		else{
			infosReply = new Infos(ActionConfig.CONTROLLER_PID, false);			
			InfosController.stopcommit(channel);
		}
		channel.writeAndFlush(JSON.toJSONString(infosReply).toString());
	}
	
//	public void excute(String msg,Channel channel,boolean isWebSocket){
//		Infos infos = JSONObject.parseObject(msg, sz.lzh.LogController.bean.Infos.class);
//		Infos infosReply;
//		if(infos.isStatus()){
//			infosReply = new Infos(ActionConfig.CONTROLLER_PID, true);
//			InfosController.startCommit(channel);
//		}
//		else{
//			infosReply = new Infos(ActionConfig.CONTROLLER_PID, false);			
//			InfosController.stopcommit(channel);
//		}
//		if(isWebSocket){
//			channel.writeAndFlush(WebSocketEncoder.encode(channel.remoteAddress(), JSON.toJSONString(infosReply).toString()));
//		}else {
//			channel.writeAndFlush((JSON.toJSONString(infosReply)+"\r\n").getBytes());
//		}
//	}
	
	
}
