package GraphingPackage;

 
import java.io.File;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.xmlbeans.impl.regex.ParseException;



import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.Duration;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;


public class MainMenu extends Application 
{
 
	
	
	public File fileName;
	public double value;
		
	

	
    @Override public void start(Stage primaryStage)
    {	
        VBox topContainer = new VBox();
        VBox midinserts = new VBox();
        VBox graphVvC = new VBox();
        
        Label labelMass = new Label("Insert Mass (g) : ");
        labelMass.setFont(Font.font ("Segoe UI", 14));
        
        Label saveLabel = new Label(" Saved!");
        saveLabel.setVisible(false);
        
        TextField insertMass = new TextField();
        Button pseudoSave = new Button("Save Mass...");
        pseudoSave.addEventHandler(ActionEvent.ACTION, (e) -> isDouble(insertMass, insertMass.getText()));
        pseudoSave.addEventHandler(ActionEvent.ACTION , ActionEvent -> {
     
        	saveLabel.setVisible(true);
        	PauseTransition visiblePause = new PauseTransition(
        	        Duration.seconds(10)
        	);
        	visiblePause.setOnFinished(
        	        event -> saveLabel.setVisible(false)
        	);
        	visiblePause.play();
        
        });
        
       
        	
        //Menu drop down bar
        MenuBar ddMenu = new MenuBar();
        Menu files = new Menu("File");
        MenuItem openFile = new MenuItem("Open File...");	
       
       
        
       openFile.setOnAction((ActionEvent event) -> {
        	
            //File Chooser class
    	   
        	FileChoose file;
        	file = new FileChoose();
            file.start(primaryStage);
            fileName = file.getFileName();
            
            //File Chooser class
       });
       
       files.getItems().add(openFile);
       ddMenu.getMenus().add(files);
        
    	//Button for the VvsCC window created
    	
    	Button createGraph = new Button();
    	createGraph.setText("Create Graph");
    	createGraph.setFont(Font.font ("Segoe UI", 16));
    	createGraph.setOnAction((ActionEvent event) -> {
       
    		double value = Double.parseDouble(insertMass.getText());
        //Voltage vs Charge Capacity class
        	VoltageVsChrgeCapacity VvCgraph;
        	VvCgraph = new VoltageVsChrgeCapacity(fileName, value);	
        //Voltage vs Charge Capacity class
        	
        /*Discharge class
        	//DischargeGraph dischrge;
        	//dischrge = new DischargeGraph(fileName, value);
         Discharge class */
        
        		 		
        });
       //The primary master window is created
       
       BorderPane pane = new BorderPane();
       
       	pane.setRight(graphVvC); 
       	graphVvC.setPadding(new Insets(20, 20, 20, 20));
       	graphVvC.setAlignment(Pos.BOTTOM_RIGHT);
       	graphVvC.getChildren().addAll(createGraph);
       	createGraph.setPrefWidth(250);
       	createGraph.setPrefHeight(70);
       	
       	
       	
       	pane.setTop(topContainer);
       	topContainer.getChildren().add(ddMenu);
       	
       	pane.setLeft(midinserts);
       	midinserts.setPadding(new Insets(20, 20, 20, 20));
       	midinserts.getChildren().addAll(labelMass ,insertMass, pseudoSave, saveLabel);
       	
       Scene scene = new Scene(pane, 800, 600);
       
       Image icon = new Image(new File("favicon.png").toURI().toString());
       
        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("Grapher");
        primaryStage.setScene(scene);
        primaryStage.show();
         
        primaryStage.setOnCloseRequest(e -> Platform.exit());
        
    }
    
    private boolean isDouble(TextField input, String mass){
    	try{
    		double dataMass = Double.parseDouble(input.getText());
    		System.out.println("Saved!");
    		return true;
    		
    	} catch(NumberFormatException e){
    		System.out.println(mass + " is not a number");
    		Label notNum = new Label(mass + " is not a number");
    		notNum.setVisible(true);
    		return false;
    	}
    }

  
    
	public static void main(String[] args)
    {
	
        launch(args);
    }
}
 
