package application;

public class PartyWiseResult
{
	private String party;
	private int votes;
	
	public PartyWiseResult()
	{
		this.party = "";
		this.votes = 0;
	}
	public PartyWiseResult(String party, int votes)
	{
		this.party = party;
		this.votes = votes;
	}
	public String GetParty()
	{
		return party;
	}
	public int GetVotesCount()
	{
		return votes;
	}
}
