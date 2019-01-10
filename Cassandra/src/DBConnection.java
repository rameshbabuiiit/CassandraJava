

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

public class DBConnection implements AutoCloseable{
	private Logger log = LoggerFactory.getLogger(DBConnection.class);
	private Cluster cluster;
	private Session session;
	public Session getConnection(String serverIp) {
		log.debug("Connecting to Host at: " + serverIp);
		try {
			cluster = Cluster.builder().addContactPoint(serverIp).build();
			session = cluster.connect("system");
		}catch(Exception e) {
			log.error("Session Could not be created for: " + serverIp + "!!\n" + e);
		 }
		return session;
	}

	@Override
	public void close() throws Exception {
		if(cluster!=null) {
			cluster.close();
			log.debug("Cluster Connection Closed!");
		}
	}
}
