package application;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

public class Controller
{
	@FXML
	private AnchorPane rootPane;
	public TextField username_textbox;
	public TextField password_textbox;
	public TextField partyname;
	public TextField partynameabbr;
	public TextField partysymbol;
	public TextField candidatename;
	public TextField candidateparty;
	public TextField cnic_textbox;
	public TextField name_textbox;
	
	public Label alreadyvoted;
	public Label wrongpasswordorusername;
	
	Voter voterr;
	
	public DBHandler db = new DBHandler();
	
	@FXML
	public void login_button_clicked(ActionEvent actionEvent)
	{
		try
		{
			AnchorPane pane = FXMLLoader.load(getClass().getResource("Login.fxml"));
			
			rootPane.getChildren().setAll(pane);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	@FXML
	public void login_button_submitted(ActionEvent actionEvent)
	{
		AdminLogin user = db.Login(username_textbox.getText(), password_textbox.getText());
		
		if(user.getUserName().compareTo("") == 0 )
		{
			wrongpasswordorusername.setText("Wrong Username or Password");
			
			return;
		}
		else
		{
			try
			{
				AnchorPane pane_1 = FXMLLoader.load(getClass().getResource("ElectionCommissionDashboard.fxml"));
				
				Node node = (Node) actionEvent.getSource();
			    Stage thisStage = (Stage) node.getScene().getWindow();
				
			    thisStage.setTitle("Election Commission Dashboard");
				
			    thisStage.setScene(new Scene(pane_1));
				
			    thisStage.show();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	@FXML
	public void gotovotingpage(ActionEvent actionEvent)
	{
		try
		{
			voterr = new Voter(cnic_textbox.getText(), name_textbox.getText());
			
			Boolean flag = db.FindVoter(voterr.GetCNIC());
			
			if(flag == true)
			{
				alreadyvoted.setText("You have already voted!");
				
				return;
			}
			else
			{
				AnchorPane pane_2 = FXMLLoader.load(getClass().getResource("VotingPage.fxml"));
				
				Node node = (Node) actionEvent.getSource();
				
			    Stage thisStage = (Stage) node.getScene().getWindow();
			    
			    thisStage.setTitle("Voting Page");
				
			    Candidate[] cndts = db.GetCandidatesForVotingPage();    
			    
			    int x1 = 100, x2 = 250, x3 = 450;
			    int y = 100;
			    
			    for(int i = 0; i < cndts.length; i++)
			    {
			    	String CndtName = cndts[i].getCandidateName();
			    	String CndtParty = cndts[i].getCandidateParty();
			    	
			    	Label C_NameLabel = new Label(CndtName);
				    C_NameLabel.setTranslateX(x1);
				    C_NameLabel.setTranslateY(y);
				    
				    Label C_PartyLabel = new Label(CndtParty);
				    C_PartyLabel.setTranslateX(x2);
				    C_PartyLabel.setTranslateY(y);
				    
				    Button VoteButton = new Button("Vote");
				    VoteButton.setTranslateX(x3);
				    VoteButton.setTranslateY(y);
				    
				    int j = i + 1;
				    
				    String ID = String.valueOf(j);
				    
				    VoteButton.setId(ID);
				    
				    VoteButton.setOnAction(new EventHandler<ActionEvent>()
				    {
			            @Override
			            public void handle(ActionEvent event)
			            {
			                Button btn = (Button) event.getSource();
			                
			                String ID = btn.getId();
			                
			                int idd = Integer.valueOf(ID);
			                
			                db.UpdateVoteCount(idd);
			                
			                db.AddVoter(voterr.GetCNIC(), voterr.GetVoterName());
			                
			                try
			                {
			                	AnchorPane pane_4 = FXMLLoader.load(getClass().getResource("CNICPageForVoter.fxml"));
								
								Node node = (Node) event.getSource();
							    Stage thisStage = (Stage) node.getScene().getWindow();
								
							    thisStage.setTitle("Voter Login Page");
								
							    thisStage.setScene(new Scene(pane_4));
								
							    thisStage.show();
			                }
			                catch(Exception e)
			        		{
			        			e.printStackTrace();
			        		}
			            }
			        });
				    
				    pane_2.getChildren().add(C_NameLabel);
				    pane_2.getChildren().add(C_PartyLabel);
				    pane_2.getChildren().add(VoteButton);
				    
				    y+=35;
			    }
			    
			    thisStage.setScene(new Scene(pane_2));
			    
			    thisStage.show();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	@FXML
	public void addnewparty(ActionEvent actionEvent)
	{
		try
		{
			AnchorPane pane_3 = FXMLLoader.load(getClass().getResource("AddNewParty.fxml"));
			
			Node node = (Node) actionEvent.getSource();
		    Stage thisStage = (Stage) node.getScene().getWindow();
			
		    thisStage.setTitle("Add New Party");
			
		    thisStage.setScene(new Scene(pane_3));
			
		    thisStage.show();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	@FXML
	public void addnewpartysubmit(ActionEvent actionEvent)
	{
		try
		{
			db.AddParty(partyname.getText(), partynameabbr.getText(), partysymbol.getText());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	@FXML
	public void addnewcandidate(ActionEvent actionEvent)
	{
		try
		{
			AnchorPane pane_4 = FXMLLoader.load(getClass().getResource("AddNewCandidate.fxml"));
			
			Node node = (Node) actionEvent.getSource();
		    Stage thisStage = (Stage) node.getScene().getWindow();
			
		    thisStage.setTitle("Add New Candidate");
			
		    thisStage.setScene(new Scene(pane_4));
			
		    thisStage.show();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	@FXML
	public void addnewcandidatesubmit(ActionEvent actionEvent)
	{
		try
		{
			db.AddCandidate(candidatename.getText(), candidateparty.getText());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	@FXML
	public void perofvotesparty(ActionEvent actionEvent)
	{
		try
		{
			AnchorPane pane_5 = FXMLLoader.load(getClass().getResource("PercOfVotesRecvByEachParty.fxml"));
			
			Node node = (Node) actionEvent.getSource();
			
		    Stage thisStage = (Stage) node.getScene().getWindow();
		    
		    thisStage.setTitle("Party Wise Results");
			
		    PartyWiseResult[] partywisereslts = db.GetPartyWiseResult();    
		    
		    int x1 = 100, x2 = 270;
		    int y = 100;
		    
		    double total_votes = 0.0;
		    
		    for(int k = 0; k < partywisereslts.length; k++)
		    {
		    	total_votes = total_votes + partywisereslts[k].GetVotesCount();
		    }
		    for(int i = 0; i < partywisereslts.length; i++)
		    {
		    	String PartyName = partywisereslts[i].GetParty();
		    	int partyvotes = partywisereslts[i].GetVotesCount();
		    	
		    	double perc = (partyvotes/total_votes)*100;
		    	
		    	String percentage = Double.toString(perc);
		    	
		    	Label Party_Name = new Label(PartyName);
		    	Party_Name.setTranslateX(x1);
		    	Party_Name.setTranslateY(y);
			    
			    Label Percen = new Label(percentage);
			    Percen.setTranslateX(x2);
			    Percen.setTranslateY(y);
			    
			    pane_5.getChildren().add(Party_Name);
			    pane_5.getChildren().add(Percen);
			    
			    y+=35;
		    }
		    
		    thisStage.setScene(new Scene(pane_5));
		    
		    thisStage.show();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	@FXML
	public void displayresults(ActionEvent actionEvent)
	{
		try
		{
			AnchorPane pane_6 = FXMLLoader.load(getClass().getResource("DisplayResult.fxml"));
			
			Node node = (Node) actionEvent.getSource();
			
		    Stage thisStage = (Stage) node.getScene().getWindow();
		    
		    thisStage.setTitle("Candidate Wise Results");
			
		    CandidateWiseResults[] candtwisereslts = db.GetCandidateWiseResult();    
		    
		    int x = 100, x2 = 270, x3 = 450;
		    int y = 100;
		    
		    for(int i = 0; i < candtwisereslts.length; i++)
		    {
		    	String PartyName = candtwisereslts[i].GetParty();
		    	int partyvotes = candtwisereslts[i].GetVotesCount();
		    	String candtname = candtwisereslts[i].GetCandidate();
		    	
		    	String noofvotes = Integer.toString(partyvotes);
		    	
		    	Label Party_Name = new Label(PartyName);
		    	Party_Name.setTranslateX(x);
		    	Party_Name.setTranslateY(y);
			    
		    	Label NameCandidate = new Label(candtname);
		    	NameCandidate.setTranslateX(x2);
		    	NameCandidate.setTranslateY(y);
		    	
			    Label votescount = new Label(noofvotes);
			    votescount.setTranslateX(x3);
			    votescount.setTranslateY(y);
			    
			    pane_6.getChildren().add(Party_Name);
			    pane_6.getChildren().add(NameCandidate);
			    pane_6.getChildren().add(votescount);
			    
			    y+=35;
		    }
		    
		    thisStage.setScene(new Scene(pane_6));
		    
		    thisStage.show();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@FXML
	public void GoToDashBoard(ActionEvent actionEvent)
	{
		try
		{
			AnchorPane pane_1 = FXMLLoader.load(getClass().getResource("ElectionCommissionDashboard.fxml"));
			
			Node node = (Node) actionEvent.getSource();
		    Stage thisStage = (Stage) node.getScene().getWindow();
			
		    thisStage.setTitle("Election Commission Dashboard");
			
		    thisStage.setScene(new Scene(pane_1));
			
		    thisStage.show();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@FXML
	public void GoToLoginPage(ActionEvent actionEvent)
	{
		try
		{
			AnchorPane pane_1 = FXMLLoader.load(getClass().getResource("Login.fxml"));
			
			Node node = (Node) actionEvent.getSource();
		    Stage thisStage = (Stage) node.getScene().getWindow();
			
		    thisStage.setTitle("Login");
			
		    thisStage.setScene(new Scene(pane_1));
			
		    thisStage.show();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@FXML
	public void GoToVoterLoginPage(ActionEvent actionEvent)
	{
		try
		{
			AnchorPane pane_1 = FXMLLoader.load(getClass().getResource("CNICPageForVoter.fxml"));
			
			Node node = (Node) actionEvent.getSource();
		    Stage thisStage = (Stage) node.getScene().getWindow();
			
		    thisStage.setTitle("Voter Login Page");
			
		    thisStage.setScene(new Scene(pane_1));
			
		    thisStage.show();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@FXML
	public void howtovote(ActionEvent actionEvent)
	{
		try
		{
			AnchorPane pane_1 = FXMLLoader.load(getClass().getResource("HowToVote.fxml"));
			
			Node node = (Node) actionEvent.getSource();
		    Stage thisStage = (Stage) node.getScene().getWindow();
			
		    thisStage.setTitle("How to Vote");
			
		    thisStage.setScene(new Scene(pane_1));
			
		    thisStage.show();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@FXML
	public void voterswhovoted(ActionEvent actionEvent)
	{
		try
		{
			AnchorPane pane_5 = FXMLLoader.load(getClass().getResource("voterswhovoted.fxml"));
			
			Node node = (Node) actionEvent.getSource();
			
		    Stage thisStage = (Stage) node.getScene().getWindow();
		    
		    thisStage.setTitle("Voters List Who Voted");
			
		    Voter[] voterslist = db.GetVotersList();    
		    
		    int x1 = 100, x2 = 250;
		    int y = 100;
		    
		    double total_votes = 0.0;
		    
		    for(int i = 0; i < voterslist.length; i++)
		    {
		    	String cnic_ = voterslist[i].GetCNIC();
		    	String name_ = voterslist[i].GetVoterName();
		    	
		    	Label cnic = new Label(cnic_);
		    	cnic.setTranslateX(x1);
		    	cnic.setTranslateY(y);
			    
			    Label name = new Label(name_);
			    name.setTranslateX(x2);
			    name.setTranslateY(y);
			    
			    pane_5.getChildren().add(cnic);
			    pane_5.getChildren().add(name);
			    
			    y+=35;
		    }
		    
		    thisStage.setScene(new Scene(pane_5));
		    
		    thisStage.show();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
