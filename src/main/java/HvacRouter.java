public class HvacRouter {

	private EnvironmentController controller;

	public HvacRouter(EnvironmentController controller) {
		this.controller = controller;
	}

	public void setTheTemperatureRangeOnHVAC(int minTemp, int maxTemp) {
		if (maxTemp - minTemp < 5) {
			throw new IllegalArgumentException(Constants.INVALID_TEMP_RANGE_MESSAGE);
		}
		this.controller.setTempratureRanges(minTemp, maxTemp);
	}

	public void setTheHighTemperatureOnHVAC(int maxTemp) {
		setTheTemperatureRangeOnHVAC(controller.getMinTemp(), maxTemp);
	}

	public void setTheLowTemperatureOnHVAC(int minTemp) {
		setTheTemperatureRangeOnHVAC(minTemp, controller.getMaxTemp());
	}

}
