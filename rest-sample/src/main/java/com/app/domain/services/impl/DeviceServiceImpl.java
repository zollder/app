package com.app.domain.services.impl;

import java.lang.reflect.Constructor;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.DeviceDao;
import com.app.domain.exceptions.DataNotFoundException;
import com.app.domain.model.Device;
import com.app.domain.services.DeviceService;

@Service("deviceService")
public class DeviceServiceImpl extends AbstractServiceImpl<Device, DeviceDao> implements DeviceService
{
	@Autowired
	public DeviceServiceImpl(DeviceDao deviceDao)
	{
		super(deviceDao);
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public Class<Device> getModelClass()
	{
		return this.getModelClass();
	}

    // --------------------------------------------------------------------------------------------------------------------------------
    @Transactional(readOnly = true)
    public Device loadWithDeviceIp(String ip)
    {
    	Device device = getDao().findByIp(ip);

		if (device == null)
			throw new DataNotFoundException(String.format(getClazzName() + " with ip '%s' not found", ip));

    	return device;
    }

	// --------------------------------------------------------------------------------------------------------------------------------
    @Transactional(readOnly = true)
    public Device loadWithDeviceMac(String mac)
    {
    	Device device = getDao().findByMac(mac);

		if (device == null)
			throw new DataNotFoundException(String.format(getClazzName() + " with mac '%s' not found", mac));

    	return device;
    }

    // --------------------------------------------------------------------------------------------------------------------------------
    @Transactional(readOnly = true)
	public List<Device> loadAllByType(String type)
	{
		if (type == null || type == "")
			throw new IllegalArgumentException(String.format(getClazzName() + ": missing device type"));

		List<Device> devices = getDao().findAllByType(type);

		return devices;
	}


    // --------------------------------------------------------------------------------------------------------------------------------
    /**
     * Creates and saves an instance of device type object obtained via reflection according to specified device type.
     * Returns device type object casted to Device.
     */
    // --------------------------------------------------------------------------------------------------------------------------------
	@Override
	@Transactional
	public Device save(Device device)
	{
		// nullify primaryKey in case it's not null for the device to be saved
		if (device.getPrimaryKey() != null)
			device.setPrimaryKey(null);

		// save device type instance, cast it to Device and return
		Object savedInstance = entityDao.save(this.getDeviceTypeInstance(device));

		return (Device) savedInstance;
	}

    // --------------------------------------------------------------------------------------------------------------------------------
    /**
     * Updates existing device type object with reflected device type instance.
     * Returns device type object casted to Device.
     */
    // --------------------------------------------------------------------------------------------------------------------------------
	@Override
	@Transactional
	public Device update(Device device)
	{
		if (device.getPrimaryKey() == null)
			throw new IllegalArgumentException(String.format(getClazzName() + ": missing device id"));

		// update device type, cast updated instance to Device and return
		Object savedInstance = entityDao.update(this.getDeviceTypeInstance(device));

		return (Device) savedInstance;
	}

    // --------------------------------------------------------------------------------------------------------------------------------
    /**
     * Creates device type reflection of Device based on specified devType.
     * Returns device type reflection (Object).
     */
    // --------------------------------------------------------------------------------------------------------------------------------
	public Object getDeviceTypeInstance(Device device)
	{
		if (device.getDevType() == null)
			throw new IllegalArgumentException(String.format(getClazzName() + ": missing device type"));

		Object instance = null;
		try
		{
			// initialize device type class with custom constructor based on payload's devType
			String deviceType = device.getDevType().getName();
			Class<?> object = Class.forName("com.app.domain.model." + deviceType);
			Constructor<?> constructor = object.getConstructor(Device.class);

			// convert Device to device type instance
			instance = constructor.newInstance(device);
		}
		// TODO: add proper handling to ControllerAdvice
		catch (ReflectiveOperationException e)
		{	e.printStackTrace();	}
		catch (RuntimeException e)
		{	e.printStackTrace();	}

		return instance;
	}
}
