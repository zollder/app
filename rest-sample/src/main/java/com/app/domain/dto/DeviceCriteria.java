package com.app.domain.dto;


//--------------------------------------------------------------------------------------------------------------------------------
/** DeviceCriteria concrete implementation. */
//--------------------------------------------------------------------------------------------------------------------------------

public class DeviceCriteria extends AbstractCriteria
{
	private static final long serialVersionUID = 1L;

	private String devIp = "";

	private String devMac = "";

	private String devType = "";

	private String devName = "";

	private String devLocation = "";

	// --------------------------------------------------------------------------------------------------------------------------------
	// default constructor
	// --------------------------------------------------------------------------------------------------------------------------------

	public DeviceCriteria()
	{}

	// --------------------------------------------------------------------------------------------------------------------------------
	// setters & getters
	// --------------------------------------------------------------------------------------------------------------------------------

	public String getDevIp()
	{
		return devIp;
	}

	public void setDevIp(String devIp)
	{
		this.devIp = devIp;
	}


	// --------------------------------------------------------------------------------------------------------------------------------
	public String getDevMac()
	{
		return devMac;
	}

	public void setDevMac(String devMac)
	{
		this.devMac = devMac;
	}


	// --------------------------------------------------------------------------------------------------------------------------------
	public String getDevType()
	{
		return devType;
	}

	public void setDevType(String devType)
	{
		this.devType = devType;
	}


	// --------------------------------------------------------------------------------------------------------------------------------
	public String getDevName()
	{
		return devName;
	}

	public void setDevName(String devName)
	{
		this.devName = devName;
	}


	// --------------------------------------------------------------------------------------------------------------------------------
	public String getDevLocation()
	{
		return devLocation;
	}

	public void setDevLocation(String devLocation)
	{
		this.devLocation = devLocation;
	}
}