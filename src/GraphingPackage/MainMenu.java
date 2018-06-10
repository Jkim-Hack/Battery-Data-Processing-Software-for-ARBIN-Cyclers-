/*
Copyright (c) <2017>, <John Kim and Chris Yao>
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:

1. Redistributions of source code must retain the above copyright notice, this
   list of conditions and the following disclaimer.
2. Redistributions in binary form must reproduce the above copyright notice,
   this list of conditions and the following disclaimer in the documentation
   and/or other materials provided with the distribution.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

The views and conclusions contained in the software and documentation are those
of the authors and should not be interpreted as representing official policies,
either expressed or implied, of the FreeBSD Project.



Copyright 2017 John Kim and Chris Yao

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

/*
 This also uses monitorjbl's "Excel-Streaming-Reader" API https://github.com/monitorjbl/excel-streaming-reader
 AND kerner1000's "Javafx-Chart-Zooming" API https://github.com/kerner1000/javafx-chart-zooming	
 */

package GraphingPackage;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import AlertBox.Alert;


public class MainMenu extends Application {



    //Instance Variables


    private final PseudoClass errorClass = PseudoClass.getPseudoClass("error");

    public FileChoose fileChooser = new FileChoose();
    public static ArrayList<Double> cycle = new ArrayList<>();
    public double mass = 0;
    public int Channel = 0;
    public boolean rem = false;
    public String fileText = "Click to open file";
    public String filePath = "";

    @SuppressWarnings("restriction")
    @Override
    public void start(Stage primaryStage) {


        //Display Panes
        VBox topContainer = new VBox();
        VBox midinserts = new VBox();
        VBox GraphChoose = new VBox();


        midinserts.setSpacing(10);
        GraphChoose.setSpacing(20);


        Label labelMass = new Label("Insert Mass in mg: ");
        labelMass.setFont(Font.font("Segoe UI", 12));


        //Textbox for File 


        //Creates Drop Down Boxes 
        Label topLabel = new Label("Choose Graph: 						    ");
        ChoiceBox<String> box1 = new ChoiceBox<String>();
        
        
        
        /*
        Label botLeft = new Label("Top Left Pane 						    	    ");
        ChoiceBox<Graph> box2 = new ChoiceBox<Graph>();
        
        Label botRight = new Label("Top Right Pane 						    ");
        ChoiceBox<Graph> box3 = new ChoiceBox<Graph>();
        */


        Label fileLabel = new Label("Chosen File:");
        TextField fileField = new TextField();

        Label SheetField = new Label("Enter Amount of Channel Sheets: ");
        TextField SheetText = new TextField("1");


        Label Cycles = new Label("Enter Three Cycles: ");
        TextField insertCycle1 = new TextField();



        //Mass textfield
        Button createGraph = new Button();


        TextField insertMass = new TextField();

        File f = new File(System.getenv("APPDATA") + "\\BatteryDataSftwre\\path.ser");

        f.getParentFile().mkdirs();
        try {
            f.createNewFile();
        } catch (IOException e) {
            System.out.println("Already Exists");

        }

        try {
            FileInputStream fileIn = new FileInputStream(System.getenv("APPDATA") + "\\BatteryDataSftwre\\path.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            fileChooser = (FileChoose) in.readObject();
            fileField.setText(fileChooser.fileName.getName());
            fileText = fileField.getText();
            filePath = fileChooser.fileName.getPath();
            in.close();
            fileIn.close();
            rem = true;

        } catch (IOException i) {
            fileField.setText("Click to open file");

        } catch (ClassNotFoundException c) {
            fileField.setText("Click to open file");

        } catch (NullPointerException k) {
            fileField.setText("Click to open file");
        }


        String ChargeCap = "Voltage vs Charge Capacity & Discharge Capacity";

        List<String> graphs = new ArrayList<String>();
        graphs.add(ChargeCap);
        graphs.add("Discharge Capacity vs Cycle Number");
        graphs.add("Coulombic Efficiency vs Cycle Number");
        box1.getItems().addAll(graphs);

        //Menu drop down bar
        MenuBar ddMenu = new MenuBar();


        fileField.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(rem) {
                    fileChooser.chooseFile(primaryStage);
                } else {
                    fileChooser.chooseFileFirst(primaryStage);
                    rem = true;
                    filePath = fileChooser.getFileName().getPath();
                    fileText = fileChooser.getFileName().getName();
                }
                fileChooser.fileName = fileChooser.getFileName();


                try {
                    FileOutputStream fileOut = new FileOutputStream(System.getenv("APPDATA") + "\\BatteryDataSftwre\\path.ser");
                    ObjectOutputStream out = new ObjectOutputStream(fileOut);
                    out.writeObject(fileChooser);
                    out.close();
                    fileOut.close();
                } catch (IOException i) {
                    i.printStackTrace();
                }

                try {
                    fileField.setText(fileChooser.fileName.getName());
                } catch (Exception e){
                    fileField.setText(fileText);
                    fileChooser.setFileName(new File(filePath));
                    try {
                        FileOutputStream fileOut = new FileOutputStream(System.getenv("APPDATA") + "\\BatteryDataSftwre\\path.ser");
                        ObjectOutputStream out = new ObjectOutputStream(fileOut);
                        out.writeObject(fileChooser);
                        out.close();
                        fileOut.close();
                    } catch (IOException i) {
                        i.printStackTrace();
                    }
                }

                //File Chooser class
            }
        });

        fileField.setEditable(false);

        //Button for the graph window created


        createGraph.setText("Create Graphs");
        createGraph.setFont(Font.font("Segoe UI", 16));
        createGraph.setOnAction((ActionEvent event) ->
        {


            Task<Void> insertionCycles = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    Channel = toSheetInt(SheetText.getText());
                    mass = toMassDouble(insertMass.getText());
                    if (insertCycle1.getText().matches("(.*)(,)(.*)")) {
                        String[] cycles = insertCycle1.getText().split(",");
                        for (int i = 0; i < cycles.length; i++) {
                            cycle.add(toCycleDouble(cycles[i]));
                        }
                    } else if(insertCycle1.getText().matches("[\\d]+[\\-][\\d]+")) {
                        String[] cycles = insertCycle1.getText().split("[\\-]");
                        for (int i = Integer.parseInt(cycles[0]); i <= Integer.parseInt(cycles[cycles.length-1]); i++) {
                            double l = (double)i;
                            cycle.add(l);
                        }
                    } else {
                        throw new Exception("Error");
                    }
                    return null;
                }
            };
            Thread t = new Thread(insertionCycles);
            t.setDaemon(true);
            t.start();

            insertionCycles.setOnFailed(evente -> {

                Alert alert1 = new Alert();
                alert1.displayBox("Error in input fields");

            });
            insertionCycles.setOnSucceeded(evente -> {

                long start = System.currentTimeMillis();

                Channel = toSheetInt(SheetText.getText());

                mass = toMassDouble(insertMass.getText());

                mass = mass / 1000;

                createGraph.setDisable(false);


                MainGraphs graph = null;

                if (box1.getValue().equals("Voltage vs Charge Capacity & Discharge Capacity")) {
                    graph = new MainGraphs(new VoltageVsChrgeCapacity(fileChooser.fileName, mass, "Voltage vs Charge Capacity & Discharge Capacity",
                            cycle, Channel));
                    long end = System.currentTimeMillis();
                    System.out.println(end - start);
                }
                if (box1.getValue().equals("Discharge Capacity vs Cycle Number")) {
                    graph = new MainGraphs(new CycleNumberDC(fileChooser.fileName, mass, "Discharge Capacity vs Cycle Number",
                            cycle, Channel));
                    long end = System.currentTimeMillis();
                    System.out.println(end - start);
                }
                if (box1.getValue().equals("Coulombic Efficiency vs Cycle Number")) {
                    graph = new MainGraphs(new CoulombicEff(fileChooser.fileName, mass, "Coulombic Efficiency vs Cycle Number",
                            cycle, Channel));
                    long end = System.currentTimeMillis();
                    System.out.println(end - start);
                }

                graph.displayGraphs();
            });
        });

        createGraph.setPrefWidth(250);
        createGraph.setPrefHeight(70);


        //The primary master window is created

        BorderPane pane = new BorderPane();

        pane.setRight(GraphChoose);
        GraphChoose.setMargin(createGraph, new Insets(280, 0, 0, 0));
        GraphChoose.setPadding(new Insets(20, 20, 20, 20));
        GraphChoose.setAlignment(Pos.TOP_RIGHT);
        GraphChoose.getChildren().addAll(topLabel, box1, createGraph);
        box1.setPrefWidth(250);
        //	box2.setPrefWidth(250);
        //	box3.setPrefWidth(250);

        pane.setTop(topContainer);
        topContainer.getChildren().add(ddMenu);

        pane.setLeft(midinserts);
        midinserts.setPadding(new Insets(20, 20, 20, 20));
        midinserts.getChildren().addAll(fileLabel, fileField, labelMass, insertMass, Cycles, insertCycle1,
                SheetField, SheetText);


        InputStream in = this.getClass().getClassLoader().getResourceAsStream("favicon.PNG");

        Scene scene = new Scene(pane, 600, 497);

        Image icon = new Image(in);

        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("Battery Data Processing Software (Dedicated to ARBIN Cycler) v1.1");
        primaryStage.setScene(scene);
        primaryStage.show();


    }


    private boolean isDouble(TextField input, String mass) {

        try {
            double dataMass = Double.parseDouble(input.getText());
            return true;

        } catch (NumberFormatException e) {
            return false;
        }
    }


    public double toMassDouble(String mass) {
        double dataMass = Double.parseDouble(mass);
        return dataMass;
    }

    public int toSheetInt(String number) {
        int sheet = Integer.parseInt(number);
        return sheet;
    }

    public double toCycleDouble(String Cycle) {
        double AllCycle = Double.parseDouble(Cycle);
        return AllCycle;
    }


    private void validate(TextField tf) {


        Alert alert = new Alert();
        alert.displayBox("Error in input fields");

    }


    public static void main(String[] args) {

        launch(args);
    }
}

