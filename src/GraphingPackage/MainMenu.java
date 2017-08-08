package GraphingPackage;

 
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hslf.util.SystemTimeUtils;

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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
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
        VBox GraphChoose = new VBox();
        
        
        midinserts.setSpacing(10);
        GraphChoose.setSpacing(20);
        
        
        Label labelMass = new Label("Insert Mass in Grams (g): ");
        labelMass.setFont(Font.font ("Segoe UI", 12));
        
        Label saveLabel = new Label(" Saved!");
        saveLabel.setVisible(false);
        
        //Textbox for File 
        
        
       
        
        //Creates Drop Down Boxes 
        Label topLabel = new Label("Bottom Pane 							    ");
        ChoiceBox<Graph> box1 = new ChoiceBox<Graph>();
        
        Label botLeft = new Label("Top Left Pane 						    	    ");
        ChoiceBox<Graph> box2 = new ChoiceBox<Graph>();
        
        Label botRight = new Label("Top Right Pane 						    ");
        ChoiceBox<Graph> box3 = new ChoiceBox<Graph>();
        
       
        
       
        
        
       
        Label fileLabel = new Label("Chosen File:");
        TextField fileField = new TextField("File -> Open File...");
        
        Label SheetField = new Label("Enter Amount of Channel Sheets: ");
        TextField SheetText = new TextField();
        
        Label StatField = new Label("Enter Amount of Statistic Sheets");
        TextField StatText = new TextField();
        
    
        
        
        
        Label Cycles = new Label("Enter Three Cycles: ");
        TextField insertCycle1 = new TextField();
        TextField insertCycle2 = new TextField();
        TextField insertCycle3 = new TextField();
        
       
        //Mass textfield
       
        TextField insertMass = new TextField();
        Button pseudoSave = new Button("Apply");
        
        
        
        pseudoSave.addEventHandler(ActionEvent.ACTION , ActionEvent -> 
        {
        	
        	
        	saveLabel.setVisible(false);
        	PauseTransition visiblePause = new PauseTransition(
        	        Duration.seconds(2)
        	);
        	visiblePause.setOnFinished(
        	        event -> saveLabel.setVisible(true)
        	);
        	visiblePause.play();
        	
        	

        	 long start = System.currentTimeMillis();
        	 isDouble(insertMass, insertMass.getText());

        	 int Channel = toSheetInt(SheetText.getText());
             int Stat = toSheetInt(StatText.getText());
        	 
        	 double cycleOne = toCycleDouble(insertCycle1.getText());
             double cycleTwo = toCycleDouble(insertCycle2.getText());
             double cycleThree = toCycleDouble(insertCycle3.getText());

             
        	double mass = toMassDouble(insertMass.getText());
        	
        	String ChargeCap = "Voltage vs Charge Capacity";
        	String dischargeTitle = "Voltage vs Discharge Capacity";
        	
        	 List<Graph> graphs = new ArrayList<Graph>();
             graphs.add(new VoltageVsChrgeCapacity(fileName, mass, ChargeCap ,
            		 cycleOne,cycleTwo,cycleThree, Channel, Stat));
             graphs.add(new DischargeGraph(fileName, mass, dischargeTitle,
            		 cycleOne,cycleTwo,cycleThree, Channel, Stat));
             graphs.add(new CycleNumberDC(fileName, mass, "Discharge Capacity vs Cycle Number",
            		 cycleOne,cycleTwo,cycleThree, Channel, Stat));
             graphs.add(new CoulombicEff(fileName, mass, "Coulombic Efficiency vs Cycle Number",
            		 cycleOne,cycleTwo,cycleThree, Channel, Stat));
             
             box1.getItems().clear();
             box2.getItems().clear();
             box3.getItems().clear();  
             
             box1.getItems().addAll(graphs);
             box2.getItems().addAll(graphs);
             box3.getItems().addAll(graphs);  
           
             
             long end = System.currentTimeMillis();
             System.out.println(end - start);
        });
                
       
        	
        //Menu drop down bar
        MenuBar ddMenu = new MenuBar();
        Menu files = new Menu("File");
        MenuItem openFile = new MenuItem("Open File...");	
       
        
       openFile.setOnAction((ActionEvent event) -> {
        	
            //File Chooser class
    	   
        	FileChoose file;
        	file = new FileChoose();
            file.chooseFile(primaryStage);
            fileName = file.getFileName();
            fileField.setText(fileName.getName());

            //File Chooser class
            
       });
       
       
       fileField.setEditable(false);
       
       files.getItems().add(openFile);
      
       Menu help = new Menu("Help");
       Menu about = new Menu("Contact");
       MenuItem htu = new MenuItem("How to Use");
       
       about.getItems().addAll( new MenuItem("Main Developer - John Kim - johnkim1108@gmail.com"),
    		   					new MenuItem("Helper - Chris Yao - chrisyao2@gmail.com"));
       
       help.getItems().addAll(htu, about);
       
       htu.setOnAction((ActionEvent event) -> {
    	  
    	   File howtouse = new File("HowToUse.txt");
    	   try {
			Desktop.getDesktop().open(howtouse);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
       });
       
       ddMenu.getMenus().addAll(files, help);
       
    	//Button for the graph window created
    	
    	Button createGraph = new Button();
    	createGraph.setText("Create Graphs");
    	createGraph.setFont(Font.font ("Segoe UI", 16));
    	createGraph.setOnAction((ActionEvent event) -> 
    	{
    		
    		MainGraphs graph = new MainGraphs(box1.getValue(), box2.getValue(), box3.getValue());
    		graph.displayGraphs();
    		
        });
    	
    	
    	
    	
    	
       //The primary master window is created
       
    	BorderPane pane = new BorderPane();
       
       	pane.setBottom(graphVvC); 
       	graphVvC.setPadding(new Insets(-87,20,400,330));
       	graphVvC.getChildren().addAll(createGraph);
       	createGraph.setPrefWidth(250);
       	createGraph.setPrefHeight(70);
       
       	
       	pane.setRight(GraphChoose);
       	GraphChoose.setPadding(new Insets(20,20,20,20));
       	GraphChoose.setAlignment(Pos.TOP_RIGHT);
       	GraphChoose.getChildren().addAll(topLabel,box1, botLeft, box2, botRight, box3);
       	box1.setPrefWidth(250);
       	box2.setPrefWidth(250);
       	box3.setPrefWidth(250);
       
       	pane.setTop(topContainer);
       	topContainer.getChildren().add(ddMenu);
       	
       	pane.setLeft(midinserts);
       	midinserts.setPadding(new Insets(20, 20, 20, 20));
       	midinserts.getChildren().addAll(fileLabel, fileField, labelMass ,insertMass, Cycles, insertCycle1, insertCycle2, insertCycle3, 
       			SheetField, SheetText, StatField, StatText, pseudoSave, saveLabel);

       	
       Scene scene = new Scene(pane, 600, 497);
       
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
    
    public int toSheetInt(String number)
    {
    	int sheet = Integer.parseInt(number);
    	return sheet;
    }
 
    public double toCycleDouble(String Cycle)
    {
    	double AllCycle = Double.parseDouble(Cycle);
    	return AllCycle;
    }
    
    
	public static void main(String[] args)
    {
	
        launch(args);
    }
}
 
