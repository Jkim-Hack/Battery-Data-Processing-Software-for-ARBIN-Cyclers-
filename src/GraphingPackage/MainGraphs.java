package GraphingPackage;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MainGraphs
{

	private Graph graphOne;
	
	private Graph graphTwo;
	
	private Graph graphThree;
	

	
	public MainGraphs(Graph one, Graph two, Graph three)
	{
		this.graphOne = one;
		this.graphTwo = two;
		this.graphThree = three;
	}
	

	public Graph getGraphOne() 
	{
		return graphOne;
	}

	public void setGraphOne(Graph graphOne) 
	{
		this.graphOne = graphOne;
	}

	public Graph getGraphTwo() 
	{
		return graphTwo;
	}

	public void setGraphTwo(Graph graphTwo) 
	{
		this.graphTwo = graphTwo;
	}

	public Graph getGraphThree() 
	{
		return graphThree;
	}

	public void setGraphThree(Graph graphThree)
	{
		this.graphThree = graphThree;
	}
	
	
	public void displayGraphs()
	{
		Stage stage = new Stage();
	
		VBox topMenu = new VBox();
		
		BorderPane borderPane = new BorderPane();
		
		double start = System.currentTimeMillis();
			
		borderPane.setBottom(graphOne.display());
		
		borderPane.setLeft(graphTwo.display());
		
		borderPane.setRight(graphThree.display());
		
		double end = System.currentTimeMillis();
		
		System.out.println(end - start);
		
		MenuBar topMen = new MenuBar();
		
		Menu file = new Menu("File"); 
		
		MenuItem screenshot = new MenuItem("Save Image...");
			
			screenshot.setOnAction((ActionEvent event) -> { 
				WritableImage image = borderPane.snapshot(new SnapshotParameters(), null);
				FileChooser fileChooser = new FileChooser();
				fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("png files (*.png)", "*.png"));
				fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("jpeg files (*.jpg)", "*.jpg"));
	            fileChooser.setTitle("Save Image");
	            File filech = fileChooser.showSaveDialog(stage);
	            if (filech != null) {
			    try {
			        ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", filech);
			    } catch (IOException e) {
			       
			    }
	            }
			});
		
		file.getItems().add(screenshot);
		topMen.getMenus().addAll(file);
			
		borderPane.setTop(topMenu);
		topMenu.setAlignment(Pos.TOP_LEFT);
		topMenu.getChildren().addAll(topMen);
			
		Scene scene = new Scene(borderPane, 1100,800);
		scene.getStylesheets().add("GraphingPackage/Chart.css");
		stage.setScene(scene);
		
		stage.show();
		
		
	}
	
	
	
}
