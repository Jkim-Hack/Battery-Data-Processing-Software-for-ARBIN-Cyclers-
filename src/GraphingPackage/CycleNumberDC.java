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

	
	public CycleNumberDC(File fileName, double value,String title,double cycleOne, double cycleTwo, double cycleThree) 
	{
		super(fileName, value, title,cycleOne,cycleTwo,cycleThree);	
	}
	public double FindGreatestDC()
	{
	
	double greatestDoubleDC = ((electricityData1.get(0).getDischarge_CapacityStat()) * 1000)/mass;

	for(int i = 1; i < electricityData1.size(); i ++)	{
		
			double DischargeGetin = (electricityData1.get(i).getDischarge_CapacityStat()) * 1000;
			double Dischargein = DischargeGetin/mass;
			
			
			
			if( greatestDoubleDC < Dischargein ){
				
				greatestDoubleDC = Dischargein;
		}
	}
		return greatestDoubleDC;
						
}

public double FindGreatestCycle()
{

	


double greatestDoubleCycle = electricityData1.get(0).getCycle_NumberStat();

for(int i = 0; i < electricityData1.size(); i ++)	{
	
		double Cycle = electricityData1.get(i).getCycle_NumberStat();
		
		

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

	
double leastDoubleDC = electricityData1.get(0).getDischarge_CapacityStat();

for(int i = 0; i < electricityData1.size(); i ++)	{
	
		double DischargeGetin = (electricityData1.get(i).getDischarge_CapacityStat()) * 1000;
		double Dischargein = DischargeGetin/mass;
		
		
		
				
			if (leastDoubleDC == Dischargein){
					return leastDoubleDC;
			
			
				
		
			
		}
	}
return leastDoubleDC;
}

public double FindLeastCycle()
{

	

double LeastCycle = electricityData1.get(1).getCycle_NumberStat();

for(int i = 0; i < electricityData1.size(); i ++)	{
	
	double Cycle = electricityData1.get(i).getCycle_NumberStat();
		
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
		
	
//defining the axes
final NumberAxis yAxis = new NumberAxis(FindLeastDC(), FindGreatestDC() + (FindGreatestDC()/4), (FindGreatestDC())/5);
final NumberAxis xAxis = new NumberAxis(0, FindGreatestCycle()+1, 5);
yAxis.setLabel("Capacity (mAh/g)");
xAxis.setLabel("Cycle Number");

//creating the chart

final LineChart<Number,Number> lineChart = 
        new LineChart<Number,Number>(xAxis,yAxis);
        
lineChart.setTitle("Discharge Capacity vs Cycle Number");


//defining a series

XYChart.Series series = new XYChart.Series();
series.nodeProperty();
series.setName("All Cycles");
//populating the series with data

if(excelReader!= null)
{
for(int i = 0; i < electricityData1.size(); i ++)
{
	double cycle = electricityData1.get(i).getCycle_NumberStat();
	
	double chargeGet = (electricityData1.get(i).getDischarge_CapacityStat()) * 1000 ;

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
	

	
	
	

