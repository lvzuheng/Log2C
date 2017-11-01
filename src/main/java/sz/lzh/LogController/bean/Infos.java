package sz.lzh.LogController.bean;

public class Infos {

	private int pid;
	private String info;
	private boolean status;
	
	public Infos(int pid,String info) {
		// TODO Auto-generated constructor stub
		setInfo(info);
		setPid(pid);
	}
	public Infos(int pid,boolean status) {
		// TODO Auto-generated constructor stub
		setPid(pid);
		setStatus(status);
	}
	public Infos() {
		// TODO Auto-generated constructor stub
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
}
