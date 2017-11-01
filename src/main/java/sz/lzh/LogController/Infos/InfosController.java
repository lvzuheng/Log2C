package sz.lzh.LogController.Infos;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

import io.netty.channel.Channel;
import sz.lzh.LogController.bean.Infos;

public class InfosController {

	private static boolean FLAG = false;
	private static SystemInfos systemInfos;
	private static List<Channel> cList = new ArrayList<Channel>();
	public static void startCommit(final Channel channel){
		MoreOutputStream moreOutputStream = new MoreOutputStream(new ByteArrayOutputStream(),System.out);
		cList.add(channel);
		if(systemInfos ==null || (!systemInfos.isAlive() && !FLAG)){
			systemInfos = new SystemInfos().setOutputStream(moreOutputStream);
			FLAG = true;
			systemInfos.write(new InfosRunnable() {
				public void run(ByteArrayOutputStream outputStream) {
					// TODO Auto-generated method stub
					while(FLAG){
						try {
							int index = 0;
							String stream = outputStream.toString().trim();

							while(stream.length()>0 && index>=0){
								index=stream.indexOf("\r\n");
								if(index+2>=stream.length())
									break;
								if(index<0){
									index = stream.length();
									Infos infos = new Infos(111,stream.substring(0,index));
									for(Channel channel:cList){
										channel.writeAndFlush(JSON.toJSONString(infos));
									}
									break;
								}else{
									Infos infos = new Infos(111,stream.substring(0,index));
									for(Channel channel:cList){
										channel.writeAndFlush((JSON.toJSONString(infos)+"\r\n").getBytes());
									}
									stream = stream.substring(index+2, stream.length());
								}
							}
							outputStream.reset();
							Thread.sleep(100);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			});
		}

	}
	public static void stopcommit(Channel channel){
		cList.remove(channel);
		if(systemInfos!=null && FLAG && cList.size()==0){
			FLAG = false;
			systemInfos.setOutputStream(System.out);
		}
	}
}
