package sz.lzh.LogListener.view.frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import sz.lzh.LogListener.view.utils.FileUtiles;

public class LabelPanel extends JPanel{

	private Graphics graphics;
	JList<String> jList;

	public LabelPanel(){
		init();

	}

	private void init(){


		//设置面板背景色为蓝色，如果不引入AWT包，程序将出错，可以试试看


		this.setBackground(Color.WHITE);       

		this.setPreferredSize(new Dimension(ViewConfig.LABEL_WEIGHT,ViewConfig.LABEL_HEIGHT));         //设置面板对象大小

		this.setBorder(BorderFactory.createLineBorder(Color.GRAY,ViewConfig.BORDER_WEIGET));

		this.setLayout(new FlowLayout(FlowLayout.CENTER,100,0));

		JLabel titleLable = new JLabel("地址");

		this.add(titleLable);
		JLabel lineLable = new JLabel("-----------------------");
		this.add(lineLable);
		initList();

		this.setVisible(true);
	}
	private void initList(){
		String[] strings = FileUtiles.getAllAddress();
		if(strings !=null){
			jList = new JList<String>(strings);
		}else {
			jList = new JList<String>();
		}
		jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jList.addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent paramListSelectionEvent) {
				// TODO Auto-generated method stub
				if(paramListSelectionEvent.getValueIsAdjusting()){
					if(jList.getSelectedValue() ==null)
						return;
					ViewConfig.currentAdrress = jList.getSelectedValue().toString();
				}
			}
		} );
		this.add(jList);
	}

	@Override
	protected void paintComponent(Graphics paramGraphics) {
		// TODO Auto-generated method stub
		super.paintComponent(paramGraphics);
		this.graphics =  this.getGraphics();
	}

	public String getCurrentSelectValue(){
		return jList.getSelectedValue();
	}

	public boolean addJList(String ip,int port){
		FileUtiles.addAddress(ip, port);
		jList.setListData(FileUtiles.getAllAddress());
		return true;
	}
	public boolean deleteCurrentSelection(){
		if(jList.getSelectedValue()!=null){
			FileUtiles.removeAddress(jList.getSelectedValue());
			jList.setListData(FileUtiles.getAllAddress());
			return true;
		}
		return false;
	}
}
