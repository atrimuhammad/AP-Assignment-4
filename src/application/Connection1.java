package application;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connection1
{
	public Connection con;
	
	public Connection getConnection()
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/evm", "root", "1234");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return con;
	}
}
