package GraphingPackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.slf4j.LoggerFactory;

import com.monitorjbl.xlsx.StreamingReader;

public class ExcelReader 
{
	
	private SheetData data;
	private File fileName;

	protected double cycleOne;
	protected double cycleTwo;
	protected double cycleThree;
	protected int Channel;
	protected int Stat;

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
	
	public double getCycleOne()
	{
		return cycleOne;
	}

	public void setCycleOne(double cycleOne) 
	{
		this.cycleOne = cycleOne;
	}

	public double getCycleTwo()
	{
		return cycleTwo;
	}

	public void setCycleTwo(double cycleTwo) 
	{
		this.cycleTwo = cycleTwo;
	}

	public double getCycleThree()
	{
		return cycleThree;
	}

	public void setCycleThree(double cycleThree)
	{
		this.cycleThree = cycleThree;
	}

	public ExcelReader(File fileName, double cycleOne, double cycleTwo, double cycleThree, int Channel, int Stat) throws IOException
	{
		this.fileName = fileName;
		
		this.cycleOne = cycleOne;
		this.cycleTwo = cycleTwo;
		this.cycleThree = cycleThree;
		
		this.Channel = Channel;
		this.Stat = Stat;
		
		data = new SheetData();
		
		readData();
		
	}
 
 

	@SuppressWarnings("incomplete-switch")
	private void readData() throws IOException
	{
		//System.out.println(fileName.getPath());

		
		File is = new File(fileName.getPath());
		Workbook workbook = StreamingReader.builder()
		        .rowCacheSize(64000)   
		        .bufferSize(3072)     
		        .open(is);         
		
		List<Double> container;
		List<Double> container1;   
		
		
		int j;
		for(j = 1; j>=Channel; j++) {
		
		Sheet firstSheet = workbook.getSheetAt(j);
		
		
		Iterator<Row> iterator = firstSheet.iterator();
		iterator.next();
	

		while (iterator.hasNext()) 
		{
			Row nextRow = iterator.next();
			
			container = new ArrayList<Double>();
			
			boolean isCycle = false;
			
			for(int i = 5; i <= 12; i++)
			{
				Cell currentCell = nextRow.getCell(i);
				
				double cellContent = 0;
				
				double cycleCell = nextRow.getCell(5).getNumericCellValue();
				
				switch(currentCell.getCellTypeEnum())
				{
					case NUMERIC: cellContent = (double)(currentCell.getNumericCellValue());
					break;
				}
				

				if(cycleCell == cycleOne || cycleCell == cycleTwo || cycleCell == cycleThree)
				{
					isCycle = true;
					container.add(cellContent);
				}
					
				
            }
           
			if(isCycle)
			{	
				data.electrictyData.add(new Data(container));
			}
			
			
		}
		
		
		}
		
		Sheet statSheet = workbook.getSheetAt(2);
	
		
		Iterator<Row> iteratorstat = statSheet.iterator();
		iteratorstat.next();
		
		
		while (iteratorstat.hasNext()) 
		{
			Row nextRow = iteratorstat.next();
			
			container1 = new ArrayList<Double>();
			
			
			
			for(int i = 0; i <= 14; i++)
			{
				Cell currentCell = nextRow.getCell(i);
				
				double cellContent1 = 0;
				
				switch(currentCell.getCellTypeEnum())
				{
					case NUMERIC: cellContent1 = (double)(currentCell.getNumericCellValue());
					break;
				}
				
					container1.add(cellContent1);
				}
					
				data.electrictyData1.add(new StatData(container1));
			
            }
			
		workbook.close();
		}
		
	
		//inputStream.close();
	}
 

 
 
