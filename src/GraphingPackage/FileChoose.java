package GraphingPackage;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FileChoose
{

	private File fileName;
	
	public File getFileName() {
		return fileName;
	}

	public void setFileName(File fileName) {
		this.fileName = fileName;
	}

	public void chooseFile(Stage primaryStage)
	{	

	FileChooser fileChooser = new FileChooser();
    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XLS Files (*.xls)", "*.xls");
    FileChooser.ExtensionFilter extFilter1 = new FileChooser.ExtensionFilter("XLSX Files (*.xlsx)", "*.xlsx");
    fileChooser.getExtensionFilters().addAll(extFilter, extFilter1);
    File fileName = fileChooser.showOpenDialog(primaryStage);   

    if(fileName != null)
    {
    	setFileName(fileName);
    }
    	

 
	}
}
    
	
