package com.app.domain.model.enums;

import com.app.domain.model.interfaces.NamedEnum;

// --------------------------------------------------------------------------------------------------------------------------------
public enum LedOffColorEnum implements NamedEnum
{
	NONE("none"),
	RED("red"),
	GREEN("green"),
	BLUE("blue"),
	AMBER("amber"),
	CYCLE("cycle"),
	TOGGLE("toggle");

	private String name;

	// --------------------------------------------------------------------------------------------------------------------------------
	private LedOffColorEnum(String name)
	{
		this.name = name;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public String getName()
	{
		return this.name;
	}
}