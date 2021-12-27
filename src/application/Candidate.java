package application;

public class Candidate
{
	private String candidateName;
	private String candidateParty;
	private int size;
	
	public Candidate()
	{
		this.candidateName = "";
		this.candidateParty = "";
		size = 0;
	}
	public Candidate(String candidateName, String candidateParty, int size)
	{
		this.candidateName = candidateName;
		this.candidateParty = candidateParty;
		size = size;
	}
	public String getCandidateName()
	{
		return candidateName;
	}
	public String getCandidateParty()
	{
		return candidateParty;
	}
	public int getCandidateArraySize()
	{
		return size;
	}
}
