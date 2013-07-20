package com.app.domain.model.enums;
import com.app.domain.model.interfaces.NamedEnum;


//--------------------------------------------------------------------------------------------------------------------------------
/** Device Type Enum */
//--------------------------------------------------------------------------------------------------------------------------------

public enum DeviceType implements NamedEnum
{
	TYPE_G("Type-G"),
	TYPE_GM("Type-GM"),
	TYPE_F("Type-F"),
	TYPE_F2("Type-F2"),
	TYPE_F4("Type-F4");

	private String type;

	// --------------------------------------------------------------------------------------------------------------------------------
	private DeviceType(String type) 
	{
	this.type = type;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public String getType()
	{
		return this.type;
	}
}