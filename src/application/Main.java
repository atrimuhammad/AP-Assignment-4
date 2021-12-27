package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.*;

public class Main extends Application
{
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		try
		{
			Parent root = FXMLLoader.load(getClass().getResource("CNICPageForVoter.fxml"));
			
			primaryStage.setTitle("Electronic Voting Machine System");
			
			primaryStage.setScene(new Scene(root, 600, 475));
			
			primaryStage.show();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		launch(args);
	}
}
