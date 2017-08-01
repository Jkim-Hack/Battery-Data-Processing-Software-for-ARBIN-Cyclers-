package GraphingPackage;

import java.io.File;
import java.util.List;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class CoulombicEff extends Graph {

	
	public CoulombicEff(File fileName, double value, String title,double cycleOne, double cycleTwo, double cycleThree) 
	{
		super(fileName, value, title, cycleOne,cycleTwo,cycleThree);
	
	}
	public double FindGreatestCoul()
	{
	
	double greatestDoubleCoul = ((((electricityData1.get(0).getDischarge_CapacityStat() * 1000)/mass)/
			((electricityData1.get(0).getCharge_CapacityStat() * 1000)/mass)) * 100);

	for(int i = 1; i < electricityData1.size(); i ++)	{
		
			double Coul = ((((electricityData1.get(i).getDischarge_CapacityStat() * 1000)/mass)/
					((electricityData1.get(i).getCharge_CapacityStat() * 1000)/mass)) * 100);
			
			
			if( greatestDoubleCoul < Coul ){
			
				greatestDoubleCoul = Coul;
		}
	}
		return greatestDoubleCoul;
						
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

public double FindLeastCoul()
{

double leastDoubleCoul = ((((electricityData1.get(0).getDischarge_CapacityStat() * 1000)/mass)/
		((electricityData1.get(0).getCharge_CapacityStat() * 1000)/mass)) * 100);

for(int i = 0; i < electricityData1.size(); i ++)	{
	
		double Coul = ((((electricityData1.get(i).getDischarge_CapacityStat() * 1000)/mass)/
				((electricityData1.get(i).getCharge_CapacityStat() * 1000)/mass)) * 100);
		
			if (leastDoubleCoul == Coul){
					return leastDoubleCoul;
			
		
	}
}
return leastDoubleCoul;
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
		
		
	//Secondary stage is called for the graphs	
//System.out.println(FindLeastCycle());
//System.out.println(FindLeastCoul());
//System.out.println(FindGreatestCoul());
//System.out.println(FindGreatestCycle());
		
//defining the axes
final NumberAxis yAxis = new NumberAxis(0, FindGreatestCoul() + (FindGreatestCoul()/4), (FindGreatestCoul())/5);
final NumberAxis xAxis = new NumberAxis(0, FindGreatestCycle() + 1, 1);
yAxis.setLabel("Capacity (mAh/g)");
xAxis.setLabel("Cycle Number");

//creating the chart

final LineChart<Number,Number> lineChart = 
        new LineChart<Number,Number>(xAxis,yAxis);
        
lineChart.setTitle("Coulombic Efficiency vs Cycle Index");



//defining a series

XYChart.Series series = new XYChart.Series();
series.nodeProperty();
series.setName("");
//populating the series with data

if(excelReader!= null)
{

for(int i = 0; i < electricityData1.size(); i ++)
{
	double cycle = electricityData1.get(i).getCycle_NumberStat();
	
	double CoulGet = (((electricityData1.get(i).getDischarge_CapacityStat() * 1000)/mass)/
			((electricityData1.get(i).getCharge_CapacityStat() * 1000)/mass)) * 100;

	
	//System.out.println(CoulGet);
	
	XYChart.Data data = new XYChart.Data(cycle,CoulGet);
	series.getData().add(data);
	
	
}

//populating the series with data




lineChart.getData().add(series);










		}

return lineChart;
	
	}
	
}
	

	
	
	

