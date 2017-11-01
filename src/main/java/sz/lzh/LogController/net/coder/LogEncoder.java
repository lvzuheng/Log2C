package sz.lzh.LogController.net.coder;


import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class LogEncoder extends  MessageToByteEncoder<String>{

	@Override
	protected void encode(ChannelHandlerContext ctx, String msg, ByteBuf out) throws Exception {
		// TODO Auto-generated method stub
		out.writeBytes((msg+"\r\n").getBytes());
	}

}
