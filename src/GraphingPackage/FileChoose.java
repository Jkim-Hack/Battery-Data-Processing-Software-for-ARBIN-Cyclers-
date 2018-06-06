package GraphingPackage;

import java.io.File;
import java.io.Serializable;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FileChoose implements Serializable
{

	private  FileChooser fileChooser;

	public File fileName;
	
	public File getFileName() {
		return fileName;
	}

	public void setDir(File fileName){fileChooser.setInitialDirectory(fileName);}

	public void setFileName(File fileName) {
		this.fileName = fileName;
	}

	public void chooseFile(Stage primaryStage){
	fileChooser = new FileChooser();
    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XLS Files (*.xls)", "*.xls");
    FileChooser.ExtensionFilter extFilter1 = new FileChooser.ExtensionFilter("XLSX Files (*.xlsx)", "*.xlsx");
    fileChooser.getExtensionFilters().addAll(extFilter, extFilter1);
    fileName = fileChooser.showSaveDialog(primaryStage);
    setDir(fileName);
  
    if(fileName != null)
    {
    	setFileName(fileName);
    }
    	

 
	}
}
    
	
