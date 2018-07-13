package ConnectingToDBUsingJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DataBaseConnection {
	
	public static void main(String[] args) {
		
		//1........Add JDBC Jars
		
		//2........Creating a variable for connection
		String connectionURL = "jdbc:sqlserver://localhost:1433;" +  
		"databaseName=DataBaseName;integratedSecurity=true;";
		//1433 --> MicrosoftSql server configuration manager -->IP Address --> TCP Port number
		
		//3.........Declare the JDBC Objects
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			//4.......Establish the connection
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionURL);
			System.out.println("Connection Established");
			
			//5........Create and Execute the SQL Statement that returns some Data
			String SQL = "SELECT * from JDBC_TEST";
			stmt = con.createStatement();
			rs= stmt.executeQuery(SQL);
			
			//Iterate through the data in the result set and display it
			while(rs.next()) {
				
				System.out.println(rs.getString(1)+ "   "+ rs.getString(2) + "    "+ rs.getString(3));
				
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		/*6-------> Set the RunConfiguration before running steps --> Run-> Run Configurations-> (x)Arguments-> VM Arguments->
		 *  Type the text ----> "-Djava.library.path= "/java/java-version/bin--path"
		*/
	}

}
