package sz.lzh.LogListener.view.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Label;import java.awt.MenuBar;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.View;

import sz.lzh.LogListener.view.utils.FileUtiles;

public class MainFrame extends JFrame{

	private static int WIDTH =  600;
	private static int HEIGHT =  400;
	private static int positionX =  600;
	private static int positionY =  300;

	private IndexPanel indexPanel;
	private LabelPanel labelPanel;
	private InfosPanel infosPanel;
	
	private static MainFrame mainFrame;
	
	public static MainFrame getInstance(){
		if(mainFrame == null)
			mainFrame = new MainFrame();
		return mainFrame;
	}

	public MainFrame(){
		initFrame();
		initMenu();
		this.setVisible(true);
	}
	private void initFrame(){
		this.setTitle("远程日志监听窗口");
		setSize(ViewConfig.WINDOW_WEIGHT,ViewConfig.WINDOW_HEIGHT);//设置窗体大小

		setBackground(Color.GRAY);//设置窗体的背景颜色

		setLocation(positionX,positionY);//设置窗体的显示位置


		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().createImage("LogListener/window/resources/icon.jpg"));
		setLayout(new BorderLayout());
		indexPanel = new IndexPanel();
		getContentPane().add("North", indexPanel);
		labelPanel = new LabelPanel();
		getContentPane().add("West", labelPanel);
		infosPanel = new InfosPanel();
		getContentPane().add("Center", infosPanel);

	}

	private void initMenu(){
		JMenuBar menubar = new JMenuBar();
		menubar.add(getSelectMenu());
		menubar.add(getLoginMenu());
		this.setJMenuBar(menubar);
	}

	private JMenu getSelectMenu(){
		JMenu selectmenu = new JMenu("选项");
		JMenuItem addItem = new JMenuItem("添加地址");
		addItem.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent paramActionEvent) {
				// TODO Auto-generated method stub
				final JFrame frame=new JFrame("添加地址");
				frame.setLocation(positionX+80,positionY+100);
				frame.setSize(400,80);
				JPanel addIPPannel = new JPanel();
				JLabel ipLable = new JLabel("ip地址");
				addIPPannel.add(ipLable);
				final JTextField ipTextField = new JTextField(10);
				addIPPannel.add(ipTextField);
				JLabel portLable = new JLabel("端口");
				addIPPannel.add(portLable);
				final JTextField portTextField = new JTextField(6);
				addIPPannel.add(portTextField);
				JButton addButton = new JButton("确定");
				addButton.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent paramActionEvent) {
						// TODO Auto-generated method stub
						if(ipTextField.getText()!=null && portTextField.getText()!=null)
							labelPanel.addJList(ipTextField.getText(), Integer.valueOf(portTextField.getText()));

						setEnabled(true);
						frame.dispose();
					}
				});
				addIPPannel.add(addButton);
				frame.add(addIPPannel);
				frame.addWindowListener(new WindowListener() {

					public void windowOpened(WindowEvent paramWindowEvent) {
						// TODO Auto-generated method stub
						setEnabled(false);
					}

					public void windowIconified(WindowEvent paramWindowEvent) {
						// TODO Auto-generated method stub

					}

					public void windowDeiconified(WindowEvent paramWindowEvent) {
						// TODO Auto-generated method stub

					}

					public void windowDeactivated(WindowEvent paramWindowEvent) {
						// TODO Auto-generated method stub

					}

					public void windowClosing(WindowEvent paramWindowEvent) {
						// TODO Auto-generated method stub
						setEnabled(true);
					}

					public void windowClosed(WindowEvent paramWindowEvent) {
						// TODO Auto-generated method stub

					}

					public void windowActivated(WindowEvent paramWindowEvent) {
						// TODO Auto-generated method stub

					}
				});
				frame.setVisible(true);
			}
		});
		selectmenu.add(addItem);

		JMenuItem removeItem = new JMenuItem("删除地址");
		removeItem.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent paramActionEvent) {
				// TODO Auto-generated method stub
				labelPanel.deleteCurrentSelection();

			}
		});
		selectmenu.add(removeItem);
		return selectmenu;
	}


	private JMenu getLoginMenu(){
		JMenu loginmenu = new JMenu("选项");
		JMenuItem userItem = new JMenuItem("设置用户名");
		userItem.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent paramActionEvent) {
				// TODO Auto-generated method stub
				final JFrame frame=new JFrame("设置用户名");
				frame.setLocation(positionX+80,positionY+100);
				frame.setSize(400,80);
				JPanel addIPPannel = new JPanel();
				JLabel userLable = new JLabel("用户名");
				addIPPannel.add(userLable);
				final JTextField userField = new JTextField(8);
				addIPPannel.add(userField);
				JLabel passwordtLable = new JLabel("密码");
				addIPPannel.add(passwordtLable);
				final JPasswordField  passwordField = new JPasswordField (8);
				addIPPannel.add(passwordField);
				JButton userButton = new JButton("确定");
				String[] userInfos = FileUtiles.getUser();
				try {
					if(userInfos!=null && userInfos.length>=2){
						userField.setText(userInfos[0].isEmpty()?"":userInfos[0]);
						passwordField.setText(userInfos[1].isEmpty()?"":userInfos[1]);
					}
				} catch (Exception e) {
					// TODO: handle exception
					append(e.getMessage());
				}
				userButton.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent paramActionEvent) {
						// TODO Auto-generated method stub
						if(userField.getText()!=null && passwordField.getPassword()!=null){
							ViewConfig.setUserName(userField.getText());
							ViewConfig.setUserName(new String(passwordField.getPassword()));
							FileUtiles.addUser(userField.getText(),new String(passwordField.getPassword()));
						}
						setEnabled(true);
						frame.dispose();
					}
				});
				addIPPannel.add(userButton);
				frame.add(addIPPannel);
				frame.addWindowListener(new WindowListener() {

					public void windowOpened(WindowEvent paramWindowEvent) {
						// TODO Auto-generated method stub
						setEnabled(false);
					}

					public void windowIconified(WindowEvent paramWindowEvent) {
						// TODO Auto-generated method stub

					}

					public void windowDeiconified(WindowEvent paramWindowEvent) {
						// TODO Auto-generated method stub

					}

					public void windowDeactivated(WindowEvent paramWindowEvent) {
						// TODO Auto-generated method stub

					}

					public void windowClosing(WindowEvent paramWindowEvent) {
						// TODO Auto-generated method stub
						setEnabled(true);
					}

					public void windowClosed(WindowEvent paramWindowEvent) {
						// TODO Auto-generated method stub

					}

					public void windowActivated(WindowEvent paramWindowEvent) {
						// TODO Auto-generated method stub

					}
				});
				frame.setVisible(true);
			}
		});
		loginmenu.add(userItem);
		return loginmenu;
	}

	public void append(String msg){
		infosPanel.append(msg);
	}
}
