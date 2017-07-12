package GraphingPackage;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FileChoose extends MainMenu {

	private File fileName;
	
public File getFileName() {
		return fileName;
	}

	public void setFileName(File fileName) {
		this.fileName = fileName;
	}

@Override public void start(Stage primaryStage){	

	FileChooser fileChooser = new FileChooser();
    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XLS Files (*.xls)", "*.xls");
    fileChooser.getExtensionFilters().add(extFilter);
    File fileName = fileChooser.showOpenDialog(primaryStage);   
    System.out.println(fileName.getPath());
    
   setFileName(fileName);
    	

 
	}
}
    
	
