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


public class VoltageVsChrgeCapacity extends Graph {


    protected List<XYChart.Series> seriesList;

    public VoltageVsChrgeCapacity(File fileName, double value, String title,
                                ArrayList<Double> cycles , int Channel) {

        super(fileName, value, title, cycles, Channel);

    }


    public LineChart<Number, Number> display() {


    //defining the axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setAutoRanging(true);
        xAxis.setForceZeroInRange(false);
        yAxis.setAutoRanging(true);
        yAxis.setForceZeroInRange(false);
        xAxis.setLabel("Capacity (mAh/g)");
        yAxis.setLabel("Voltage (V)");


    //xAxis.setAutoRanging(true);
    //xAxis.setForceZeroInRange(false);
    //yAxis.setAutoRanging(true);
    //yAxis.setForceZeroInRange(false);
    //creating the chart

        final LineChart<Number, Number> lineChart =
                new LineChart<Number, Number>(xAxis, yAxis);

        lineChart.setTitle("Voltage vs Capacity");
        lineChart.setCreateSymbols(false);
        lineChart.getStylesheets().add("Chart.css");

        return lineChart;
    }


    @Override
    public List<XYChart.Series> getSeriesList() {
        return seriesList;
    }

    //defining a series
    public void series() {
        XYChart.Series series = new XYChart.Series();
        //series.setName("Cycle " + cycleOne);

        for (int u = 0; u < cycles.size(); u++) {


            if (excelReader != null) {


                List<Data> currentByCycle = new ArrayList<Data>();
                for (int i = 0; i < electricityData.size(); i++) {

                    if (electricityData.get(i).getCycle_Number() == cycles.get(u)) {
                        currentByCycle.add(electricityData.get(i));
                    }


                }


                for (int i = 0; i < currentByCycle.size(); ) {
                    if (currentByCycle.get(i).getCurrent() <= 0) {
                        currentByCycle.remove(i);
                    } else {
                        i++;
                    }
                }


                for (int i = 0; i < currentByCycle.size(); i++) {

                    double chargeGet = (currentByCycle.get(i).getCharge_Capacity()) * 1000;

                    double charge = chargeGet / mass;


                    //System.out.println(charge);

                    double voltage = currentByCycle.get(i).getVoltage();

                    XYChart.Data data = new XYChart.Data(charge, voltage);
                    Rectangle rect = new Rectangle(0, 0);
                    rect.setVisible(false);
                    data.setNode(rect);
                    series.getData().addAll(data);

                }

            }

            seriesList.add(series);

        }
    }


    public void seriesdis() {

        XYChart.Series seriesdis = new XYChart.Series();


//populating the series with data

        if (excelReader != null) {

            for (int u = 0; u < cycles.size(); u++) {


                List<Data> currentByCycle = new ArrayList<Data>();
                for (int i = 0; i < electricityData.size(); i++) {

                    if (electricityData.get(i).getCycle_Number() == cycles.get(u)) {
                        currentByCycle.add(electricityData.get(i));
                    }


                }


                for (int i = 0; i < currentByCycle.size(); ) {
                    if (currentByCycle.get(i).getCurrent() >= 0) {
                        currentByCycle.remove(i);
                    } else {
                        i++;
                    }
                }


                for (int i = 0; i < currentByCycle.size(); i++) {

                    double chargeGet = (currentByCycle.get(i).getDischarge_Capacity()) * 1000;

                    double charge = chargeGet / mass;

                    //System.out.println(charge);

                    double voltage = currentByCycle.get(i).getVoltage();

                    XYChart.Data data3 = new XYChart.Data(charge, voltage);
                    Rectangle rect = new Rectangle(0, 0);
                    rect.setVisible(false);
                    data3.setNode(rect);
                    seriesdis.getData().add(data3);

                }
                seriesList.add(seriesdis);
            }

        }

    }


}
	
			
		
	



	
		

