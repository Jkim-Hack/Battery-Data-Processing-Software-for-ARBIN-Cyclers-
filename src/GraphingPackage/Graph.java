package GraphingPackage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.shape.Rectangle;

public class Graph {

    protected File fileName;
    protected double DataMass;
    protected double mass;
    protected String title;
    protected ExcelReader excelReader;
    protected List<Data> electricityData;
    protected List<StatData> electricityData1;
    protected ArrayList<Double> cycles;
    protected int Channel;
    protected int Stat;


    public Graph(File fileName, double value, String title,
                 ArrayList<Double> cycles) {
        excelReader = null;

        try {
            excelReader = new ExcelReader(fileName, cycles);
            electricityData = excelReader.getData().electrictyData;
            electricityData1 = excelReader.getData().electrictyData1;
        } catch (Exception ioException) {
            ioException.printStackTrace();
        }

        this.mass = value;
        this.title = title;
        this.cycles = cycles;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getValue() {
        return mass;
    }

    public void setValue(double value) {
        this.mass = value;
    }

    public File getFile() {
        return fileName;
    }

    public void setFile(File fileName) {
        this.fileName = fileName;

    }

    public LineChart<Number, Number> display() {

        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();

        final LineChart<Number, Number> defaultGraph = new LineChart<Number, Number>(xAxis, yAxis);
        return defaultGraph;


    }

    public ArrayList<XYChart.Series> getSeriesList() {
        ArrayList<Series> seriesList = new ArrayList<>();
        return seriesList;
    }

    public void series() {

        XYChart.Series series = new XYChart.Series();
        getSeriesList().add(series);
    }

    public void seriesdis() {
        XYChart.Series series = new XYChart.Series();
        getSeriesList().add(series);
    }


    @Override
    public String toString() {
        return title;
    }


}
