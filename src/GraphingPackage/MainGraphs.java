package GraphingPackage;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
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
		
		BorderPane borderPane = new BorderPane();
		
		double start = System.currentTimeMillis();
			
		borderPane.setTop(graphOne.display());
	
		borderPane.setLeft(graphTwo.display());
		
		borderPane.setRight(graphThree.display());
		
		double end = System.currentTimeMillis();
		
		System.out.println(end - start);
		
		Scene scene = new Scene(borderPane, 1100,800);
		
		stage.setScene(scene);
		
		stage.show();
		
		
	}
	
	
	
}
