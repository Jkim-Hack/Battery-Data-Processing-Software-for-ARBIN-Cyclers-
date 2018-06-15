package GraphingPackage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class VoltageVsChrgeCapacity extends Graph {

    private ArrayList<String> colorCodes = new ArrayList<>();
    private ArrayList<String> seriesName = new ArrayList<>();
    private ArrayList<String> colorFills = new ArrayList<>();

    private ArrayList<XYChart.Series> seriesList = new ArrayList<>();

    public VoltageVsChrgeCapacity(File fileName, double value, String title,
                                ArrayList<Double> cycles) {

        super(fileName, value, title, cycles);

        colorCodes.add("{ -fx-stroke: red; }");
        colorCodes.add("{-fx-stroke: green;}");
        colorCodes.add("{-fx-stroke: blue;}");
        colorCodes.add("{-fx-stroke: #ffa500;}");
        colorCodes.add("{-fx-stroke: #4169e1;}");
        colorCodes.add("{-fx-stroke: #9933ff;}");
        colorCodes.add("{-fx-stroke: #00ffff;}");
        colorCodes.add("{-fx-stroke: yellow;}");
        colorCodes.add("{-fx-stroke: #ff3399;}");
        colorCodes.add("{-fx-stroke: yellow;}");

        colorFills.add("-fx-fill: red;");
        colorFills.add("-fx-fill: green;");
        colorFills.add("-fx-fill: blue;");
        colorFills.add("-fx-fill: #ffa500;");
        colorFills.add("-fx-fill: #4169e1;");
        colorFills.add("-fx-fill: #9933ff;");
        colorFills.add("-fx-fill: #00ffff;");
        colorFills.add("-fx-fill: yellow;");
        colorFills.add("-fx-fill: #ff3399;");
        colorFills.add("-fx-fill: yellow;");

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
        int count = 0;
        for (int i = 0; i < cycles.size(); i++) {
            lineChart.getStyleClass().add(".default-color" + i + ".chart-series-line " + colorCodes.get(count));
            if(i % 2 != 0) {
                count++;
            }
        }



        return lineChart;
    }


    @Override
    public ArrayList<XYChart.Series> getSeriesList() {
        return seriesList;
    }

    @Override
    public ArrayList<String> getColorCodes() {
        return colorCodes;
    }
    @Override
    public ArrayList<String> getSeriesName() {
        return seriesName;
    }
    @Override
    public ArrayList<String> getColorFills(){
        return colorFills;
    }

    //defining a series
    @Override
    public void series() {
        //series.setName("Cycle " + cycleOne);

        for (int u = 0; u < cycles.size(); u++) {

            XYChart.Series series = new XYChart.Series();
            seriesName.add("Cycle #" + u);

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
                    data.setNode(new HoverOverPane(u));
                    series.getData().addAll(data);

                }

            }

            seriesList.add(series);

        }

    }

    @Override
    public void seriesdis() {



    //populating the series with data

        if (excelReader != null) {

            for (int u = 0; u < cycles.size(); u++) {
                XYChart.Series seriesdis = new XYChart.Series();

                //seriesdis.setName("Cycle " + u);

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
                    data3.setNode(new HoverOverPane(u));
                    seriesdis.getData().add(data3);

                }
                seriesList.add(seriesdis);
            }

        }

    }

    class HoverOverPane extends StackPane{

        HoverOverPane(double cycleNumber){
            setPrefSize(15, 15);

            final Label label = new Label("Cycle #" + cycleNumber);
            label.getStyleClass().addAll("default-color0", "chart-line-symbol", "chart-series-line");
            label.setStyle("-fx-font-size: 20;");
            label.setMinSize(Label.USE_PREF_SIZE, Label.USE_PREF_SIZE);

            setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    getChildren().addAll(label);
                    toFront();
                }
            });

            setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    getChildren().clear();
                }
            });

        }


    }


}
			
		
	



	
		

