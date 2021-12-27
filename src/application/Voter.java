package application;

public class Voter
{
	private String cnic;
	private String name;
	
	public Voter()
	{
		this.cnic = "";
		this.name = "";
	}
	public Voter(String cnic, String name)
	{
		this.cnic = cnic;
		this.name = name;
	}
	public String GetCNIC()
	{
		return cnic;
	}
	public String GetVoterName()
	{
		return name;
	}
}
