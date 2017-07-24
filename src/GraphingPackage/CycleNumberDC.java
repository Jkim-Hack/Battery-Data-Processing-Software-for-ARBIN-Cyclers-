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

	
	public CycleNumberDC(File fileName, double value,String title) 
	{
		super(fileName, value, title);	
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
		
	List<StatData> electrictyData1 = excelData.getData().electrictyData1;
	
	double greatestDoubleDC = ((electrictyData1.get(0).getDischarge_CapacityStat()) * 1000)/mass;

	for(int i = 1; i < electrictyData1.size(); i ++)	{
		
			double DischargeGetin = (electrictyData1.get(i).getDischarge_CapacityStat()) * 1000;
			double Dischargein = DischargeGetin/mass;
			double current = electrictyData1.get(i).getCurrentStat();
			
			
			if( greatestDoubleDC < Dischargein ){
				if (current > 0){
					break;
				}
				greatestDoubleDC = Dischargein;
		}
	}
		return greatestDoubleDC;
						
}

public double FindGreatestCycle()
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
	
List<StatData> electrictyData1 = excelData.getData().electrictyData1;

double greatestDoubleCycle = electrictyData1.get(0).getCycle_NumberStat();

for(int i = 0; i < electrictyData1.size(); i ++)	{
	
		double Cycle = electrictyData1.get(i).getCycle_NumberStat();
		
		

		if( greatestDoubleCycle < Cycle ){
			if (Cycle < 0){
				break;
		}	greatestDoubleCycle = Cycle;
	}
}
	return greatestDoubleCycle;
					
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
	
List<StatData> electrictyData1 = excelData.getData().electrictyData1;

double leastDoubleDC = electrictyData1.get(0).getDischarge_CapacityStat();

for(int i = 0; i < electrictyData1.size(); i ++)	{
	
		double DischargeGetin = (electrictyData1.get(i).getDischarge_CapacityStat()) * 1000;
		double Dischargein = DischargeGetin/mass;
		double current = electrictyData1.get(i).getCurrentStat();
		
		if (current == 0){
				
			if (leastDoubleDC == Dischargein){
					return leastDoubleDC;
			
			}
				
			break;
			
		}
	}
return leastDoubleDC;
}

public double FindLeastCycle()
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
	
List<StatData> electrictyData1 = excelData.getData().electrictyData1;

double LeastCycle = electrictyData1.get(1).getCycle_NumberStat();

for(int i = 0; i < electrictyData1.size(); i ++)	{
	
	double Cycle = electrictyData1.get(i).getCycle_NumberStat();
		
		if (Cycle == 1){
				
			if (Cycle == LeastCycle){
					return LeastCycle;
			
			}
				
			break;
			
		}
	}

return LeastCycle;

}
	

	public LineChart<Number,Number> display()
	{
		
		
	//Secondary stage is called for the graphs	
System.out.println(FindLeastCycle());
System.out.println(FindLeastDC());
System.out.println(FindGreatestDC());
System.out.println(FindGreatestCycle());
		
	Stage secondaryStage = new Stage();
	
BorderPane borderPane = new BorderPane();
secondaryStage.setTitle("Discharge Capacity vs Cycle Index");
//defining the axes
final NumberAxis yAxis = new NumberAxis(FindLeastDC(), FindGreatestDC() + (FindGreatestDC()/4), (FindGreatestDC())/5);
final NumberAxis xAxis = new NumberAxis(0, FindGreatestCycle() + 1, 1);
yAxis.setLabel("Capacity (mAh/g)");
xAxis.setLabel("Cycle Number");

//creating the chart

final LineChart<Number,Number> lineChart = 
        new LineChart<Number,Number>(xAxis,yAxis);
        
lineChart.setTitle("Discharge Capacity vs Cycle Index");

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
series.setName("");
//populating the series with data

if(excelData!= null)
{
List<StatData> electrictyData1 = excelData.getData().electrictyData1;
for(int i = 0; i < electrictyData1.size(); i ++)
{
	double cycle = electrictyData1.get(i).getCycle_NumberStat();
	
	double chargeGet = (electrictyData1.get(i).getDischarge_CapacityStat()) * 1000 ;

	double charge = chargeGet/mass;
	
	
	//System.out.println(charge);
	
	XYChart.Data data = new XYChart.Data(cycle,charge);
	series.getData().add(data);
	
	
}

//populating the series with data




lineChart.getData().add(series);










		}

return lineChart;
	
	}
	
}
	

	
	
	

