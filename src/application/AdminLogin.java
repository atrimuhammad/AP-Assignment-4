package application;

public class AdminLogin
{
	private String username;
	private String password;
	
	public AdminLogin()
	{
		this.username = "";
		this.password = "";
	}
	public AdminLogin(String username, String password)
	{
		this.username = username;
		this.password = password;
	}
	public String getUserName()
	{
		return this.username;
	}
}
