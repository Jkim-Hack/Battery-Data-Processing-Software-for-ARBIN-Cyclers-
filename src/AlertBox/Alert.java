package AlertBox;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Alert 
{
	
	public void displayBox(String message)
	{
		Stage stage = new Stage();
		
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setMinWidth(230);
		stage.setMinHeight(150);
		
		
		Label messageLabel = new Label(message);
		
		
		
		VBox pane = new VBox();
		pane.getChildren().addAll(messageLabel);
		pane.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(pane);
		stage.setTitle("Error");
		stage.setScene(scene);
		stage.show();
	}
	
}
