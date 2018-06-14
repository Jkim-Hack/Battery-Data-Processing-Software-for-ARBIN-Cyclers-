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

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import com.monitorjbl.xlsx.StreamingReader;

public class ExcelReader {

    private SheetData data;
    private File fileName;

    protected ArrayList<Double> cycles;
    protected int Channel;
    protected int Stat;

    public static int current;
    public static int voltage;
    public static int charge;
    public static int discharge;
    public static int dvdt;

    public static  double finalCycle;

    public File getFileName() {
        return fileName;

    }

    public void setFileName(File fileName) {
        this.fileName = fileName;
    }

    public SheetData getData() {
        return data;
    }

    public void setData(SheetData data) {
        this.data = data;
    }

    public ExcelReader(File fileName, ArrayList<Double> cycles) throws IOException {
        this.fileName = fileName;

        this.cycles = cycles;


        data = new SheetData();

        readData();

    }


    @SuppressWarnings("incomplete-switch")
    private void readData() throws IOException {
        //System.out.println(fileName.getPath());


        File is = new File(fileName.getPath());
        Workbook workbook = StreamingReader.builder()
                .rowCacheSize(64000)
                .bufferSize(3072)
                .open(is);

        List<Double> container;
        List<Double> container1;


        int ChannelStart = 0;
        int o = 0;
        int s = 0;
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            if (workbook.getSheetAt(i).getSheetName().matches("Channel(.*)")) {
                if(o == 0){
                    ChannelStart = i;
                }
                o++;
                continue;
            }if (workbook.getSheetAt(i).getSheetName().matches("(.*)Statistics(.*)")){
                s = i;
            }
        }


        Iterator<Row> iterator1 = workbook.getSheetAt(ChannelStart).iterator();

        Row row = iterator1.next();

        int cycleStuff = 0;


        for (int i = 1; i < row.getPhysicalNumberOfCells(); i++) {

            if(row.getCell(i).getStringCellValue().matches("(.*)Cycle_Index(.*)")){
                cycleStuff = i;
                continue;
            }

            if(row.getCell(i)
                    .getStringCellValue().matches("(.*)Voltage(.*)")){
                voltage = i - cycleStuff;
            } else if(row.getCell(i)
                    .getStringCellValue().matches("(.*)Current(.*)")){
                current = i - cycleStuff;
            } else if(row.getCell(i)
                    .getStringCellValue().matches("Charge_Capacity(.*)")){
                charge = i - cycleStuff;
            } else if(row.getCell(i)
                    .getStringCellValue().matches("Discharge_Capacity(.*)")){
                discharge = i - cycleStuff;
            } else if(row.getCell(i)
                    .getStringCellValue().matches("dV/dt(.*)")){
                dvdt = i - cycleStuff;
            }
        }

        finalCycle = workbook.getSheetAt(o).getRow(workbook.getSheetAt(o).getLastRowNum()).getCell(cycleStuff).getNumericCellValue();

        Channel = o;
        for (int j = ChannelStart; j <= Channel; j++) {

            Sheet firstSheet = workbook.getSheetAt(j);


            Iterator<Row> iterator = firstSheet.iterator();
            iterator.next();

            while (iterator.hasNext()) {
                Row nextRow = iterator.next();

                container = new ArrayList<Double>();

                boolean isCycle = false;

                for (int i = cycleStuff; i <= (dvdt+cycleStuff); i++) {
                    Cell currentCell = nextRow.getCell(i);

                    try{
                    if (currentCell.equals(null)){
                        continue;
                    }
                    }catch (Exception e){
                        continue;
                    }

                    double cellContent = 0;

                    double cycleCell = nextRow.getCell(cycleStuff).getNumericCellValue();

                    switch (currentCell.getCellTypeEnum()) {
                        case NUMERIC:
                            cellContent = (double) (currentCell.getNumericCellValue());
                            break;
                    }

                    for (int k = 0; k < cycles.size(); k++) {


                        if (cycleCell == cycles.get(k).doubleValue()) {
                            isCycle = true;
                            container.add(cellContent);

                        }
                    }
                }

                if (isCycle) {
                    data.electrictyData.add(new Data(container));
                }


            }


        }


        Sheet statSheet = workbook.getSheetAt(s);


        Iterator<Row> iteratorstat = statSheet.iterator();
        iteratorstat.next();


        while (iteratorstat.hasNext()) {
            Row nextRow = iteratorstat.next();

            container1 = new ArrayList<Double>();


            for (int i = 0; i <= 14; i++) {
                Cell currentCell = nextRow.getCell(i);

                double cellContent1 = 0;

                try{
                    if (currentCell.equals(null)){
                        continue;
                    }
                }catch (Exception e){
                    continue;
                }

                switch (currentCell.getCellTypeEnum()) {
                    case NUMERIC:
                        cellContent1 = (double) (currentCell.getNumericCellValue());
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
 

 
 
