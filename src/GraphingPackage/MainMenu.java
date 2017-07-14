package GraphingPackage;

 
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.jogamp.newt.event.KeyEvent;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;


public class MainMenu extends Application 
{
 
	//Instance Variables
	
	public File fileName;
	
	
	
	public FileChoose fileChooser = new FileChoose();
		
	

	
    @Override public void start(Stage primaryStage)
    {	
    	
    	
    	//Display Panes
        VBox topContainer = new VBox();
        VBox midinserts = new VBox();
        VBox graphVvC = new VBox();
        //VBox zeTable = new VBox();
        
        Label labelMass = new Label("Insert Mass: ");
        labelMass.setFont(Font.font ("Segoe UI", 14));
        
        Label saveLabel = new Label(" Saved!");
        saveLabel.setVisible(false);
        
       /* TableView selectGraph = new TableView();
        selectGraph.setEditable(false);
        
        TableColumn firstCol = new TableColumn("Top Graph");
        TableColumn	secondCol = new TableColumn("Right Graph");
        TableColumn thirdCol = new TableColumn("Left Graph");*/
        
        
        //Textbox for File 
        
        Label fileLabel = new Label("Choose File");
        TextField fileField = new TextField();
        
        fileField.setOnMouseClicked(e -> {
		
				
					
					fileChooser.chooseFile(primaryStage);
					if(fileChooser.getFileName() != null)
					{	
						fileName = fileChooser.getFileName();
						fileField.setText(fileName.getName());
					}
					else
					{
						System.out.println("Need file");
					}
				//TODO ADD POP UP WINDOW ASKING TO SUBMIT FILE
			
		} );
       
        
        //Creates Drop Down Boxes 
        Label topLabel = new Label("Top Pane");
        ChoiceBox<Graph> box1 = new ChoiceBox<Graph>();
        
        Label botLeft = new Label("Bottom Left Pane");
        ChoiceBox<Graph> box2 = new ChoiceBox<Graph>();
        
        Label botRight = new Label("Bottom Right Pane");
        ChoiceBox<Graph> box3 = new ChoiceBox<Graph>();
        
       
        
       
        //graphs.add(new DischargeGraph(fileName, value));
        
       
        

      
        
        
        //Mass textfield
       
        TextField insertMass = new TextField();
        Button pseudoSave = new Button("Apply");
        pseudoSave.addEventHandler(ActionEvent.ACTION, (e) -> isDouble(insertMass, insertMass.getText()));
        pseudoSave.addEventHandler(ActionEvent.ACTION , ActionEvent -> 
        {
     
        	saveLabel.setVisible(true);
        	PauseTransition visiblePause = new PauseTransition(
        	        Duration.seconds(10)
        	);
        	visiblePause.setOnFinished(
        	        event -> saveLabel.setVisible(false)
        	);
        	visiblePause.play();
        	
        	double mass = toMassDouble(insertMass.getText());
        	
        	 List<Graph> graphs = new ArrayList<Graph>();
             graphs.add(new VoltageVsChrgeCapacity(fileName, mass));
             box1.getItems().addAll(graphs);
             box2.getItems().addAll(graphs);
             box3.getItems().addAll(graphs);  
        
        });
        
       
        	
        //Menu drop down bar
        MenuBar ddMenu = new MenuBar();
        Menu files = new Menu("File");
        MenuItem openFile = new MenuItem("Open File...");	
       
       
        
       openFile.setOnAction((ActionEvent event) -> {
        	
            //File Chooser class
        	
        	fileChooser.chooseFile(primaryStage);
            fileName = fileChooser.getFileName();
            fileField.setText(fileName.getName());
           
            //File Chooser class
       });
       
       files.getItems().add(openFile);
       ddMenu.getMenus().add(files);
       
       
        
    	//Button for the VvsCC window created
    	
    	Button createGraph = new Button();
    	createGraph.setText("Create Graph");
    	createGraph.setFont(Font.font ("Segoe UI", 16));
    	createGraph.setOnAction(e -> 
    	{
       
    		MainGraphs graph = new MainGraphs(box1.getValue());
    	
    		
    		graph.displayGraphs();
    		
        });
    	
    	
    	
    	
    	
       //The primary master window is created
       
       BorderPane pane = new BorderPane();
       
       	pane.setRight(graphVvC); 
       	graphVvC.setPadding(new Insets(20, 20, 20, 20));
       	graphVvC.setAlignment(Pos.BOTTOM_RIGHT);
       	graphVvC.getChildren().addAll(topLabel,box1, botLeft, box2, botRight, box3,createGraph);
       	createGraph.setPrefWidth(250);
       	createGraph.setPrefHeight(70);
       	
       	//pane.setRight(zeTable);
       	//zeTable.getChildren().addAll(topLabel,botLeft,box2,botRight,box3);
       	//zeTable.setAlignment(Pos.TOP_RIGHT);
       	
       	
       	pane.setTop(topContainer);
       	topContainer.getChildren().add(ddMenu);
       	
       	pane.setLeft(midinserts);
       	midinserts.setPadding(new Insets(20, 20, 20, 20));
       	midinserts.getChildren().addAll(fileLabel,fileField,labelMass ,insertMass, pseudoSave, saveLabel);
       	
       Scene scene = new Scene(pane, 800, 600);
       
       Image icon = new Image(new File("favicon.png").toURI().toString());
       
        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("Grapher");
        primaryStage.setScene(scene);
        primaryStage.show();
         
        primaryStage.setOnCloseRequest(e -> Platform.exit());
        
        
      
        
    }
    
    
    
    
    
    
    private boolean isDouble(TextField input, String mass)
    {
    	
    	try
    	{
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

    
    public double toMassDouble(String mass)
    {
    	double dataMass = Double.parseDouble(mass);
    	return dataMass;
    }
  
    
    
	public static void main(String[] args)
    {
	
        launch(args);
    }
}
 
