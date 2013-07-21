package com.app.domain.services;

import com.app.domain.model.Device;

public interface DeviceService extends AbstractService<Device>
{
	// --------------------------------------------------------------------------------------------------------------------------------
	/** Loads given device entity by IP address. Returns device object. */
	// --------------------------------------------------------------------------------------------------------------------------------
	public Device loadWithDeviceIp(String ip);

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Loads given device entity by MAC address. Returns device object. */
	// --------------------------------------------------------------------------------------------------------------------------------
	public Device loadWithDeviceMac(String mac);
}