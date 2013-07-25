package com.app.domain.model.enums;

import com.app.domain.model.interfaces.NamedEnum;

// --------------------------------------------------------------------------------------------------------------------------------
public enum DeviceType implements NamedEnum
{
	TYPE_G("TypeG"),
	TYPE_GM("TypeGM"),
	TYPE_F("TypeF"),
	TYPE_F2("TypeF2"),
	TYPE_F4("TypeF4");

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
}