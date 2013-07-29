package com.app.dao;

import java.util.List;

import com.app.domain.model.Device;

public interface DeviceDao extends AbstractDao<Device>
{
	// --------------------------------------------------------------------------------------------------------------------------------
	/** Loads given {@link Device} by IP. Returns {@link Device} entity. */
	// --------------------------------------------------------------------------------------------------------------------------------
	public Device findByIp(String ip);

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Loads given {@link Device} by MAC. Returns {@link Device} entity. */
	// --------------------------------------------------------------------------------------------------------------------------------
	public Device findByMac(String mac);

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Loads all {@link Device} entities by type. Returns a list of {@link Device} entities. */
	// --------------------------------------------------------------------------------------------------------------------------------
	public List<Device> findAllByType(String type);
}