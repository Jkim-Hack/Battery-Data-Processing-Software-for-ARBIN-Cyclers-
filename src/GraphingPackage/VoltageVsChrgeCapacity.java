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
/*<<<<<<< HEAD
=======

return leastDoubleV;

}
	

// DISPLAY
// DISPLAY


public void display()
	{
		
		
	//Secondary stage is called for the graphs	
 
	
	

	
System.out.println(FindLeastV());
System.out.println(FindLeastCC());
System.out.println(FindGreatestCC());
System.out.println(FindGreatestV());


	Stage secondaryStage = new Stage();
	
BorderPane borderPane = new BorderPane();
secondaryStage.setTitle("Voltage vs Charge Capacity");
//defining the axes
final NumberAxis xAxis = new NumberAxis(FindLeastCC(), FindGreatestCC(), (FindGreatestCC())/6);
final NumberAxis yAxis = new NumberAxis(0, FindGreatestV(), (FindGreatestV())/4);
xAxis.setLabel("Charge Capacity (mAh/g)");
yAxis.setLabel("Voltage (V)");

//creating the chart

final LineChart<Number,Number> lineChart = 
        new LineChart<Number,Number>(xAxis,yAxis);
        
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
List<Data> electrictyData = excelData.getData().electrictyData;
for(int i = 0; i < electrictyData.size(); i ++)
{
	double chargeGet = (electrictyData.get(i).getCharge_Capacity()) * 1000;
	double charge = chargeGet/value;
	double voltage = electrictyData.get(i).getVoltage();
	XYChart.Data data = new XYChart.Data(charge,voltage);
	Rectangle rect = new Rectangle(0,0);
	rect.setVisible(false);
	data.setNode(rect);
	series.getData().add(data);
	double stpI = electrictyData.get(i).getStepIndx();
	if (stpI == 3) {
		break;
	}
	
}

XYChart.Series Dseries = new XYChart.Series();
Dseries.setName("Discharge");

for(int i = 0; i < electrictyData.size(); i ++)
{
	double DchargeGet = (electrictyData.get(i).getDischarge_Capacity()) * 1000 ;
	double Dcharge = DchargeGet/value;
	//System.out.println(Dcharge);
	double Dvoltage = electrictyData.get(i).getVoltage();
	XYChart.Data Ddata = new XYChart.Data(Dcharge,Dvoltage);
	Rectangle rect = new Rectangle(0,0);
	rect.setVisible(false);
	Ddata.setNode(rect);
	Dseries.getData().add(Ddata);
	double stpI = electrictyData.get(i).getStepIndx();
	if (stpI == 5) {
		break;
	}
}

//populating the series with data




lineChart.getData().addAll(series, Dseries);






//SECOND GRAPH IS CREATED (for now it will be the same)
//SECOND GRAPH IS CREATED






secondaryStage.setTitle("Voltage vs Charge Capacity");

//defining the axes

final NumberAxis xAxis1 = new NumberAxis();
final NumberAxis yAxis1 = new NumberAxis();
xAxis1.setLabel("Charge Capacity (Ah/g)");
yAxis1.setLabel("Voltage (V)");

//creating the chart

final LineChart<Number,Number> lineChart1 = 
    new LineChart<Number,Number>(xAxis1,yAxis1);

//lineChart.setTitle("Voltage vs Charge Capacity");
//Change this to subgraph titles^^^^^^

ExcelReader excelData1 = null;
try
{
excelData1 = new ExcelReader(fileName);
}

catch(Exception ioException)
{
ioException.printStackTrace();
}


//defining a series

XYChart.Series series1 = new XYChart.Series();
series1.nodeProperty();

//populating the series with data

if(excelData1!= null)
{
List<Data> electrictyData1 = excelData1.getData().electrictyData;
for(int i = 0; i < electrictyData.size(); i ++)
{
	double charge1 = (electrictyData.get(i).getCharge_Capacity());
	double voltage1 = electrictyData.get(i).getVoltage();
	XYChart.Data data1 = new XYChart.Data(charge1,voltage1);
	Rectangle rect1 = new Rectangle(0,0);
	rect1.setVisible(false);
	data1.setNode(rect1);
	series1.getData().add(data1);
}

}



Scene scene  = new Scene(borderPane,1000,800);


lineChart1.getData().add(series1);

secondaryStage.setScene(scene);

secondaryStage.show();


borderPane.setTop(lineChart);
borderPane.setBottom(lineChart1);
}
}
>>>>>>> branch 'master' of https://github.com/Jkim-Hack/OSUGrapherEEngineering.git
}*/
	
	