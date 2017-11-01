package sz.lzh.LogListener.view.frame;

public class ViewConfig {

	public static int WINDOW_HEIGHT = 450; 
	public static int WINDOW_WEIGHT = 700; 
	public static int INDEX_HEIGHT = 30; 
	public static int INDEX_WEIGHT = 400; 
	public static int LABEL_HEIGHT = 550; 
	public static int LABEL_WEIGHT = 150; 
	public static int INFOS_HEIGHT = 450; 
	public static int INFOS_WEIGHT = 600; 
	
	
	public static int BORDER_WEIGET = 5;
	
	
	public static String currentAdrress;
	
	private static String userName;
	private static String passWord;

	public static String getUserName() {
		return userName;
	}

	public static void setUserName(String user) {
		userName = user;
	}

	public static String getPassWord() {
		return passWord;
	}

	public static void setPassWord(String pass) {
		passWord = pass;
	}
	
}
