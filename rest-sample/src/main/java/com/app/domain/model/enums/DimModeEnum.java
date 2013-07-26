package com.app.domain.model.enums;

import com.app.domain.model.interfaces.NamedEnum;

// --------------------------------------------------------------------------------------------------------------------------------
public enum DimModeEnum implements NamedEnum
{
	OFF("off"),
	ON("on"),
	ONFADE("onFade");

	private String name;

	// --------------------------------------------------------------------------------------------------------------------------------
	private DimModeEnum(String name)
	{
		this.name = name;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public String getName()
	{
		return this.name;
	}
}