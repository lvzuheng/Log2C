package sz.lzh.LogController.server;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;




public class AutoConnectManager extends Thread{
	    private Set<NettyConnector>       clients  = new HashSet<NettyConnector>();
	    
	    private static AutoConnectManager autoConnectManager;
	    
	    public static AutoConnectManager getInstance() {
			if(autoConnectManager == null)
				autoConnectManager = new AutoConnectManager();
			
			return autoConnectManager;
		}
	    
	    private AutoConnectManager() {
	    }


	    public AutoConnectManager offer(NettyConnector client) {
	        if (!clients.contains(client)) {
	            clients.add(client);
	        }
	        return this;
	    }

	    public void remove(NettyConnector client) {
	        clients.remove(client);
	    }

	    @Override
	    public void run() {
	        while (true) {
	            try {
	                Iterator<NettyConnector> it = clients.iterator();
	                Thread.sleep(10000);
	                while (it.hasNext()) {
	                	NettyConnector client = it.next();
	                    if (!client.isConnected()) {
	                        client.reConnect();
	                    }
	                }
	            } catch (Exception e) {
	            }
	        }
	    }
}
