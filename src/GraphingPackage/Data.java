package GraphingPackage;


import java.util.List;
 
public class Data
{
    private double Voltage;
    private double Charge_Capacity;
    private double Discharge_Capacity;
    private double Cycle_Number;
    private double Current;
    private double dtDv;
    private double stepIndx;

	public Data(List<Double> data) 
    {
		for(int i = 0; i < data.size(); i++)
		{


			if(i == 0){
				setCycle_Number(data.get(i));
				continue;
			}if(i == ExcelReader.current){
				setCurrent(data.get(i));
				continue;
			}if(i == ExcelReader.voltage){
				setVoltage(data.get(i));
				continue;
			}if(i == ExcelReader.charge){
				setCharge_Capacity(data.get(i));
				continue;
			}if(i == ExcelReader.discharge){
				setDischarge_Capacity(data.get(i));
				continue;
			}if(i == ExcelReader.dvdt){
				setDtDv(data.get(i));
				continue;
			}


			/*
			switch(i)
			{
			
				case 0:setCycle_Number(data.get(i));
				break;
				case 1:setCurrent(data.get(i));
				break;
				case 2:setVoltage(data.get(i));
				break;
				case 3:setCharge_Capacity(data.get(i));
				break;
				case 4:setDischarge_Capacity(data.get(i));
				break;
				case 7:setDtDv(data.get(i));
				
			}
			*/

		}
	}

	public double getStepIndx() {
		
		return stepIndx;
	}




	public void setStepIndx(double stepIndx) {
		this.stepIndx = stepIndx;
	}




	public double getDtDv() {
		return dtDv;
	}




	public void setDtDv(double dtDv) {
		this.dtDv = dtDv;
	}




	public double getCurrent() {
		return Current;
	}




	public void setCurrent(double current) {
		Current = current;
	}




	public double getCycle_Number() {
		return Cycle_Number;
	}




	public void setCycle_Number(double cycle_number) {
		Cycle_Number = cycle_number;
	}




	public double getDischarge_Capacity() {
		return Discharge_Capacity;
	}



	public void setDischarge_Capacity(double discharge_Capacity) {
		Discharge_Capacity = discharge_Capacity;
	}


	public double getVoltage() {
		return Voltage;
	}
 
 
 
	public void setVoltage(double voltage) {
		Voltage = voltage;
	}
 
 
 
	public double getCharge_Capacity() {
		return Charge_Capacity;
	}
 
 
 
	public void setCharge_Capacity(double charge_Capacity) {
		Charge_Capacity = charge_Capacity;
	}
 
 
 
	
    public String toString() {
       return Voltage + "" + "" + Charge_Capacity;
    }



    // getters and setters
}
 

