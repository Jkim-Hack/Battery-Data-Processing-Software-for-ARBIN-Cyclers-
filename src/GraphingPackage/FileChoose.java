package GraphingPackage;

import java.io.File;
import java.io.Serializable;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FileChoose implements Serializable {


    public File fileName;

    public File fileDir;

    public File getFileName() {
        return fileName;
    }

    public void setFileName(File fileName) {
        this.fileName = fileName;
    }

    public void chooseFile(Stage primaryStage) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XLS Files (*.xls)", "*.xls");
        FileChooser.ExtensionFilter extFilter1 = new FileChooser.ExtensionFilter("XLSX Files (*.xlsx)", "*.xlsx");
        fileChooser.getExtensionFilters().addAll(extFilter, extFilter1);
        try {
           if(fileChooser.getInitialDirectory().equals(null));
            fileName = fileChooser.getInitialDirectory();
            System.out.println(fileName + "!");
        } catch (NullPointerException e){
            fileChooser.setInitialDirectory(fileName.getParentFile());
        }
        fileName = fileChooser.showOpenDialog(primaryStage);

        if (fileName != null) {
            setFileName(fileName);
        }


    }

    public void chooseFileFirst(Stage primaryStage) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XLS Files (*.xls)", "*.xls");
        FileChooser.ExtensionFilter extFilter1 = new FileChooser.ExtensionFilter("XLSX Files (*.xlsx)", "*.xlsx");
        fileChooser.getExtensionFilters().addAll(extFilter, extFilter1);
        fileName = fileChooser.showOpenDialog(primaryStage);

        if (fileName != null) {
            setFileName(fileName);
        }


    }


}
    
	
