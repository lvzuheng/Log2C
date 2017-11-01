package sz.lzh.LogListener.view.utils;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

public class StreamWriter {

	public static void PrintStreamWrite(byte[] b,String path,boolean savegethere) throws FileNotFoundException{
		PrintStream printStream = null;
		try {
			File file = new File(path);
			if(!file.exists())
				file.mkdirs();
			printStream = new PrintStream(new FileOutputStream(file,savegethere),true);
			printStream.write(b);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			printStream.close();
		}
	}
	
	public static void PrintStreamWrite(String string,String path,boolean savegethere) throws FileNotFoundException{
		PrintWriter printWriter = null;
		try {
			File file = new File(path);
			if(!file.exists())
				file.createNewFile();
			printWriter = new PrintWriter(new FileOutputStream(file,savegethere),true);
			printWriter.write(string);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			printWriter.close();
		}
	}
	public void FileWriterWrite(String string,String path) throws FileNotFoundException{
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(new File(path),true);
			fileWriter.write(string);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				fileWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
