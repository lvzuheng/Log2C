package sz.lzh.LogListener.Action;

import sz.lzh.LogListener.view.frame.MainFrame;

public class InfosAction {

	public void excute(String msg){
		if(msg!=null && !msg.equals("")){
			System.out.println("(服务端的消息)"+msg);
			MainFrame.getInstance().append(msg);
		}
	}
}
