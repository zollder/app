package com.app.domain.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.app.dao.DeviceDao;
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
}
