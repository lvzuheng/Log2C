package sz.lzh.LogListener.net;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import sz.lzh.LogController.Action.InfosLogin;
import sz.lzh.LogController.bean.Infos;
import sz.lzh.LogController.bean.LogLogin;
import sz.lzh.LogListener.Action.InfosAction;
import sz.lzh.LogListener.Action.InfosControllerAction;
import sz.lzh.LogListener.Action.LoginAction;

public class LogListenerDecoder extends ByteToMessageDecoder{

	
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
		// TODO Auto-generated method stub
		byte[] buf=new byte[msg.readableBytes()];
		msg.readBytes(buf);
		System.out.println(new String(buf,"UTF-8"));
		int pid = JSON.parseObject(new String(buf)).getIntValue("pid");
		if(pid == 110){
			LogLogin login = JSONObject.parseObject(new String(buf,"UTF-8"), LogLogin.class);
			new LoginAction().excute(login.isStatus(),ctx.channel());
		}else if(pid == 111){
			Infos infos = JSONObject.parseObject(new String(buf,"UTF-8"), Infos.class);
			new InfosAction().excute(infos.getInfo());
		}else if(pid == 112){
			new InfosControllerAction().excute(new String(buf,"UTF-8"),ctx.channel());
		}
	}
}
