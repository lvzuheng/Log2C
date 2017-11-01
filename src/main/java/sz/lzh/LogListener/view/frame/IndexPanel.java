package sz.lzh.LogListener.view.frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.plaf.InsetsUIResource;
import javax.swing.text.View;

import sz.lzh.LogListener.net.LogConnect;
import sz.lzh.LogListener.view.utils.FileUtiles;

public class IndexPanel extends JPanel{
public IndexPanel(){
		
		init();
	}
	
	private void init(){
		this.setBackground(Color.PINK);
		this.setPreferredSize(new Dimension( ViewConfig.INDEX_WEIGHT,ViewConfig.INDEX_HEIGHT));
		
		add(getLoginButton());
		add(getStartButton());
		add(getStopButton());
		setLayout(new FlowLayout(FlowLayout.LEFT));
	
	}
	
	private JButton getLoginButton(){
		JButton loginButton = new JButton("登录");
		loginButton.setMargin(new InsetsUIResource(0, 0, 0, 0));
		loginButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent paramActionEvent) {
				// TODO Auto-generated method stub
				if(ViewConfig.getUserName() == null || ViewConfig.getPassWord() == null){
					String[] user = FileUtiles.getUser();
					if(user!=null && user.length >=2){
						ViewConfig.setUserName(user[0]);
						ViewConfig.setPassWord(user[1]);
					}
				}
				LogConnect.getInstance().connect(ViewConfig.getUserName(),  ViewConfig.getPassWord());
			}
		});
		return loginButton;
	}

	private JButton getStartButton(){
		JButton startButton = new JButton("开始");
		startButton.setMargin(new InsetsUIResource(0, 0, 0, 0));
		startButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent paramActionEvent) {
				// TODO Auto-generated method stub
				LogConnect.getInstance().startListener();
			}
		});
		return startButton;
	}
	private JButton getStopButton(){
		JButton stopButton = new JButton("停止");
		stopButton.setMargin(new InsetsUIResource(0, 0, 0, 0));
		stopButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent paramActionEvent) {
				// TODO Auto-generated method stub
				LogConnect.getInstance().stopListener();
				
			}
		});
		return stopButton;
	}
}
