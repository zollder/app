package com.app.domain.dto;
 
import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

//--------------------------------------------------------------------------------------------------------------------------------
/** Person DTO entity. */
//--------------------------------------------------------------------------------------------------------------------------------
@XmlRootElement(name="person")
@XmlAccessorType(XmlAccessType.FIELD)
public class Person implements Serializable
{
	private static final long serialVersionUID = 1327585362175925003L;

	@XmlAttribute
	public String name;

	@XmlElement
	public String address;

	// --------------------------------------------------------------------------------------------------------------------------------
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
	// --------------------------------------------------------------------------------------------------------------------------------
	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}
}