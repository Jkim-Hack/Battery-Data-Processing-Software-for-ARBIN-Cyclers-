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
		stage.setMinWidth(500);
		stage.setMinHeight(350);
		
		
		Label messageLabel = new Label(message);
		
		Button exitButton = new Button("Close");
		
		exitButton.setOnAction(e ->
		{
			stage.close();
		});
		
		VBox pane = new VBox();
		pane.getChildren().addAll(messageLabel,exitButton);
		pane.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(pane);
		
		stage.setScene(scene);
		stage.show();
	}
	
}
