package com.app.domain.model.enums;

import com.app.domain.model.interfaces.NamedEnum;

// --------------------------------------------------------------------------------------------------------------------------------
public enum DeviceAutoModeEnum implements NamedEnum
{
	MAN("man"),
	AUTOONOFF("autoOnOff"),
	MANONAUTOOFF("manOnAutoOff");

	private String name;

	// --------------------------------------------------------------------------------------------------------------------------------
	private DeviceAutoModeEnum(String name)
	{
		this.name = name;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public String getName()
	{
		return this.name;
	}
}