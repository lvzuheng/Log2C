package sz.lzh.LogListener.Action;

import org.omg.CORBA.CTX_RESTRICT_SCOPE;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.netty.channel.Channel;
import sz.lzh.LogController.Infos.InfosController;
import sz.lzh.LogController.bean.Infos;
import sz.lzh.LogController.config.ActionConfig;
import sz.lzh.LogListener.view.frame.MainFrame;

public class InfosControllerAction {
	
	public void excute(String msg,Channel channel) {
		Infos infos = JSONObject.parseObject(msg, sz.lzh.LogController.bean.Infos.class);
		MainFrame.getInstance().append("(来自服务端的消息)"+(infos.isStatus()?"开始监听":"监听失败,请重新登陆"));
	}
}
