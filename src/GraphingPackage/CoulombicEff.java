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

public class CoulombicEff extends Graph {


    private ArrayList<XYChart.Series> seriesList;

    public CoulombicEff(File fileName, double value, String title,
                        ArrayList<Double> cycles, int Channel) {
        super(fileName, value, title, cycles, Channel);

    }

    public LineChart<Number, Number> display() {


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

        lineChart.setTitle("Coulombic Efficiency vs Cycle Number");
        lineChart.setLegendVisible(false);


    //defining a series


        return lineChart;
    }


    public void seriesdis() {

    }

    @Override
    public ArrayList<XYChart.Series> getSeriesList() {
        return seriesList;
    }

    public void series() {
        XYChart.Series series = new XYChart.Series();


        //populating the series with data

        if (excelReader != null) {

            for (int i = 0; i < electricityData1.size(); i++) {
                double cycle = electricityData1.get(i).getCycle_NumberStat();

                double CoulGet = (((electricityData1.get(i).getDischarge_CapacityStat() * 1000) / mass) /
                        ((electricityData1.get(i).getCharge_CapacityStat() * 1000) / mass)) * 100;


                //System.out.println(CoulGet);

                XYChart.Data data = new XYChart.Data(cycle, CoulGet);
                series.getData().add(data);


            }

        }
        seriesList.add(series);
    }
}
	

	
	
	

