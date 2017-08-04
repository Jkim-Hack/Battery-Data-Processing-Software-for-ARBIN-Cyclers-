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
    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XLS Files (*.xls)", "*.xls"));
    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XLSX Files (*.xlsx)", "*.xlsx"));
    File fileName = fileChooser.showOpenDialog(primaryStage);   

    if(fileName != null)
    {
    	setFileName(fileName);
    }
    	

 
	}
}
    
	
