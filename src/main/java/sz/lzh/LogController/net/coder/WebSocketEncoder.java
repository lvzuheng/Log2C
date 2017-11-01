package sz.lzh.LogController.net.coder;

import java.net.SocketAddress;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

public class WebSocketEncoder extends  MessageToMessageEncoder<String>{


	public static TextWebSocketFrame textWebSocketEncode(String request){
		TextWebSocketFrame tws = new TextWebSocketFrame(request);  
		return tws;
	}

	@Override
	protected void encode(ChannelHandlerContext ctx, String msg, List<Object> out) throws Exception {
		// TODO Auto-generated method stub
		out.add(textWebSocketEncode(msg));
	}

	private static String getDateTime() {  
		// Calendar calendar = Calendar.getInstance();  
		Calendar calendar = new GregorianCalendar();  
		java.util.Date date = new java.util.Date();  
		calendar.setTime(date);  
		String sHour = null;  
		String sMinute = null;  
		String sSecond = null;  
		String sYear = null;  
		String sMonth = null;  
		String sDay = null;  
		int year = calendar.get(Calendar.YEAR);  
		int month = calendar.get(Calendar.MONTH) + 1;  
		int day = calendar.get(Calendar.DATE);  
		int hour = calendar.get(Calendar.HOUR_OF_DAY);  
		int minute = calendar.get(Calendar.MINUTE);  
		int second = calendar.get(Calendar.SECOND);  
		int milliSecond = calendar.get(Calendar.MILLISECOND);   
		sYear = String.valueOf(year);  
		if (month < 10) {  
			sMonth = "0" + month;  
		} else  
			sMonth = String.valueOf(month);  
		if (day < 10) {  
			sDay = "0" + day;  
		} else  
			sDay = String.valueOf(day);   
		if (hour < 10) {  
			sHour = "0" + hour;  
		} else {  
			sHour = String.valueOf(hour);  
		}   
		if (minute < 10) {  
			sMinute = "0" + minute;  
		} else {  
			sMinute = String.valueOf(minute);  
		}   
		if (second < 10) {  
			sSecond = "0" + second;  
		} else {  
			sSecond = String.valueOf(second);  
		}   
		return sYear + "-" + sMonth + "-" + sDay + " " + sHour + ":" + sMinute + ":" + sSecond;  
	}


}
