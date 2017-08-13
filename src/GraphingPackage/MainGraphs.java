package GraphingPackage;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.github.javafx.charts.zooming.ZoomManager;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.WritableImage;
import javafx.scene.input.ScrollEvent;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MainGraphs
{

	private Graph graphOne;
	
	private Graph graphTwo;
	
	private Graph graphThree;
	

	
	public MainGraphs(Graph one)
	{
		this.graphOne = one;
		
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
	
	
	@SuppressWarnings("restriction")
	public void displayGraphs()
	{
		Stage stage = new Stage();
		
		//BorderPane Pane = new BorderPane();
		
		final NumberAxis xAxis = new NumberAxis();
		final NumberAxis yAxis = new NumberAxis();
		
		// creating the chart
		LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);
		
		lineChart = graphOne.display();
		
		double start = System.currentTimeMillis();
		
		
		final StackPane stack = new StackPane();
		stack.getChildren().add(graphOne.display());

			
<<<<<<< HEAD
		

		
		//Pane.setRight(graphTwo.display());
		
		//Pane.setLeft(graphThree.display());
=======
		Pane.setCenter(lineChart);
>>>>>>> branch 'master' of https://github.com/Jkim-Hack/OSUGrapherEEngineering.git
		
		double end = System.currentTimeMillis();
		
		System.out.println(end - start);
		
		MenuBar topMen = new MenuBar();
		
		Menu file = new Menu("File"); 
		
		MenuItem screenshot = new MenuItem("Save Image...");
			
			screenshot.setOnAction((ActionEvent event) -> { 
				//WritableImage image = Pane.snapshot(new SnapshotParameters(), null);
				FileChooser fileChooser = new FileChooser();
				fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("png files (*.png)", "*.png"));
				fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("jpeg files (*.jpg)", "*.jpg"));
	            fileChooser.setTitle("Save Image");
	            File filech = fileChooser.showSaveDialog(stage);
	            if (filech != null) {
			   // try {
			     //   ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", filech);
			   // } catch (IOException e) {
			       
			    //}
	            }
			});
			


			
		file.getItems().add(screenshot);
<<<<<<< HEAD
		//Pane.setTop(topMenu);
=======
		Pane.setTop(topMen);
>>>>>>> branch 'master' of https://github.com/Jkim-Hack/OSUGrapherEEngineering.git
		topMen.getMenus().addAll(file);
		
			
		final Scene scene = new Scene(stack, 1100,800);
	
<<<<<<< HEAD
		//new ZoomManager(Pane, graphOne.display(), graphOne.series());
		
		
		
		//new ZoomManager(stack, graphTwo.display(), graphTwo.filler(), graphTwo.filler2(), graphTwo.series(),
			//graphOne.series1(), graphTwo.series2(), graphTwo.seriesdis(), graphTwo.series1dis(), graphTwo.series2dis());
=======
		if (graphOne.filler() == null || graphOne.filler() == null || graphOne.filler1() == null || 
				graphOne.filler2() == null || graphOne.series1() == null || graphOne.series2() == null || graphOne.series1dis() == null ||
				graphOne.seriesdis() == null || graphOne.series2dis() == null) {
			
			new ZoomManager(Pane, lineChart, graphOne.series());
			
		}
		
		else {
		
		new ZoomManager(Pane, lineChart, graphOne.filler(),graphOne.filler1(), graphOne.filler2(), graphOne.series(),
				graphOne.series1(), graphOne.series2(), graphOne.seriesdis(), graphOne.series1dis(), graphOne.series2dis());
		}
>>>>>>> branch 'master' of https://github.com/Jkim-Hack/OSUGrapherEEngineering.git
		
		new ZoomManager(stack, graphOne.display(), graphOne.series());
			
		stage.setScene(scene);
		stage.show();
		
		
	}
	
}
