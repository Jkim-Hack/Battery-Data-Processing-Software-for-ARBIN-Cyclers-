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

	
	public CoulombicEff(File fileName, double value, String title,
			double cycleOne, double cycleTwo, double cycleThree, int Channel, int Stat) 
	{
		super(fileName, value, title, cycleOne,cycleTwo,cycleThree, Channel, Stat);
	
	}

public LineChart<Number,Number> display()
	{
		
		
		
//defining the axes
final NumberAxis yAxis = new NumberAxis();
final NumberAxis xAxis = new NumberAxis();
xAxis.setAutoRanging(true);
xAxis.setForceZeroInRange(false);
yAxis.setAutoRanging(true);
yAxis.setForceZeroInRange(false);
yAxis.setLabel("Capacity (mAh/g)");
xAxis.setLabel("Cycle Number");

//creating the chart

final LineChart<Number,Number> lineChart = 
        new LineChart<Number,Number>(xAxis,yAxis);
        
lineChart.setTitle("Coulombic Efficiency vs Cycle Number");
lineChart.setLegendVisible(false);



//defining a series


return lineChart;
	}
	
		public XYChart.Series filler(){ 	
		final XYChart.Series filler = new XYChart.Series();
		return filler;
		}
		public XYChart.Series filler1(){ 	
		final XYChart.Series filler1 = new XYChart.Series();
		return filler1;
		}
		public XYChart.Series filler2(){ 	
		final XYChart.Series filler2 = new XYChart.Series();
		return filler2;
		}
		public XYChart.Series series1(){ 	
		final XYChart.Series filler = new XYChart.Series();
		return filler;
		}
		public XYChart.Series series2(){ 	
		final XYChart.Series filler1 = new XYChart.Series();
		return filler1;
		}
		public XYChart.Series seriesdis(){ 	
		final XYChart.Series filler2 = new XYChart.Series();
		return filler2;
		}
		public XYChart.Series series1dis(){ 	
		final XYChart.Series filler = new XYChart.Series();
		return filler;
		}
		public XYChart.Series series2dis(){ 	
		final XYChart.Series filler1 = new XYChart.Series();
		return filler1;
		}
		
			
			
	public XYChart.Series series(){
	final XYChart.Series series = new XYChart.Series();
	
	//populating the series with data

	if(excelReader!= null)
	{

	for(int i = 0; i < electricityData1.size(); i++)
	{
		double cycle = electricityData1.get(i).getCycle_NumberStat();
		
		double CoulGet = (((electricityData1.get(i).getDischarge_CapacityStat() * 1000)/mass)/
				((electricityData1.get(i).getCharge_CapacityStat() * 1000)/mass)) * 100;

		
		//System.out.println(CoulGet);
		
		XYChart.Data data = new XYChart.Data(cycle,CoulGet);
		series.getData().add(data);
		
		
	}
	
	}
	return series;
}
}
	

	
	
	

