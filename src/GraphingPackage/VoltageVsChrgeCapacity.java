package GraphingPackage;

import java.io.File;
import java.util.ArrayList;
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
	

	public VoltageVsChrgeCapacity(File fileName, double value, String title, double cycleOne, double cycleTwo, double cycleThree) 
	{
		
		super(fileName, value, title, cycleOne, cycleTwo, cycleThree);
		
	}
	
	
	
	public double FindGreatestCC()
	{
	
		
		
		double greatestDoubleCC = ((electricityData.get(0).getCharge_Capacity()) * 1000)/mass;

		for(int i = 1; i < electricityData.size(); i ++)
		{
		
			double chargeGetin = (electricityData.get(i).getCharge_Capacity()) * 1000;
			double chargein = chargeGetin/mass;
			double current = electricityData.get(i).getCurrent();
			
			
			if( greatestDoubleCC < chargein )
			{
				if (current < 0)
				{
					break;
				}
				greatestDoubleCC = chargein;
			}
		 }
		
		 return greatestDoubleCC;
						
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
		}
			greatestDoubleV = voltagein;
	}
}
	return greatestDoubleV;
					
}

public double FindLeastCC()
{

	
	


double leastDoubleCC = electricityData.get(0).getCharge_Capacity();

for(int i = 0; i < electricityData.size(); i ++)	{
	
		double chargeGetin = (electricityData.get(i).getCharge_Capacity()) * 1000;
		double chargein = chargeGetin/mass;
		double current = electricityData.get(i).getCurrent();
		
		if (current == 0){
				
			if (leastDoubleCC == chargein){
					return leastDoubleCC;
			
			}
				
			break;
			
		}
	}
return leastDoubleCC;
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
	

	

	

// DISPLAY
// DISPLAY

	

	
/*System.out.println(FindLeastV());
System.out.println(FindLeastCC());
System.out.println(FindGreatestCC());
System.out.println(FindGreatestV());
*/


//defining the axes
final NumberAxis xAxis = new NumberAxis(FindLeastCC(), FindGreatestCC() + (FindGreatestCC()/4), (FindGreatestCC())/6);
final NumberAxis yAxis = new NumberAxis(0, FindGreatestV() + (FindGreatestV()/4), (FindGreatestV())/6);
xAxis.setLabel("Charge Capacity (mAh/g)");
yAxis.setLabel("Voltage (V)");

//creating the chart

final LineChart<Number,Number> lineChart = 
        new LineChart<Number,Number>(xAxis,yAxis);
        
lineChart.setTitle("Voltage vs Charge Capacity");


//defining a series

XYChart.Series series = new XYChart.Series();
series.setName("Cycle " + cycleOne);
series.nodeProperty();

//populating the series with data

if(excelReader != null)
{
	
	
	
	List<Data> currentByCycle = new ArrayList<Data>();
	for(int i = 0; i < electricityData.size(); i++)
	{
		
		if(electricityData.get(i).getCycle_Number() == cycleOne)
		{
			currentByCycle.add(electricityData.get(i));
		}
		
		
	}
	
	
	
	
	for(int i = 0; i < currentByCycle.size();)
	{
		if(currentByCycle.get(i).getCurrent() <= 0)
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

		double chargeGet = (currentByCycle.get(i).getCharge_Capacity()) * 1000 ;

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

XYChart.Series series1 = new XYChart.Series();
series1.setName("Cycle " + cycleTwo);
series1.nodeProperty();

//populating the series with data

if(excelReader != null)
{
	
	
	
	List<Data> currentByCycle = new ArrayList<Data>();
	for(int i = 0; i < electricityData.size(); i++)
	{
		
		if(electricityData.get(i).getCycle_Number() == cycleTwo)
		{
			currentByCycle.add(electricityData.get(i));
		}
		
		
	}
	
	
	
	
	for(int i = 0; i < currentByCycle.size();)
	{
		if(currentByCycle.get(i).getCurrent() <= 0)
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

		double chargeGet = (currentByCycle.get(i).getCharge_Capacity()) * 1000 ;

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

XYChart.Series series2 = new XYChart.Series();
series2.setName("Cycle " + cycleThree);
series2.nodeProperty();

//populating the series with data

if(excelReader != null)
{
	
	
	
	List<Data> currentByCycle = new ArrayList<Data>();
	for(int i = 0; i < electricityData.size(); i++)
	{
		
		if(electricityData.get(i).getCycle_Number() == cycleThree)
		{
			currentByCycle.add(electricityData.get(i));
		}
		
		
	}
	
	
	
	
	for(int i = 0; i < currentByCycle.size();)
	{
		if(currentByCycle.get(i).getCurrent() <= 0)
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

		double chargeGet = (currentByCycle.get(i).getCharge_Capacity()) * 1000 ;

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




lineChart.getData().addAll(series, series1, series2);


		
return lineChart;
	}
	
}
