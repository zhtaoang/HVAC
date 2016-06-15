import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EnvironmentControllerTest {
	
	private EnvironmentController controller;
	private HVACMock hvac;
	
	@Before
	public void setup() {
		hvac = new HVACMock();
		controller = new EnvironmentController(hvac);
	}
	
	@Test
	public void lessThan7125() {
		hvac.setTemp(71);
		controller.tick();
		
		Assert.assertTrue(hvac.isFanOn());
		Assert.assertFalse(hvac.isCoolOn());
		Assert.assertTrue(hvac.isHeatOn());
	}
	
	@Test
	public void tempGreaterThan7125() {
		hvac.setTemp(72);
		controller.tick();
		
		Assert.assertTrue(hvac.isCoolOn());
		Assert.assertTrue(hvac.isFanOn());
		Assert.assertFalse(hvac.isHeatOn());
	}
	
	@Test
	public void fanCantRunFor5MinsAfterHeaterOff() {
		// turning on the heater
		hvac.setTemp(60);
		controller.tick();

		// turning off the heater and turn on the cooler
		// fan should be off for 5 minutes
		hvac.setTemp(80);
		controller.tick();
		
		Assert.assertFalse(hvac.isHeatOn());
		Assert.assertTrue(hvac.isCoolOn());
		Assert.assertFalse(hvac.isFanOn());
		
		for (int i = 0; i < 5; i++){
			controller.tick();

			Assert.assertFalse(hvac.isHeatOn());
			Assert.assertTrue(hvac.isCoolOn());
			Assert.assertFalse(hvac.isFanOn());
		}

		controller.tick();
		
		Assert.assertFalse(hvac.isHeatOn());
		Assert.assertTrue(hvac.isCoolOn());
		Assert.assertTrue(hvac.isFanOn());
	}
	
	@Test
	public void fanCantRunFor3MinsAfterCoolerOff() {
		// turning on the cooler
		hvac.setTemp(80);
		controller.tick();

		// turning off the cooler and turn on the heater
		// fan should be off for 3 minutes
		hvac.setTemp(60);
		controller.tick();
		
		Assert.assertTrue(hvac.isHeatOn());
		Assert.assertFalse(hvac.isCoolOn());
		Assert.assertFalse(hvac.isFanOn());

		for (int i = 0; i < 3; i++){
			controller.tick();

			Assert.assertTrue(hvac.isHeatOn());
			Assert.assertFalse(hvac.isCoolOn());
			Assert.assertFalse(hvac.isFanOn());
		}

		controller.tick();
		
		Assert.assertTrue(hvac.isHeatOn());
		Assert.assertFalse(hvac.isCoolOn());
		Assert.assertTrue(hvac.isFanOn());
	}
 
}
