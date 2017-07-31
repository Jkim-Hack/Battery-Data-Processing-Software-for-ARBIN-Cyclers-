package GraphingPackage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class ExcelReader 
{
	
	private SheetData data;
	private File fileName;
	protected double Cycle1;
	protected double Cycle2;
	protected double Cycle3;
	
	public double getCycle1() {
		return Cycle1;
	}

	public void setCycle1(double cycle1) {
		Cycle1 = cycle1;
	}

	public double getCycle2() {
		return Cycle2;
	}

	public void setCycle2(double cycle2) {
		Cycle2 = cycle2;
	}

	public double getCycle3() {
		return Cycle3;
	}

	public void setCycle3(double cycle3) {
		Cycle3 = cycle3;
	}

	public File getFileName()
	{
		return fileName;
		
	}

	public void setFileName(File fileName) 
	{
		this.fileName = fileName;
	}
 
	public SheetData getData() 
	{
		return data;
	}
 
	public void setData(SheetData data)
	{
		this.data = data;
	}
	
	public ExcelReader(File fileName) throws IOException
	{
		this.fileName = fileName;
		
		data = new SheetData();
		
		readData();
		
	}
 
 
	@SuppressWarnings("incomplete-switch")
	private void readData() throws IOException
	{
		//System.out.println(fileName.getPath());

		
		XSSFWorkbook workbook = new XSSFWorkbook(fileName.getPath());
		Sheet firstSheet = workbook.getSheetAt(1);
		Sheet secondSheet = workbook.getSheetAt(2);
		Iterator<Row> iterator = firstSheet.iterator();
		iterator.next();
		
		Iterator<Row> iterator2 = secondSheet.iterator();
		iterator2.next();
		
		List<Double> container;
		List<Double> container1;
		
		//use for loop here instead of while
		while (iterator.hasNext()) 
		{
			Row nextRow = iterator.next();
			
			container = new ArrayList<Double>();
			
			
			for(int i = 5; i <= 12; i++)
			{
				Cell currentCell = nextRow.getCell(i);
				
				double cellContent = 0;
				
				switch(currentCell.getCellTypeEnum())
				{
					case NUMERIC: cellContent = (double)(currentCell.getNumericCellValue());
					break;
				}
				
				if(cellContent == Cycle1 || cellContent == Cycle2 || cellContent == Cycle3)
				{
					container.add(cellContent);
				}
					
				
            }
           
		
			data.electrictyData.add(new Data(container));
		}
<<<<<<< HEAD
	/*
=======
		
>>>>>>> branch 'master' of https://github.com/Jkim-Hack/OSUGrapherEEngineering.git
		while (iterator2.hasNext()) 
		{
			Row nextRow = iterator2.next();
			Iterator<Cell> cellIterator = nextRow.cellIterator();
			container1 = new ArrayList<Double>();
			
			
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				
				double cellContent = 0;
				switch(cell.getCellTypeEnum())
				{
				
					case STRING: cellContent = Double.parseDouble(cell.getStringCellValue());
					
					break;
					
					case NUMERIC: cellContent = (double)(cell.getNumericCellValue());
					break;
					
					
				
				
				}
				container1.add(cellContent);
            }
           
			data.electrictyData1.add(new StatData(container1));
		}
		
		
		workbook.close();
		//inputStream.close();
	}
 
}
 
 
