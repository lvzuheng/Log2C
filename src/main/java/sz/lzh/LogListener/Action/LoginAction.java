package sz.lzh.LogListener.Action;

import com.alibaba.fastjson.JSON;

import io.netty.channel.Channel;
import sz.lzh.LogController.bean.Infos;
import sz.lzh.LogListener.view.frame.MainFrame;

public class LoginAction {

	public void excute(boolean isSccuss,Channel channel){
		
		if(isSccuss){
			System.out.println("(服务端的消息)"+(isSccuss?"登录成功,开始监听":"登录失败"));
			MainFrame.getInstance().append("(服务端的消息)"+(isSccuss?"登录成功":"登录失败"));
			channel.writeAndFlush((JSON.toJSONString(new Infos(111,true))+"\r\n").getBytes());
		}
	}
}
