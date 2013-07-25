package com.app.domain.services.impl;

import java.lang.reflect.Constructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.DeviceDao;
import com.app.domain.exceptions.DataNotFoundException;
import com.app.domain.exceptions.NotModifiedException;
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
    /**
     * Creates and saves an instance of device type object obtained via reflection according to specified device type.
     * Returns persisted device object.
     */
    // --------------------------------------------------------------------------------------------------------------------------------
	@Override
	@Transactional
	public Device save(Device device)
	{
		String deviceType = device.getDevType().getName();
		try
		{
			if (device.getPrimaryKey() != null)
				device.setPrimaryKey(null);

			// initialize device type class with custom constructor based on payload's devType
			Class<?> object = Class.forName("com.app.domain.model." + deviceType);
			Constructor<?> constructor = object.getConstructor(Device.class);

			// create and save an instance of device type object
			Object instance = constructor.newInstance(device);
			Object savedInstance = this.save(instance);

			// cast device type instance to Device and return
			return (Device) savedInstance;
		}
		catch (Exception e)	// TODO: revise exception handling in this section
		{
			throw new NotModifiedException(String.format(getClazzName() + " of type " + deviceType + " with name '%s' was not modified", device.getDevName()));
		}
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	@Override
	@Transactional
	public Device update(Device device) throws NotModifiedException
	{
		String deviceType = device.getDevType().getName();
		try
		{
			if (device.getPrimaryKey() != null)
			{
				// initialize device type class with custom constructor based on payload's devType
				Class<?> object = Class.forName("com.app.domain.model." + deviceType);
				Constructor<?> constructor = object.getConstructor(Device.class);

				// convert Device to device type instance and update
				Object instance = constructor.newInstance(device);
				Object savedInstance = this.update(instance);

				// cast device type instance to Device and return
				return (Device) savedInstance;
			}
			else
				throw new IllegalArgumentException(String.format(getClazzName() + ": missing primary key"));
		}
		catch (Exception e)	// TODO: revise exception handling in this section
		{
			throw new NotModifiedException(String.format(getClazzName() + " of type " + deviceType + " with name '%s' was not modified", device.getDevName()));
		}
	}
}
