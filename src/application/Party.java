package application;

public class Party
{
	private String partyname;
	private String partyAbbreviation;
	private String partysymbol;
	
	public Party()
	{
		this.partyname = "";
		this.partyAbbreviation = "";
		this.partysymbol = "";
	}
	public Party(String partyname, String partyAbbreviation, String partysymbol)
	{
		this.partyname = partyname;
		this.partyAbbreviation = partyAbbreviation;
		this.partysymbol = partysymbol;
	}
}
