package application;

public class CandidateWiseResults
{
	private String candidate;
	private String party;
	private int votes;
	
	public CandidateWiseResults()
	{
		this.candidate = "";
		this.party = "";
		this.votes = 0;
	}
	public CandidateWiseResults(String candidate, String party, int votes)
	{
		this.candidate = candidate;
		this.party = party;
		this.votes = votes;
	}
	public String GetCandidate()
	{
		return candidate;
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
