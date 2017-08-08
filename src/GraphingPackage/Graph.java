package GraphingPackage;

import java.io.File;
import java.util.List;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;

public class Graph
{

	protected File fileName;
	protected double DataMass;
	protected double mass;
	protected String title;
	protected ExcelReader excelReader;
	protected List<Data> electricityData;
	protected List<StatData> electricityData1;
	protected double cycleOne;
	protected double cycleTwo;
	protected double cycleThree;
	protected int Channel;
	protected int Stat;
	
	
	public Graph(File fileName, double value, String title, double cycleOne, double cycleTwo, double cycleThree)
	{
		excelReader = null;
		
		try
		{
			excelReader = new ExcelReader(fileName, cycleOne, cycleTwo, cycleThree, Channel, Stat);
			electricityData = excelReader.getData().electrictyData;
			electricityData1 = excelReader.getData().electrictyData1;
		}
		
		catch(Exception ioException)
		{
			ioException.printStackTrace();
		}
		
		this.mass = value;
		this.title = title;
		this.cycleOne = cycleOne;
		this.cycleTwo = cycleTwo;
		this.cycleThree = cycleThree;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getValue()
	{
		return mass;
	}

	public void setValue(double value) {
		this.mass = value;
	}

	public File getFile()
	{
		return fileName;
	}
	
	public void setFile(File fileName)
	{
		this.fileName = fileName;
		
	}
	
	public double getCycleOne()
	{
		return cycleOne;
	}

	public void setCycleOne(double cycleOne) 
	{
		this.cycleOne = cycleOne;
	}

	public double getCycleTwo()
	{
		return cycleTwo;
	}

	public void setCycleTwo(double cycleTwo) 
	{
		this.cycleTwo = cycleTwo;
	}

	public double getCycleThree()
	{
		return cycleThree;
	}

	public void setCycleThree(double cycleThree)
	{
		this.cycleThree = cycleThree;
	}

	public LineChart<Number, Number> display()
	{
		
		final NumberAxis xAxis = new NumberAxis();
		final NumberAxis yAxis = new NumberAxis();
		
		LineChart<Number,Number> defaultGraph = new LineChart<Number,Number>(xAxis,yAxis);
		return defaultGraph;
		
		
		
	}
	
	
	@Override
	public String toString()
	{
		return title;
	}

}
