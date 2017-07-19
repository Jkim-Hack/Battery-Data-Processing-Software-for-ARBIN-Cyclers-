package GraphingPackage;

import java.io.File;
import java.util.List;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class CycleNumberDC extends Graph {

	
	public CycleNumberDC(File fileName, double value) 
	{
		setFile(fileName);
		setValue(value);
	
	}
	public double FindGreatestDC()
	{
	
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
		
	List<Data> electrictyData = excelData.getData().electrictyData;
	
	double greatestDoubleDC = ((electrictyData.get(0).getDischarge_Capacity()) * 1000)/mass;

	for(int i = 1; i < electrictyData.size(); i ++)	{
		
			double DischargeGetin = (electrictyData.get(i).getDischarge_Capacity()) * 1000;
			double Dischargein = DischargeGetin/mass;
			double stpIndex = electrictyData.get(i).getStepIndx();
			
			
			if( greatestDoubleDC < Dischargein ){
				if (stpIndex > 5){
					break;
			}
				greatestDoubleDC = Dischargein;
		}
	}
		return greatestDoubleDC;
						
}

public double FindGreatestV()
{

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
	
List<Data> electrictyData = excelData.getData().electrictyData;

double greatestDoubleV = electrictyData.get(0).getVoltage();

for(int i = 0; i < electrictyData.size(); i ++)	{
	
		double voltagein = electrictyData.get(i).getVoltage();
		double stpIndex = electrictyData.get(i).getStepIndx();
		

		if( greatestDoubleV < voltagein ){
			if (stpIndex > 3){
				break;
		}
			greatestDoubleV = voltagein;
	}
}
	return greatestDoubleV;
					
}

public double FindLeastDC()
{

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
	
List<Data> electrictyData = excelData.getData().electrictyData;

double leastDoubleDC = electrictyData.get(0).getDischarge_Capacity();

for(int i = 0; i < electrictyData.size(); i ++)	{
	
		double DischargeGetin = (electrictyData.get(i).getDischarge_Capacity()) * 1000;
		double Dischargein = DischargeGetin/mass;
		double stpIndex = electrictyData.get(i).getStepIndx();
		
		if (stpIndex == 5){
				
			if (leastDoubleDC == Dischargein){
					return leastDoubleDC;
			
			}
				
			break;
			
		}
	}
return leastDoubleDC;
}

public double FindLeastV()
{

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
	
List<Data> electrictyData = excelData.getData().electrictyData;

double leastDoubleV = electrictyData.get(0).getVoltage();

for(int i = 0; i < electrictyData.size(); i ++)	{
	
	double voltagein = electrictyData.get(i).getVoltage();
	double stpIndex = electrictyData.get(i).getStepIndx();
		
		if (stpIndex == 3){
				
			if (leastDoubleV == voltagein){
					return leastDoubleV;
			
			}
				
			break;
			
		}
	}

return leastDoubleV;

}
	

	public LineChart<Number,Number> display()
	{
		
		
	//Secondary stage is called for the graphs	
System.out.println(FindLeastV());
System.out.println(FindLeastDC());
System.out.println(FindGreatestDC());
System.out.println(FindGreatestV());
		
	Stage secondaryStage = new Stage();
	
BorderPane borderPane = new BorderPane();
secondaryStage.setTitle("Voltage vs Discharge Capacity");
//defining the axes
final NumberAxis xAxis = new NumberAxis(FindLeastDC(), FindGreatestDC() + (FindGreatestDC()/4), (FindGreatestDC())/6);
final NumberAxis yAxis = new NumberAxis(0, FindGreatestV() + (FindGreatestV()/4), (FindGreatestV())/6);
xAxis.setLabel("Capacity (mAh/g)");
yAxis.setLabel("Voltage (V)");

//creating the chart

final LineChart<Number,Number> lineChart = 
        new LineChart<Number,Number>(xAxis,yAxis);
        
lineChart.setTitle("Voltage vs Discharge Capacity");

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
series.nodeProperty();
series.setName("1st Discharge");
//populating the series with data

if(excelData!= null)
{
List<Data> electrictyData = excelData.getData().electrictyData;
for(int i = 0; i < electrictyData.size(); i ++)
{
	double stpI = electrictyData.get(i).getStepIndx();
	
	double chargeGet = (electrictyData.get(i).getDischarge_Capacity()) * 1000 ;

	double charge = chargeGet/mass;
	System.out.println(charge);
	
	//System.out.println(charge);
	
	
	
	double voltage = electrictyData.get(i).getVoltage();
	XYChart.Data data = new XYChart.Data(charge,voltage);
	Rectangle rect = new Rectangle(0,0);
	rect.setVisible(false);
	data.setNode(rect);
	series.getData().add(data);
	
	if (stpI == 5) {
		break;
	}
}

//populating the series with data




lineChart.getData().add(series);










		}

return lineChart;
	
	}
	
}
	

	
	
	

