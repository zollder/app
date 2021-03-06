package com.app.web.rest;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.app.domain.model.Device;
import com.app.domain.services.DeviceService;

//-------------------------------------------------------------------------------------------------------------------------------------
@Controller
@RequestMapping(value = "/device")
public class DeviceResource
{
	private static Logger logger = Logger.getLogger(DeviceResource.class);
	// --------------------------------------------------------------------------------------------------------------------------------
	@Autowired
	private DeviceService deviceService;

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Retrieves {@link Device} resource associated to the given key. */
	// --------------------------------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/{key}", method = { RequestMethod.GET })
	@ResponseBody
	public Device loadWithPrimaryKey(@PathVariable Long key)
	{
		logger.info("load device with primary key:" + key);
		Device device = deviceService.loadWithPrimaryKey(key);

		return device;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Retrieves {@link Device} resource associated to the given MAC address. */
	// --------------------------------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/mac/{mac}", method = { RequestMethod.GET })
	@ResponseBody
	public Device loadWithDeviceMac(@PathVariable String mac)
	{
		logger.info("load device with device mac:" + mac);
		Device device = deviceService.loadWithDeviceMac(mac);

		return device;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Retrieves a collection of all {@link Device} resources of specified type. */
	// --------------------------------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/type/{type}", method = { RequestMethod.GET })
	@ResponseBody
	public List<Device> loadWithDeviceType(@PathVariable String type)
	{
		logger.info("loading all devices of specified type");
		List<Device> devices = deviceService.loadAllByType(type);

		return devices;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Retrieves a collection of all {@link Device} resources. */
	// --------------------------------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/all", method = { RequestMethod.GET })
	@ResponseBody
	public List<Device> loadAllDevices()
	{
		logger.info("loading all devices");
		List<Device> devices = deviceService.findAll();

		return devices;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Deletes {@link Device} resource and associated device parameters by a given key. */
	// --------------------------------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/{key}", method = { RequestMethod.DELETE })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long key)
	{
		logger.info("delete device with primary key:" + key);
		deviceService.delete(key);
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Inserts {@link Device} resource received in the payload.
	 * Creates device parameters with default values in the associated device type table. 
	 */
	// --------------------------------------------------------------------------------------------------------------------------------
	@RequestMapping(method = { RequestMethod.POST })
	@ResponseBody
	public Device save(@RequestBody Device device)
	{
		logger.info("saving device with device name:" + device.getDevName());
		Device savedDevice = deviceService.save(device);
		deviceService.refresh(savedDevice);

		return savedDevice;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Updates {@link Device} resource and its associated parameters. */
	// --------------------------------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/{key}", method = { RequestMethod.PUT })
	@ResponseBody
	public Device update(@PathVariable Long key, @RequestBody Device device)
	{
		logger.info("update device with name:" + device.getDevName());
		device.setPrimaryKey(key);
		Device updatedDevice = deviceService.update(device);
		deviceService.refresh(updatedDevice);

		return updatedDevice;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Updates {@link Device} and its associated parameters. */
	// --------------------------------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/params", method = { RequestMethod.PUT })
	@ResponseBody
	public Device update(@RequestBody HashMap<String,String> params)
	{
		Device updatedDevice = deviceService.updateDeviceParameters(params);
		deviceService.refresh(updatedDevice);

		return updatedDevice;
	}
}