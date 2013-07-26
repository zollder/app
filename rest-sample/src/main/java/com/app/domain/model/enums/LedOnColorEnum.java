package com.app.domain.model.enums;

import com.app.domain.model.interfaces.NamedEnum;

// --------------------------------------------------------------------------------------------------------------------------------
public enum LedOnColorEnum implements NamedEnum
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
	private LedOnColorEnum(String name)
	{
		this.name = name;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public String getName()
	{
		return this.name;
	}
}