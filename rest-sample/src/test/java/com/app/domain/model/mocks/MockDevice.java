package com.app.domain.model.mocks;

import com.app.domain.model.Device;
import com.app.domain.model.enums.DeviceType;

public class MockDevice extends Device
{
	private static final long serialVersionUID = 1L;

	private static Long key = new Long(12345L);
	private static String devIp = "10.10.10.10";
	private static String devMac = "11:22:33:44:55:66";
	private static DeviceType devType = DeviceType.TYPE_F;
	private static String devName = "testDevice";
	private static String devLocation = "testLocation";
	private static String devVersion = "1.00";
	private static String devDescription = "testDescription";

	// prepare mock device object with predefined field values
	private static Device testDevice = new Device();

	static
	{
		testDevice.setPrimaryKey(key);
		testDevice.setDevIp(devIp);
		testDevice.setDevMac(devMac);
		testDevice.setDevType(devType);
		testDevice.setDevName(devName);
		testDevice.setDevLocation(devLocation);
		testDevice.setFirmwareVersion(devVersion);
		testDevice.setDevDescription(devDescription);
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Constructor */
	// --------------------------------------------------------------------------------------------------------------------------------
	public MockDevice()
	{
		// Set valid field values
		setPrimaryKey(key);
		setDevIp(devIp);
		setDevMac(devMac);
		setDevType(devType);
		setDevName(devName);
		setDevLocation(devLocation);
		setFirmwareVersion(devVersion);
		setDevDescription(devDescription);
	}
}
