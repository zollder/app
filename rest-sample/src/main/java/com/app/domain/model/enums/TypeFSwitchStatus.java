package com.app.domain.model.enums;

import com.app.domain.model.interfaces.NamedEnum;

// --------------------------------------------------------------------------------------------------------------------------------
public enum TypeFSwitchStatus implements NamedEnum
{
	OVR("ovr"),
	OFF("off"),
	AUTO("auto");

	private String name;

	// --------------------------------------------------------------------------------------------------------------------------------
	private TypeFSwitchStatus(String name)
	{
		this.name = name;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public String getName()
	{
		return this.name;
	}
}