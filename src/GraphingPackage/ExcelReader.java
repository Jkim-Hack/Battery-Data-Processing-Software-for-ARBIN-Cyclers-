/*
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
 */

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


import com.monitorjbl.xlsx.StreamingReader;

public class ExcelReader 
{
	
	private SheetData data;
	private File fileName;

	protected ArrayList<Double> cycles;
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

	public ExcelReader(File fileName, ArrayList<Double> cycles, int Channel) throws IOException
	{
		this.fileName = fileName;
		
		this.cycles = cycles;

		
		this.Channel = Channel;
	
		
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
		
		
		
		for(int j = 1; j<=Channel; j++) {
		
		Sheet firstSheet = workbook.getSheetAt(j);
		
		
		Iterator<Row> iterator = firstSheet.iterator();
		iterator.next();
	
		int k = 0;
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


					if (cycleCell == cycles.get(k)) {
						isCycle = true;
						container.add(cellContent);
						k++;
					}
				
            }
           
			if(isCycle)
			{	
				data.electrictyData.add(new Data(container));
			}
			
			
		}
		
		
		}
		
		int s = Channel + 1;
		
		Sheet statSheet = workbook.getSheetAt(s);
	
		
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
 

 
 
