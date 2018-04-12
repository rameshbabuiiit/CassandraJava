import com.datastax.driver.core.*;

public class Demo {
	public static void main(String[] args) throws Exception {
		try(DBConnection conn = new DBConnection()){
			Session session = conn.getConnection("localhost");
			putData(session);
			getData(session);
	    }
	}

	
			///////////////////////////////////////
			//      RESPONSE FROM THE TABLE      //
			//////////////////////////////////////
	
	public static void getData(Session session) {
		ResultSet rs = session.execute("select *from demokeyspace.test"); 
		for(Row row : rs) {
			System.out.println(row.toString());
		}
	}
	
		///////////////////////////////////////
		//    Push into Cassandra Table      //
		//////////////////////////////////////
	public static void putData(Session session) {
		PreparedStatement preparedStatement = session.prepare("insert into demokeyspace.test (col1,col2,col3) values (?, ?, ?)");
		try {
			String one="ram";
			String two="esh";
			long three=45;
			BoundStatement boundStatement = preparedStatement.bind(one,two,three);
			ResultSet rs = session.execute(boundStatement);
			System.out.println(rs);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
