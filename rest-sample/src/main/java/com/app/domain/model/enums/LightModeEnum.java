package com.app.domain.model.enums;

import com.app.domain.model.interfaces.NamedEnum;

//--------------------------------------------------------------------------------------------------------------------------------
public enum LightModeEnum implements NamedEnum
{
	CALI24("cali24"),
	AUTOONOFF("autoOnOff");

	private String name;

	// --------------------------------------------------------------------------------------------------------------------------------
	private LightModeEnum(String name)
	{
		this.name = name;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public String getName()
	{
		return this.name;
	}
}