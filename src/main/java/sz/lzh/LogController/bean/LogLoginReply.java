package sz.lzh.LogController.bean;

public class LogLoginReply {

	private int pid;
	private boolean status;
	
	public LogLoginReply(int pid,boolean status) {
		// TODO Auto-generated constructor stub
		setPid(pid);
		setStatus(status);
	}
	
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
}
