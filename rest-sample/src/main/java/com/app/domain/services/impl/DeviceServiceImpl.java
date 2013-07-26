package com.app.domain.services.impl;

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
}
