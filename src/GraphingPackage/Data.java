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

	public Data(List<Double> data) 
    {
		for(int i = 0; i < data.size(); i++)
		{
			switch(i)
			{
				
				case 5:setCycle_Number(data.get(i));
				break;
				case 6:setCurrent(data.get(i));
				case 7:setVoltage(data.get(i));
				break;
				case 8:setCharge_Capacity(data.get(i));
				break;
				case 9:setDischarge_Capacity(data.get(i));
				break;
				case 12:setDtDv(data.get(i));
				
			}
		}
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




	public void setCycle_Number(double cycle_Number) {
		Cycle_Number = cycle_Number;
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
 

