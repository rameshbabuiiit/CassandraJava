import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

public class Test {
	public static void main(String[] args) {
		Cluster cluster;
		Session session;
		ResultSet rs; 
		cluster = Cluster.builder().addContactPoint("localhost").build();
		session = cluster.connect("demokeyspace");
		try {
			rs =  session.execute("SELECT * FROM TEST");
			for(Row row : rs) {
				System.out.println(row.getString("col1") + "\n" + row.getString("col2") + "\n" );
			}
		}catch(Exception e){
			throw e;
		 }finally {
			if(cluster!=null) {
				cluster.close();
			}
		  }	
	}
}
