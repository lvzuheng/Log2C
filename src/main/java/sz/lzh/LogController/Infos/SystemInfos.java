package sz.lzh.LogController.Infos;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.netty.channel.Channel;


public class SystemInfos extends Thread{

	private InfosRunnable infosRunnable;
	private ByteArrayOutputStream outputStream;
	
	
	public SystemInfos() {
		// TODO Auto-generated constructor stub
		
	}
	
	
	public SystemInfos setOutputStream(ByteArrayOutputStream outputStream){
		PrintStream printStream = new PrintStream(outputStream);
		this.outputStream = outputStream;
		System.setErr(printStream);
		System.setOut(printStream);
		return this;
	}
	public void setOutputStream(PrintStream printStream){
		System.setOut(printStream);
	}
	
	public ByteArrayOutputStream getConsoleInfos(){
		return outputStream;
	}

	public void write(InfosRunnable runnable){
		this.infosRunnable =runnable;
			start();
	}
	
	public void close(){
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		infosRunnable.run(outputStream);
	}
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
		outputStream.flush();
		outputStream.close();
	}
	
}
