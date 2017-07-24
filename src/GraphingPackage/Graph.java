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
	
	
	public Graph(File fileName, double value, String title)
	{
		excelReader = null;
		
		try
		{
			excelReader = new ExcelReader(fileName);
			electricityData = excelReader.getData().electrictyData;
			electricityData1 = excelReader.getData().electrictyData1;
		}
		
		catch(Exception ioException)
		{
			ioException.printStackTrace();
		}
		
		this.mass = value;
		
		this.title = title;
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
	
	public LineChart<Number, Number> display()
	{
		
		final NumberAxis xAxis = new NumberAxis();
		final NumberAxis yAxis = new NumberAxis();
		
		LineChart<Number,Number> defaultGraph = new LineChart<Number,Number>(xAxis,yAxis);
		return defaultGraph;
		
	}
}
