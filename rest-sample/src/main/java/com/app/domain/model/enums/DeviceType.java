package com.app.domain.model.enums;

import com.app.domain.model.interfaces.NamedEnum;

// --------------------------------------------------------------------------------------------------------------------------------
public enum DeviceType implements NamedEnum
{
	TYPE_G("Type-G"),
	TYPE_GM("Type-GM"),
	TYPE_F("Type-F"),
	TYPE_F2("Type-F2"),
	TYPE_F4("Type-F4");

	private String name;

	// --------------------------------------------------------------------------------------------------------------------------------
	private DeviceType(String name)
	{
		this.name = name;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public String getName()
	{
		return this.name;
	}

	public String getDeviceTypes()
	{
		return DeviceType.values().toString();
	}
}