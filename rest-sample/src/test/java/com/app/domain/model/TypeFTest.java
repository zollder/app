package com.app.domain.model;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.BeforeClass;
import org.junit.Test;

import com.app.domain.model.enums.DeviceType;
import com.app.domain.model.enums.DimModeEnum;
import com.app.domain.model.enums.SwitchStatusEnum;
import com.app.domain.model.mocks.MockTypeF;

public class TypeFTest extends BaseTest
{
	private static Validator validator;

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Static initialization */
	// --------------------------------------------------------------------------------------------------------------------------------
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		// Prepare validator
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Verifies an empty {@link TypeF} instance is rejected. */
	// --------------------------------------------------------------------------------------------------------------------------------
	@Test
	public void testDefaultConstructorI()
	{
		TypeF newDevice = new TypeF();
		Set<ConstraintViolation<TypeF>> constraintViolations = validator.validate(newDevice);

		// Expects 3 constraint violations
		assertEquals(3, constraintViolations.size());
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Verifies default parameter values of a new {@link TypeF} instance. */
	// --------------------------------------------------------------------------------------------------------------------------------
	@Test
	public void testDefaultConstructorII()
	{
		TypeF newDevice = new MockTypeF();
		Set<ConstraintViolation<TypeF>> constraintViolations = validator.validate(newDevice);

		// Expects 0 constraint violations
		assertEquals(0, constraintViolations.size());

		// assert superclass fields
		assertEquals(new Long(12345L), newDevice.getPrimaryKey());
		assertEquals("10.10.10.10", newDevice.getDevIp());
		assertEquals("11:22:33:44:55:66", newDevice.getDevMac());
		assertEquals(DeviceType.TYPE_F, newDevice.getDevType());
		assertEquals("testDevice", newDevice.getDevName());
		assertEquals("testLocation", newDevice.getDevLocation());
		assertEquals("1.00", newDevice.getFirmwareVersion());
		assertEquals("testDescription", newDevice.getDevDescription());

		// assert subclass fields
		assertEquals(null, newDevice.getLatchActive());
		assertEquals(null, newDevice.getbPressLapse());
		assertEquals(null, newDevice.getFlickWarn());
		assertEquals(null, newDevice.getFlickReps());
		assertEquals(null, newDevice.getOffDelay());
		assertEquals(null, newDevice.getMotionMuteDelay());
		assertEquals(null, newDevice.getDim());
		assertEquals(null, newDevice.getDimMin());
		assertEquals(DimModeEnum.OFF, newDevice.getDimMode());
		assertEquals(null, newDevice.getInput());
		assertEquals(SwitchStatusEnum.OFF, newDevice.getSwitchStatus());
		assertEquals(null, newDevice.getNetworkOn());
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Verifies parameter values of a new {@link TypeF} instance initialized from the HashMap. */
	// --------------------------------------------------------------------------------------------------------------------------------
	@Test
	public void testCustomConstructor()
	{
		HashMap<String, String> testMap = new HashMap<String,String>();
		testMap.put("primaryKey", new Long(12345L).toString());
		testMap.put("devIp", "10.10.10.10");
		testMap.put("devMac", "11:22:33:44:55:66");
		testMap.put("devType", "TYPE_F");
		testMap.put("devName", "testDevice");
		testMap.put("devLocation", "testLocation");
		testMap.put("devDescription", "testDescription");
		testMap.put("firmwareVersion", "1.00");
		testMap.put("latchActive", "true");
		testMap.put("bPressLapse", "50");
		testMap.put("flickWarn", "true");
		testMap.put("flickReps", "50");
		testMap.put("offDelay", "50");
		testMap.put("motionMuteDelay", "50");
		testMap.put("dim", "50");
		testMap.put("dimMin", "50");
		testMap.put("dimMode", "ONFADE");
		testMap.put("input", "true");
		testMap.put("switchStatus", "AUTO");
		testMap.put("networkOn", "true");
		
		TypeF newDevice = new TypeF(testMap);
		Set<ConstraintViolation<TypeF>> constraintViolations = validator.validate(newDevice);

		// Expects 0 constraint violations
		assertEquals(0, constraintViolations.size());

		// assert superclass fields
		assertEquals(new Long(12345L), newDevice.getPrimaryKey());
		assertEquals("10.10.10.10", newDevice.getDevIp());
		assertEquals("11:22:33:44:55:66", newDevice.getDevMac());
		assertEquals(DeviceType.TYPE_F, newDevice.getDevType());
		assertEquals("testDevice", newDevice.getDevName());
		assertEquals("testLocation", newDevice.getDevLocation());
		assertEquals("1.00", newDevice.getFirmwareVersion());
		assertEquals("testDescription", newDevice.getDevDescription());

		// assert subclass fields
		assertEquals(true, newDevice.getLatchActive());
		assertEquals(Integer.valueOf(50), newDevice.getbPressLapse());
		assertEquals(true, newDevice.getFlickWarn());
		assertEquals(Integer.valueOf(50), newDevice.getFlickReps());
		assertEquals(Integer.valueOf(50), newDevice.getOffDelay());
		assertEquals(Integer.valueOf(50), newDevice.getMotionMuteDelay());
		assertEquals(Integer.valueOf(50), newDevice.getDim());
		assertEquals(Integer.valueOf(50), newDevice.getDimMin());
		assertEquals(DimModeEnum.ONFADE, newDevice.getDimMode());
		assertEquals(true, newDevice.getInput());
		assertEquals(SwitchStatusEnum.AUTO, newDevice.getSwitchStatus());
		assertEquals(true, newDevice.getNetworkOn());
	}
}