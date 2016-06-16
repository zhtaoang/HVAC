
public class EnvironmentController {



	private HVAC hvac;
	
	private int heaterTurnOffTimer;
	private int coolerTurnOffTimer;
	
	private boolean coolStatus;
	private boolean heatStatus;
	private int maxTemp = 75;
	private int minTemp = 65;
	private static int heaterCooldown = 5;
	private static int coolerCooldown = 3;
	double p;

	public EnvironmentController(HVAC hvac) {
		this.hvac = hvac;
	}

	public int getMaxTemp() {
		return maxTemp;
	}

	public int getMinTemp() {
		return minTemp;
	}

	public HVAC getHvac() {
		return hvac;
	}

	public void tick() {

		p = minTemp + (maxTemp - minTemp)*heaterCooldown/(heaterCooldown+coolerCooldown);

		if(hvac.temp() <= p) {
			hvac.heat(true);
			heatStatus = true;

			if(coolStatus) {
				hvac.cool(false);
				coolerTurnOffTimer = 4;
				coolStatus = false;
				hvac.fan(false);
			} else if(coolerTurnOffTimer == 0 && heaterTurnOffTimer == 0){
				hvac.fan(true);
			}

		}

		if(hvac.temp() > p) {
			hvac.cool(true);
			coolStatus = true;

			if(heatStatus) {
				hvac.heat(false);
				heaterTurnOffTimer = 6;
				heatStatus = false;
				hvac.fan(false);
			} else if(coolerTurnOffTimer == 0 && heaterTurnOffTimer == 0){
				hvac.fan(true);
			}

		}
		
		heaterTurnOffTimer = heaterTurnOffTimer == 0 ? 0 : heaterTurnOffTimer - 1;
		coolerTurnOffTimer = coolerTurnOffTimer == 0 ? 0 : coolerTurnOffTimer - 1;
 	}

	public void setTempratureRanges(int minTemp, int maxTemp) {
		this.minTemp = minTemp;
		this.maxTemp = maxTemp;
	}
}
