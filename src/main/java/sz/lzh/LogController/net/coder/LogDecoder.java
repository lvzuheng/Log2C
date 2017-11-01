package sz.lzh.LogController.net.coder;

import java.util.List;

import com.alibaba.fastjson.JSON;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.json.JsonObjectDecoder;
import sz.lzh.LogController.Action.InfosControllerAction;
import sz.lzh.LogController.Action.InfosLoginAction;
import sz.lzh.LogController.Infos.InfosController;
import sz.lzh.LogController.config.ActionConfig;

public class LogDecoder extends ByteToMessageDecoder{

	public LogDecoder() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> arg2) throws Exception {
		// TODO Auto-generated method stub
		byte[] b = new byte[byteBuf.readableBytes()];
		byteBuf.readBytes(b);
		String msg = new String(b);
		excute(msg,channelHandlerContext.channel()  );
	}
	
	private void excute(String msg,Channel channel){
	
		int pid = JSON.parseObject(msg).getInteger("pid");
		switch (pid) {
		case ActionConfig.LOGIN_PID:
				InfosLoginAction.excute(msg,channel);
			break;
		case ActionConfig.CONTROLLER_PID:
				new InfosControllerAction().excute(msg, channel);
			break;

		default:
			break;
		}
	}
	
}
