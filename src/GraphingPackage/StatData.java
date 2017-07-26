package GraphingPackage;

import java.util.List;

public class StatData
{
    private double VoltageStat;
    private double Charge_CapacityStat;
    private double Discharge_CapacityStat;
    private double Cycle_NumberStat;
    private double CurrentStat;
    private double dtDvStat;
    


	public StatData(List<Double> data) 
    {
		for(int i = 0; i < data.size(); i++)
		{
			switch(i)
			{
			case 0:setCycle_NumberStat(data.get(i));
			break;
			case 5:setCharge_CapacityStat(data.get(i));
			break;
			case 6:setDischarge_CapacityStat(data.get(i));
			break;
			}
		}
	}

	
    public double getVoltageStat() {
		return VoltageStat;
	}


	public void setVoltageStat(double voltageStat) {
		VoltageStat = voltageStat;
	}


	public double getCharge_CapacityStat() {
		return Charge_CapacityStat;
	}


	public void setCharge_CapacityStat(double charge_CapacityStat) {
		Charge_CapacityStat = charge_CapacityStat;
	}


	public double getDischarge_CapacityStat() {
		return Discharge_CapacityStat;
	}


	public void setDischarge_CapacityStat(double discharge_CapacityStat) {
		Discharge_CapacityStat = discharge_CapacityStat;
	}


	public double getCycle_NumberStat() {
		return Cycle_NumberStat;
	}


	public void setCycle_NumberStat(double cycle_NumberStat) {
		Cycle_NumberStat = cycle_NumberStat;
	}


	public double getCurrentStat() {
		return CurrentStat;
	}



	public void setCurrentStat(double currentStat) {
		CurrentStat = currentStat;
	}



	public double getDtDvStat() {
		return dtDvStat;
	}


	public void setDtDvStat(double dtDvStat) {
		this.dtDvStat = dtDvStat;
	}


	public String toString() {
       return VoltageStat + "" + "" + Charge_CapacityStat;
    }



    // getters and setters
}
 

