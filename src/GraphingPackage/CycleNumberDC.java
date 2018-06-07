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

public class CycleNumberDC extends Graph {

    private ArrayList<XYChart.Series> seriesList;

    public CycleNumberDC(File fileName, double value, String title,
                         ArrayList<Double> cycles, int Channel) {
        super(fileName, value, title, cycles, Channel);
    }

    public LineChart<Number, Number> display() {


        //Secondary stage is called for the graphs
//System.out.println(FindLeastCycle());
//System.out.println(FindLeastCoul());
//System.out.println(FindGreatestCoul());
//System.out.println(FindGreatestCycle());

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

        final LineChart<Number, Number> lineChart =
                new LineChart<Number, Number>(xAxis, yAxis);

        lineChart.setTitle("Discharge Capacity vs Cycle Number");
        lineChart.setLegendVisible(false);


//defining a series


        return lineChart;
    }


    @Override
    public ArrayList<XYChart.Series> getSeriesList() {
        return seriesList;
    }

    public void seriesdis() {
    }

    //defining a series
    public void series() {
        XYChart.Series series = new XYChart.Series();

        if (excelReader != null) {
            for (int i = 0; i < electricityData1.size(); i++) {
                double cycle = electricityData1.get(i).getCycle_NumberStat();

                double chargeGet = (electricityData1.get(i).getDischarge_CapacityStat()) * 1000;

                double charge = chargeGet / mass;


                //System.out.println(charge);

                XYChart.Data data = new XYChart.Data(cycle, charge);
                series.getData().add(data);


            }

        }

        seriesList.add(series);

    }


}
	

	

	
	
	

