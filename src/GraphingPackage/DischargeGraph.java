package GraphingPackage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class DischargeGraph extends Graph
{
	
	
	public DischargeGraph(File fileName, double value, String title, 
			double cycleOne, double cycleTwo, double cycleThree, int Channel, int Stat) 
	{
		super(fileName, value, title, cycleOne,cycleTwo,cycleThree, Channel, Stat);
	
	}
	
	private double CYCLE_INDEX = cycleOne;
	private double CYCLE_INDEX1 = cycleTwo;
	private double CYCLE_INDEX2 = cycleThree;
	
	public double FindGreatestDC()
	{
	
	
	double greatestDoubleDC = ((electricityData.get(0).getDischarge_Capacity()) * 1000)/mass;

	for(int i = 1; i < electricityData.size(); i ++)	{
		
			double DischargeGetin = (electricityData.get(i).getDischarge_Capacity()) * 1000;
			double Dischargein = DischargeGetin/mass;
			double current = electricityData.get(i).getCurrent();
			
			
			if( greatestDoubleDC < Dischargein ){
				if (current > 0){
					break;
				}
				greatestDoubleDC = Dischargein;
		}
	}
		return greatestDoubleDC;
						
}

public double FindGreatestV()
{

	
	


double greatestDoubleV = electricityData.get(0).getVoltage();

for(int i = 0; i < electricityData.size(); i ++)	{
	
		double voltagein = electricityData.get(i).getVoltage();
		double current = electricityData.get(i).getCurrent();
		

		if( greatestDoubleV < voltagein ){
			if (current < 0){
				break;
		}	greatestDoubleV = voltagein;
	}
}
	return greatestDoubleV;
					
}

public double FindLeastDC()
{

	
	


double leastDoubleDC = electricityData.get(0).getDischarge_Capacity();

for(int i = 0; i < electricityData.size(); i ++)	{
	
		double DischargeGetin = (electricityData.get(i).getDischarge_Capacity()) * 1000;
		double Dischargein = DischargeGetin/mass;
		double current = electricityData.get(i).getCurrent();
		
		if (current == 0)
		{
				
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

	

double leastDoubleV = electricityData.get(0).getVoltage();

for(int i = 0; i < electricityData.size(); i ++)	{
	
	double voltagein = electricityData.get(i).getVoltage();
	double current = electricityData.get(i).getCurrent();
		
		if (current == 0){
				
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
/*System.out.println(FindLeastV());
System.out.println(FindLeastDC());
System.out.println(FindGreatestDC());
System.out.println(FindGreatestV());
*/
		
//defining the axes
final NumberAxis xAxis = new NumberAxis(FindLeastDC(), FindGreatestDC() + (FindGreatestDC()/4), (FindGreatestDC())/6);
final NumberAxis yAxis = new NumberAxis(0, FindGreatestV() + (FindGreatestV()/4), (FindGreatestV())/6);
xAxis.setLabel("Capacity (mAh/g)");
yAxis.setLabel("Voltage (V)");

//creating the chart

final LineChart<Number,Number> lineChart = 
        new LineChart<Number,Number>(xAxis,yAxis);
        
lineChart.setTitle("Voltage vs Discharge Capacity");




//defining a series

XYChart.Series series = new XYChart.Series();
series.nodeProperty();
series.setName("Cycle " + cycleOne);
//populating the series with data

	if(excelReader != null)
	{
		
		
		
		List<Data> currentByCycle = new ArrayList<Data>();
		for(int i = 0; i < electricityData.size(); i++)
		{
			
			if(electricityData.get(i).getCycle_Number() == CYCLE_INDEX)
			{
				currentByCycle.add(electricityData.get(i));
			}
			
			
		}
		
		
		
		
		for(int i = 0; i < currentByCycle.size();)
		{
			if(currentByCycle.get(i).getCurrent() >= 0)
			{
				currentByCycle.remove(i);
			}
			else
			{
				i++;
			}
		}
		
		
		

		for(int i = 0; i < currentByCycle.size(); i ++)
		{
	
			double chargeGet = (currentByCycle.get(i).getDischarge_Capacity()) * 1000 ;

			double charge = chargeGet/mass;

			//System.out.println(charge);
	
			double voltage = currentByCycle.get(i).getVoltage();
	
			XYChart.Data data = new XYChart.Data(charge,voltage);
			Rectangle rect = new Rectangle(0,0);
			rect.setVisible(false);
			data.setNode(rect);
			series.getData().add(data);

		}
	

	}

//populating the series with data

	XYChart.Series series1 = new XYChart.Series();
	series1.nodeProperty();
	series1.setName("Cycle " + cycleTwo);
	//populating the series with data

		if(excelReader != null)
		{
			
			
			
			List<Data> currentByCycle = new ArrayList<Data>();
			for(int i = 0; i < electricityData.size(); i++)
			{
				
				if(electricityData.get(i).getCycle_Number() == CYCLE_INDEX1)
				{
					currentByCycle.add(electricityData.get(i));
				}
				
				
			}
			
			
			
			
			for(int i = 0; i < currentByCycle.size();)
			{
				if(currentByCycle.get(i).getCurrent() >= 0)
				{
					currentByCycle.remove(i);
				}
				else
				{
					i++;
				}
			}
			
			
			

			for(int i = 0; i < currentByCycle.size(); i ++)
			{
		
				double chargeGet = (currentByCycle.get(i).getDischarge_Capacity()) * 1000 ;

				double charge = chargeGet/mass;

				//System.out.println(charge);
		
				double voltage = currentByCycle.get(i).getVoltage();
		
				XYChart.Data data1 = new XYChart.Data(charge,voltage);
				Rectangle rect = new Rectangle(0,0);
				rect.setVisible(false);
				data1.setNode(rect);
				series1.getData().add(data1);

			}
		

		}

	//populating the series with data

		XYChart.Series series2 = new XYChart.Series();
		series2.nodeProperty();
		series2.setName("Cycle " + cycleThree);
		//populating the series with data

			if(excelReader != null)
			{
				
				
				
				List<Data> currentByCycle = new ArrayList<Data>();
				for(int i = 0; i < electricityData.size(); i++)
				{
					
					if(electricityData.get(i).getCycle_Number() == CYCLE_INDEX2)
					{
						currentByCycle.add(electricityData.get(i));
					}
					
					
				}
				
				
				
				
				for(int i = 0; i < currentByCycle.size();)
				{
					if(currentByCycle.get(i).getCurrent() >= 0)
					{
						currentByCycle.remove(i);
					}
					else
					{
						i++;
					}
				}
				
				
				

				for(int i = 0; i < currentByCycle.size(); i ++)
				{
			
					double chargeGet = (currentByCycle.get(i).getDischarge_Capacity()) * 1000 ;

					double charge = chargeGet/mass;

					//System.out.println(charge);
			
					double voltage = currentByCycle.get(i).getVoltage();
			
					XYChart.Data data2 = new XYChart.Data(charge,voltage);
					Rectangle rect = new Rectangle(0,0);
					rect.setVisible(false);
					data2.setNode(rect);
					series2.getData().add(data2);

				}
			

			}

		//populating the series with data

		lineChart.getData().addAll(series, series1, series2);

	


return lineChart;
	
	}
	
}
	
	