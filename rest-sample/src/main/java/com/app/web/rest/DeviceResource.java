package com.app.web.rest;

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
import com.app.domain.model.TypeF;
import com.app.domain.services.DeviceService;
import com.app.domain.services.TypeFService;

//-------------------------------------------------------------------------------------------------------------------------------------
@Controller
@RequestMapping(value = "/device")
public class DeviceResource
{
	private static Logger logger = Logger.getLogger(DeviceResource.class);
	// --------------------------------------------------------------------------------------------------------------------------------
	@Autowired
	private DeviceService deviceService;

	@Autowired
	private TypeFService typeFService;


	// ========================= Common Device resources =========================

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
	/** TODO: Retrieves {@link Device} resource associated to the given IP address.
	 * Implement IP address decoder to decode incoming IP address from Integer into String (design) */
	// --------------------------------------------------------------------------------------------------------------------------------
/*	@RequestMapping(value = "/ip/{ip}", method = { RequestMethod.GET })
	@ResponseBody
	public Device loadWithDeviceIp(@PathVariable String ip)
	{
		logger.info("load device with device ip:" + ip);
		Device device = deviceService.loadWithDeviceIp(ip);

		return device;
	}
*/

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


	// ========================= Type-F Device resources =========================

	// --------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Inserts {@link TypeF} device resource.
	 * Creates device of specified type and associated device parameters (default) in the TypeF table. 
	 */
	// --------------------------------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/typef", method = { RequestMethod.POST })
	@ResponseBody
	public TypeF save(@RequestBody TypeF device)
	{
		logger.info("saving device with device name:" + device.getDevName());
		TypeF savedDevice = typeFService.save(device);
		typeFService.refresh(savedDevice);

		return savedDevice;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Updates the {@link Device} resource. */
	// --------------------------------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/typef/{key}", method = { RequestMethod.PUT })
	@ResponseBody
	public TypeF update(@PathVariable Long key, @RequestBody TypeF device)
	{
		logger.info("update device with name:" + device.getDevName());
		device.setPrimaryKey(key);
		TypeF updatedDevice = typeFService.update(device);
		typeFService.refresh(updatedDevice);

		return updatedDevice;
	}
}