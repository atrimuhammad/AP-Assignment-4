package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBHandler
{
	Connection1 connclass = new Connection1();
	
	Connection con = connclass.getConnection();
	
	public AdminLogin Login(String username, String password) 
	{
		AdminLogin user = new AdminLogin();
		
		PreparedStatement stmt;
		
		ResultSet rs;
		
		try
		{
			stmt = con.prepareStatement("select * from login where username=? and password=?");
			
			stmt.setString(1, username);
			stmt.setString(2, password);
			
			rs = stmt.executeQuery();
			
			if(rs.next())
			{
				user = new AdminLogin(rs.getString(1), rs.getString(2));
			}
		}
		catch (SQLException e)
		{
			System.out.println("Something went wrong while checking if account exists");
		}
		
		return user;
	}
	public void AddParty(String partyname, String partyAbbreviation, String partysymbol)
	{
		PreparedStatement stmt;
		
		try
		{
			stmt = con.prepareStatement("insert into parties(partyname, partynameabbr, partysymbol) values(?, ?, ?)");
			
			stmt.setString(1, partyname);
			stmt.setString(2, partyAbbreviation);
			stmt.setString(3, partysymbol);
			
			stmt.executeUpdate();
		}
		catch (SQLException e)
		{
			System.out.println("Something went wrong while adding new party");
		}
	}
	public void AddCandidate(String candidateName, String candidateParty)
	{
		PreparedStatement stmt;
		PreparedStatement stmt1;
		
		try
		{
			stmt = con.prepareStatement("insert into candidates(candidateName, candidateParty) values(?, ?)");
			stmt1 = con.prepareStatement("insert into voting(candidatename, partyname, votescount) values(?, ?, ?)");
					
			stmt.setString(1, candidateName);
			stmt.setString(2, candidateParty);
			
			stmt1.setString(1, candidateName);
			stmt1.setString(2, candidateParty);
			stmt1.setInt(3, 0);
			
			stmt.executeUpdate();
			stmt1.executeUpdate();
		}
		catch (SQLException e)
		{
			System.out.println("Something went wrong while adding new Candidate");
		}
	}
	
	public void AddVoter(String cnic, String name)
	{
		PreparedStatement stmt;
		
		try
		{
			stmt = con.prepareStatement("insert into voter(votercnic, votername) values(?, ?)");
			
			stmt.setString(1, cnic);
			stmt.setString(2, name);
			
			stmt.executeUpdate();
		}
		catch (SQLException e)
		{
			System.out.println("Something went wrong while adding voter information");
		}
	}
	public Candidate[] GetCandidatesForVotingPage()
	{
		PreparedStatement stmt, stmt1;
		
		ResultSet rs, rs1;
		
		Candidate[] candidates = new Candidate[100];
		
		try
		{
			stmt = con.prepareStatement("select candidateName, candidateParty from candidates");
			
			rs = stmt.executeQuery();
			
			int size = 0;
			
			while(rs.next())
			{
				size++;
			}
			
			candidates = new Candidate[size];
			
			stmt1 = con.prepareStatement("select candidateName, candidateParty from candidates");
			
			rs1 = stmt1.executeQuery();

			int j = 0;
			
			while(rs1.next())
			{
				candidates[j] = new Candidate(rs1.getString("candidateName"), rs1.getString("candidateParty"), size);
				
				j++;
	        }
			
			return candidates;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	
		return null;
	}
	public void UpdateVoteCount(int voteid)
	{
		PreparedStatement stmt, stmt1;
		ResultSet rs;
		
		try
		{
			stmt = con.prepareStatement("select votescount from voting where voteid = ?");
			
			stmt.setInt(1, voteid);
			
			rs = stmt.executeQuery();
			
			int votescount;
			
			rs.next();
			
			votescount = rs.getInt(1);
			
			votescount++;
			
			stmt1 = con.prepareStatement("update voting set votescount = ? where voteid = ?");
			
			stmt1.setInt(1, votescount);
			stmt1.setInt(2, voteid);
			
			stmt1.executeUpdate();
		}
		catch (SQLException e)
		{
			System.out.println("Something went wrong while adding voter information");
		}
	}
	public Boolean FindVoter(String cnic)
	{
		PreparedStatement stmt;
		ResultSet rs;
		
		try
		{
			stmt = con.prepareStatement("select votercnic, votername from voter where votercnic = ?");
			
			stmt.setString(1, cnic);
			
			rs = stmt.executeQuery();
			
			if(rs.next())
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		catch (SQLException e)
		{
			System.out.println("Something went wrong while adding voter information");
		}
		
		return null;
	}
	
	public PartyWiseResult[] GetPartyWiseResult()
	{
		PreparedStatement stmt, stmt1;
		
		ResultSet rs, rs1;
		
		PartyWiseResult[] prtywiseresult = new PartyWiseResult[100];
		
		try
		{
			stmt = con.prepareStatement("select partyname, votescount from voting");
			
			rs = stmt.executeQuery();
			
			int size = 0;
			
			while(rs.next())
			{
				size++;
			}
			
			prtywiseresult = new PartyWiseResult[size];
			
			stmt1 = con.prepareStatement("select partyname, votescount from voting");
			
			rs1 = stmt1.executeQuery();

			int j = 0;
			
			while(rs1.next())
			{
				prtywiseresult[j] = new PartyWiseResult(rs1.getString("partyname"), rs1.getInt("votescount"));
				
				j++;
	        }
			
			return prtywiseresult;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	
		return null;
	}
	public CandidateWiseResults[] GetCandidateWiseResult()
	{
		PreparedStatement stmt, stmt1;
		
		ResultSet rs, rs1;
		
		CandidateWiseResults[] candtwiseresult = new CandidateWiseResults[100];
		
		try
		{
			stmt = con.prepareStatement("select candidatename, partyname, votescount from voting");
			
			rs = stmt.executeQuery();
			
			int size = 0;
			
			while(rs.next())
			{
				size++;
			}
			
			candtwiseresult = new CandidateWiseResults[size];
			
			stmt1 = con.prepareStatement("select candidatename, partyname, votescount from voting");
			
			rs1 = stmt1.executeQuery();

			int j = 0;
			
			while(rs1.next())
			{
				candtwiseresult[j] = new CandidateWiseResults(rs1.getString("candidatename"), rs1.getString("partyname"), rs1.getInt("votescount"));
				
				j++;
	        }
			
			return candtwiseresult;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	
		return null;
	}
	public Voter[] GetVotersList()
	{
		PreparedStatement stmt, stmt1;
		
		ResultSet rs, rs1;
		
		Voter[] voterslist = new Voter[100];
		
		try
		{
			stmt = con.prepareStatement("select votercnic, votername from voter");
			
			rs = stmt.executeQuery();
			
			int size = 0;
			
			while(rs.next())
			{
				size++;
			}
			
			voterslist = new Voter[size];
			
			stmt1 = con.prepareStatement("select votercnic, votername from voter");
			
			rs1 = stmt1.executeQuery();

			int j = 0;
			
			while(rs1.next())
			{
				voterslist[j] = new Voter(rs1.getString("votercnic"), rs1.getString("votername"));
				
				j++;
	        }
			
			return voterslist;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	
		return null;
	}
}
