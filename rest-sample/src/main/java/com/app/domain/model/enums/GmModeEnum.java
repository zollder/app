package com.app.domain.model.enums;

import com.app.domain.model.interfaces.NamedEnum;

// --------------------------------------------------------------------------------------------------------------------------------
public enum GmModeEnum implements NamedEnum
{
	MAN("man"),
	AUTO_ON_OFF("autoOnOff"),
	MAN_ON_AUTO_OFF("manOnAutoOff");

	private String name;

	// --------------------------------------------------------------------------------------------------------------------------------
	private GmModeEnum(String name)
	{
		this.name = name;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public String getName()
	{
		return this.name;
	}
}