package sz.lzh.LogListener.view.frame;

import java.awt.Color;import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class LogWindowFrame {
	private JFrame jFrameWindow;
	private static int WIDTH =  600;
	private static int HEIGHT =  400;
	private static int positionX =  600;
	private static int positionY =  300;
	

	public LogWindowFrame(){
		initFrame();
		initPanel();
	}
	private void initFrame(){
		jFrameWindow=new JFrame("远程日志监听窗口");//实例化窗体对象

		jFrameWindow.setSize(WIDTH,HEIGHT);//设置窗体大小

		jFrameWindow.setBackground(Color.GRAY);//设置窗体的背景颜色

		jFrameWindow.setLocation(positionX,positionY);//设置窗体的显示位置

		jFrameWindow.setVisible(true);//让组建显示

		jFrameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrameWindow.setIconImage(Toolkit.getDefaultToolkit().createImage("LogListener/window/resources/icon.jpg"));
		jFrameWindow.setLayout(new FlowLayout());
	}
	private void initPanel(){
		Container cont = jFrameWindow.getContentPane();
		cont.add(topPanel());
		cont.add(midPanel());
		jFrameWindow.setVisible(true);
	}
	
	public JPanel topPanel(){
		JPanel jPanel =new JPanel();             //实例化一个面板

		//设置面板背景色为蓝色，如果不引入AWT包，程序将出错，可以试试看

		
		jPanel.setBackground(Color.GRAY);       

		jPanel.setSize(WIDTH,100);          //设置面板对象大小
		JLabel usernameLabel = new JLabel("用户名");
		JLabel passwordLabel = new JLabel("密码");
		JTextField usernameTextField = new JTextField(12);
		JTextField passwordTextField = new JTextField(12);
		JButton loginBtn = new JButton("登录");
		jPanel.add(usernameLabel);
		jPanel.add(usernameTextField);
		jPanel.add(passwordLabel);
		jPanel.add(passwordTextField);
		jPanel.add(loginBtn);
		return jPanel;
	}
	
	

	
	public JPanel midPanel(){
		JPanel jPanel =new JPanel();             //实例化一个面板

		//设置面板背景色为蓝色，如果不引入AWT包，程序将出错，可以试试看

		
		jPanel.setBackground(Color.WHITE);       

		jPanel.setSize(WIDTH,HEIGHT);          //设置面板对象大小
		JLabel infosLabel = new JLabel("消息：");
		JTextArea infosTextArea = new JTextArea(10,46);
		infosTextArea.setLineWrap(true);//设置它自动换行
		infosTextArea.setWrapStyleWord(true);//设置它自动换行时根据单词换行,而不是根据字符
		jPanel.add(infosLabel);
		jPanel.add(infosTextArea);
		return jPanel;
	}
	public JPanel bottomPanel(){
		JPanel jPanel =new JPanel();             //实例化一个面板
		
		//设置面板背景色为蓝色，如果不引入AWT包，程序将出错，可以试试看
		
		
		jPanel.setBackground(Color.WHITE);       
		
		jPanel.setSize(WIDTH,HEIGHT);          //设置面板对象大小
		JLabel infosLabel = new JLabel("消息：");
		JTextArea infosTextArea = new JTextArea(10,46);
		infosTextArea.setLineWrap(true);//设置它自动换行
		infosTextArea.setWrapStyleWord(true);//设置它自动换行时根据单词换行,而不是根据字符
		jPanel.add(infosLabel);
		jPanel.add(infosTextArea);
		return jPanel;
	}
}
