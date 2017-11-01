package sz.lzh.LogListener.view.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import sz.lzh.LogListener.view.frame.MainFrame;

public class FileUtiles {
	private final static String addressFile = System.getProperty("user.dir")+"/resource/address.txt";
	private final static String userFile = System.getProperty("user.dir")+"/resource/userInfos.txt";

	public static void addAddress(String ip,int port){
		try {
			StringBuilder address = new StringBuilder().append(ip).append(":").append(port).append("\r\n");
			StreamWriter.PrintStreamWrite(address.toString(),addressFile,true);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void removeAddress(String address){
		File file = new File(addressFile);
		BufferedReader br = null;
		try {
			if(!file.exists())
				return;
			br = new BufferedReader(new FileReader(file));
			StringBuilder sb = new StringBuilder();
			String temp;
			while((temp=br.readLine())!=null){
				if(temp.trim().equals(address)){
					System.out.println("找到了要删除的行");
					continue;
				}
				sb.append(temp+"\r\n");
			}
			br.close();
			BufferedWriter bw;
			bw = new BufferedWriter(new FileWriter(file));
			bw.write(sb.toString());
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("删除 完成  OK!");
	}
	public static void clearAddress(){
		try {
			StreamWriter.PrintStreamWrite("",addressFile,true);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String[] getAllAddress(){
		BufferedReader br = null;
		try {
			File file = new File(addressFile);
			if(!file.exists()){
				if(!file.getParentFile().exists())
					file.getParentFile().mkdirs();
				file.createNewFile();
				return null;
			}
			br = new BufferedReader(new FileReader(file));
			List<String> sList = new ArrayList<String>();
			String temp;
			while((temp=br.readLine())!=null){
				sList.add(temp);
			}
			br.close();
			String[] strings = new String[sList.size()];
			sList.toArray(strings);
			return strings;
		}catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	
	public static void addUser(String username,String password){
		try {
			StringBuilder address = new StringBuilder().append(username).append(":").append(password);
			StreamWriter.PrintStreamWrite(address.toString(),userFile,false);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			MainFrame.getInstance().append(e.getMessage());
		}
	}
	
	public static String[] getUser(){
		BufferedReader br = null;
		try {
			File file = new File(userFile);
			if(!file.exists()){
				if(!file.getParentFile().exists())
					file.getParentFile().mkdirs();
				file.createNewFile();
				return null;
			}
			br = new BufferedReader(new FileReader(file));
			List<String> sList = new ArrayList<String>();
			String temp;
			while((temp=br.readLine())!=null){
				sList.add(temp);
			}
			br.close();
			String[] strings = new String[sList.size()];
			sList.toArray(strings);
			return strings;
		}catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
}
