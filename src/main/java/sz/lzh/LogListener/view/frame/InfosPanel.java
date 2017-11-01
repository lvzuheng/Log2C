package sz.lzh.LogListener.view.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class InfosPanel extends JPanel{
	
	private JTextArea infosArea;

	public InfosPanel() {
		// TODO Auto-generated constructor stub
		init();
	}
	
	private void init(){
		this.setBackground(Color.LIGHT_GRAY);
		this.setPreferredSize(new Dimension(ViewConfig.INFOS_WEIGHT,ViewConfig.INFOS_HEIGHT));
		this.setBorder(BorderFactory.createLineBorder(Color.GRAY,ViewConfig.BORDER_WEIGET));
		this.setLayout(new BorderLayout());
		infosArea = new JTextArea();
		infosArea.setLineWrap(false);
		infosArea.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		infosArea.setFont(new Font("宋体", Font.BOLD, 20));
		JScrollPane scroll = new JScrollPane(infosArea); 
		this.add(scroll);
	}
	
	public void append(String string){
		infosArea.append(string+"\r\n");
	}
}
