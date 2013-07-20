package com.app.dao;

import com.app.domain.model.Device;
import com.app.domain.model.enums.DeviceType;

public interface DeviceDao extends AbstractDao<Device>
{
	// --------------------------------------------------------------------------------------------------------------------------------
	/** Loads given Device by userName. Returns fetched Device. */
	// --------------------------------------------------------------------------------------------------------------------------------
	public Device findByIp(String ip);

	// --------------------------------------------------------------------------------------------------------------------------------
	/** TODO: revise to make it generic. Loads given User by criteria. Returns fetched Device. */
	// --------------------------------------------------------------------------------------------------------------------------------
	public Device findByType(DeviceType type);
}