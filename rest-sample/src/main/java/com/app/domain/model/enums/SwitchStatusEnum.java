package com.app.domain.model.enums;

import com.app.domain.model.interfaces.NamedEnum;

// --------------------------------------------------------------------------------------------------------------------------------
public enum SwitchStatusEnum implements NamedEnum
{
	OVR("ovr"),
	OFF("off"),
	AUTO("auto");

	private String name;

	// --------------------------------------------------------------------------------------------------------------------------------
	private SwitchStatusEnum(String name)
	{
		this.name = name;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public String getName()
	{
		return this.name;
	}
}