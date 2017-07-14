package GraphingPackage;

import java.io.File;
import java.util.List;

import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class VoltageVsChrgeCapacity extends Graph
{
	
	public VoltageVsChrgeCapacity(File fileName, double value) 
	{
		setFile(fileName);
		setValue(value);
		
	}
	

	public LineChart<Number,Number> display()
	{		
	
		//defining the axes
		final NumberAxis xAxis = new NumberAxis();
		final NumberAxis yAxis = new NumberAxis();
		xAxis.setLabel("Charge Capacity (mAh/g)");
		yAxis.setLabel("Voltage (V)");
	
		//creating the chart

		LineChart<Number,Number> lineChart = new LineChart<Number,Number>(xAxis,yAxis);
        
		lineChart.setTitle("Voltage vs Charge Capacity");

		ExcelReader excelData = null;
		
		try
		{
			if(fileName == null)
			{
				System.out.println("Null Exception");
			}
			excelData = new ExcelReader(fileName);
			
		}

		catch(Exception ioException)
		{
			ioException.printStackTrace();
		}


		//defining a series

		XYChart.Series series = new XYChart.Series();
		series.setName("Charge");
		series.nodeProperty();

		//populating the series with data

		if(excelData!= null)
		{
			System.out.println("Excel Data Not Null");
			List<Data> electrictyData = excelData.getData().electrictyData;
			for(int i = 0; i < electrictyData.size(); i ++)
			{
				double chargeGet = (electrictyData.get(i).getCharge_Capacity()) * 1000;
				double charge = chargeGet/mass;
				
				//System.out.println(charge);
				System.out.println(mass);
			
				double voltage = electrictyData.get(i).getVoltage();
				XYChart.Data data = new XYChart.Data(charge,voltage);
				

				Rectangle rect = new Rectangle(0,0);
				rect.setVisible(false);
				data.setNode(rect);
				series.getData().add(data);
				
			}
			//populating the series with data

			lineChart.getData().add(series);
			
		 }
		
		return lineChart;
	}
}
	
	