package com.app.domain.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.app.domain.model.enums.DeviceType;
import com.app.domain.model.mocks.MockDevice;

public class DeviceTest extends BaseTest
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

	private Device testDevice = null;

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Class instance initialization */
	// --------------------------------------------------------------------------------------------------------------------------------
	@Before
	public void setUp() throws Exception
	{
		// Valid Device entity that will be altered field by field in the test functions below
		testDevice = new MockDevice();
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Verifies an empty {@link Device} instance is rejected */
	// --------------------------------------------------------------------------------------------------------------------------------
	@Test
	public void testConstructor()
	{
		Device newDevice = new Device();
		Set<ConstraintViolation<Device>> constraintViolations = validator.validate(newDevice);

		// Expects 3 constraint violations
		assertEquals(3, constraintViolations.size());
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Verifies a valid {@link Device} is accepted */
	// --------------------------------------------------------------------------------------------------------------------------------
	@Test
	public void testValidUser()
	{
		testDevice = new MockDevice();
		Set<ConstraintViolation<Device>> constraintViolations = validator.validate(testDevice);

		// 0 constraint violations
		assertEquals(0, constraintViolations.size());
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Verifies a {@link Device} without primakyKey is accepted. */
	// --------------------------------------------------------------------------------------------------------------------------------
	@Test
	public void testDeviceWithoutPrimaryKey()
	{
		testDevice.setPrimaryKey(null);
		assertNull(testDevice.getPrimaryKey());

		Set<ConstraintViolation<Device>> constraintViolations = validator.validate(testDevice);

		// 0 constraint violations, as the PrimaryKey can be <null> at creation
		assertEquals(0, constraintViolations.size());
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Verifies a {@link Device} without IP address is rejected (decorated with @NotNull). */
	// --------------------------------------------------------------------------------------------------------------------------------
	@Test
	public void testDeviceWithoutIpAddress()
	{
		testDevice.setDevIp(null);
		assertNull(testDevice.getDevIp());

		Set<ConstraintViolation<Device>> constraintViolations = validator.validate(testDevice);

		// 1 constraint violation
		assertEquals(1, constraintViolations.size());
		ConstraintViolation<Device> violation = constraintViolations.iterator().next();
		assertEquals("devIp", violation.getPropertyPath().toString());
		assertEquals("may not be null", violation.getMessage());
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Verifies a {@link Device} with wrong (short) IP address is rejected (decorated with @Size(min = 7, max = 15)). */
	// --------------------------------------------------------------------------------------------------------------------------------
	@Test
	public void testDeviceWithShortIpAddress()
	{
		testDevice.setDevIp("1.1.1");
		assertNotNull(testDevice.getDevIp());

		Set<ConstraintViolation<Device>> constraintViolations = validator.validate(testDevice);

		// 1 constraint violation
		assertEquals(1, constraintViolations.size());
		ConstraintViolation<Device> violation = constraintViolations.iterator().next();
		assertEquals("devIp", violation.getPropertyPath().toString());
		assertEquals("size must be between 7 and 15", violation.getMessage());
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Verifies a {@link Device} with wrong (long) IP address is rejected (decorated with @Size(min = 7, max = 15)). */
	// --------------------------------------------------------------------------------------------------------------------------------
	@Test
	public void testDeviceWithLongIpAddress()
	{
		testDevice.setDevIp("255.255.255.2555");
		assertNotNull(testDevice.getDevIp());

		Set<ConstraintViolation<Device>> constraintViolations = validator.validate(testDevice);

		// 1 constraint violation
		assertEquals(1, constraintViolations.size());
		ConstraintViolation<Device> violation = constraintViolations.iterator().next();
		assertEquals("devIp", violation.getPropertyPath().toString());
		assertEquals("size must be between 7 and 15", violation.getMessage());
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Verifies a {@link Device} without MAC address is accepted. */
	// --------------------------------------------------------------------------------------------------------------------------------
	@Test
	public void testDeviceWithoutMacAddress()
	{
		testDevice.setDevMac(null);
		assertNull(testDevice.getDevMac());

		Set<ConstraintViolation<Device>> constraintViolations = validator.validate(testDevice);

		// 0 constraint violations, as the MAC address can be <null> at creation
		assertEquals(0, constraintViolations.size());
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Verifies a {@link Device} with wrong (short) MAC address is rejected (decorated with @Size(min = 17, max = 17)). */
	// --------------------------------------------------------------------------------------------------------------------------------
	@Test
	public void testDeviceWithShortMacAddress()
	{
		testDevice.setDevMac("ab:cd:ef:gh:i");
		assertNotNull(testDevice.getDevMac());

		Set<ConstraintViolation<Device>> constraintViolations = validator.validate(testDevice);

		// 1 constraint violation
		assertEquals(1, constraintViolations.size());
		ConstraintViolation<Device> violation = constraintViolations.iterator().next();
		assertEquals("devMac", violation.getPropertyPath().toString());
		assertEquals("size must be between 17 and 17", violation.getMessage());
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Verifies a {@link Device} with wrong (long) MAC address is rejected (decorated with @Size(min = 17, max = 17)). */
	// --------------------------------------------------------------------------------------------------------------------------------
	@Test
	public void testDeviceWithLongMacAddress()
	{
		testDevice.setDevMac("ab:cd:ef:gh:ij:k");
		assertNotNull(testDevice.getDevMac());

		Set<ConstraintViolation<Device>> constraintViolations = validator.validate(testDevice);

		// 1 constraint violation
		assertEquals(1, constraintViolations.size());
		ConstraintViolation<Device> violation = constraintViolations.iterator().next();
		assertEquals("devMac", violation.getPropertyPath().toString());
		assertEquals("size must be between 17 and 17", violation.getMessage());
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Verifies a {@link Device} without {@link DeviceType} is rejected (decorated with @NotNull) */
	// --------------------------------------------------------------------------------------------------------------------------------
	@Test
	public void testDeviceWithoutDeviceType()
	{
		testDevice.setDevType(null);
		assertNull(testDevice.getDevType());

		Set<ConstraintViolation<Device>> constraintViolations = validator.validate(testDevice);

		// 1 constraint violation
		assertEquals(1, constraintViolations.size());
		ConstraintViolation<Device> violation = constraintViolations.iterator().next();
		assertEquals("devType", violation.getPropertyPath().toString());
		assertEquals("may not be null", violation.getMessage());
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Verifies a {@link Device} with wrong {@link DeviceType} is rejected (decorated with @Enumerated) */
	// --------------------------------------------------------------------------------------------------------------------------------
	@Test(expected = IllegalArgumentException.class)
	public void testDeviceWithWrongDeviceType()
	{
		testDevice.setDevType(DeviceType.valueOf("WRONG"));
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Verifies a {@link Device} without devName is rejected. */
	// --------------------------------------------------------------------------------------------------------------------------------
	@Test
	public void testDeviceWithoutDevName()
	{
		testDevice.setDevName(null);
		assertNull(testDevice.getDevName());

		Set<ConstraintViolation<Device>> constraintViolations = validator.validate(testDevice);

		// 1 constraint violation
		assertEquals(1, constraintViolations.size());
		ConstraintViolation<Device> violation = constraintViolations.iterator().next();
		assertEquals("devName", violation.getPropertyPath().toString());
		assertEquals("may not be null", violation.getMessage());
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Verifies a {@link Device} with wrong (long) name is rejected (decorated with @Size(max = 20)). */
	// --------------------------------------------------------------------------------------------------------------------------------
	@Test
	public void testDeviceWithLongName()
	{
		testDevice.setDevName("veeeery_loooong_deviiiiiice_naaaaaame");
		assertNotNull(testDevice.getDevName());

		Set<ConstraintViolation<Device>> constraintViolations = validator.validate(testDevice);

		// 1 constraint violation
		assertEquals(1, constraintViolations.size());
		ConstraintViolation<Device> violation = constraintViolations.iterator().next();
		assertEquals("devName", violation.getPropertyPath().toString());
		assertEquals("size must be between 0 and 20", violation.getMessage());
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Verifies a {@link Device} with wrong (long) firmware version is rejected (decorated with @Size(max = 4)). */
	// --------------------------------------------------------------------------------------------------------------------------------
	@Test
	public void testDeviceWithLongVersion()
	{
		testDevice.setFirmwareVersion("1.0.0.1");
		assertNotNull(testDevice.getFirmwareVersion());

		Set<ConstraintViolation<Device>> constraintViolations = validator.validate(testDevice);

		// 1 constraint violation
		assertEquals(1, constraintViolations.size());
		ConstraintViolation<Device> violation = constraintViolations.iterator().next();
		assertEquals("firmwareVersion", violation.getPropertyPath().toString());
		assertEquals("size must be between 0 and 4", violation.getMessage());
	}
}