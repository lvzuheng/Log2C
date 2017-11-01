package sz.lzh.LogController.Infos;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.Set;

public class MoreOutputStream extends ByteArrayOutputStream{

	private Set<OutputStream> set = new HashSet<OutputStream>();
	
	public MoreOutputStream(OutputStream... outputStream) {
		// TODO Auto-generated constructor stub
		for(OutputStream os:outputStream)
			set.add(os);
	}
	
	@Override
	public synchronized void write(int paramInt) {
		// TODO Auto-generated method stub
		super.write(paramInt);
		for(OutputStream outputStream:set){
			try {
				outputStream.write(paramInt);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void write(byte[] paramArrayOfByte) throws IOException {
		// TODO Auto-generated method stub
		super.write(paramArrayOfByte);
		for(OutputStream outputStream:set){
			try {
				outputStream.write(paramArrayOfByte);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public synchronized void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2) {
		// TODO Auto-generated method stub
		super.write(paramArrayOfByte, paramInt1, paramInt2);
		for(OutputStream outputStream:set){
			try {
				outputStream.write(paramArrayOfByte,  paramInt1,  paramInt2);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
