package GraphingPackage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.event.EventHandler;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class CycleNumberDC extends Graph {

    private ArrayList<XYChart.Series> seriesList = new ArrayList<>();

    public CycleNumberDC(File fileName, double value, String title,
                         ArrayList<Double> cycles) {
        super(fileName, value, title, cycles);
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
                data.setNode(new HoverOverPane(1));
                series.getData().add(data);


            }

        }

        seriesList.add(series);

    }


    class HoverOverPane extends StackPane {

        HoverOverPane(double cycleNumber){
            setPrefSize(15, 15);

            final Label label = new Label("Cycle #" + cycleNumber);
            label.getStyleClass().addAll("default-color0", "chart-line-symbol", "chart-series-line");
            label.setStyle("-fx-font-size: 20;");
            label.setMinSize(Label.USE_PREF_SIZE, Label.USE_PREF_SIZE);

            setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    System.out.println("HHEEEE");
                    getChildren().addAll(label);
                    toFront();
                }
            });

            setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    getChildren().clear();
                    System.out.println("k");
                }
            });

        }


    }

}
	

	

	
	
	

