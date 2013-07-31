package com.app.domain.services;

import java.util.HashMap;
import java.util.List;

import com.app.domain.model.Device;

public interface DeviceService extends AbstractService<Device>
{
	// --------------------------------------------------------------------------------------------------------------------------------
	/** Loads given {@link Device} entity by IP address. Returns {@link Device} object. */
	// --------------------------------------------------------------------------------------------------------------------------------
	public Device loadWithDeviceIp(String ip);

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Loads given {@link Device} entity by MAC address. Returns {@link Device} object. */
	// --------------------------------------------------------------------------------------------------------------------------------
	public Device loadWithDeviceMac(String mac);

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Loads all {@link Device} entities of specified type. Returns a list of {@link Device} objects. */
	// --------------------------------------------------------------------------------------------------------------------------------
	public List<Device> loadAllByType(String type);

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Update parameters of the existing {@link Device}. Returns an updated {@link Device} entity. */
	// --------------------------------------------------------------------------------------------------------------------------------
	public Device updateDeviceParameters(HashMap<String,String> params);
}