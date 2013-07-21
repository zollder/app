package com.app.domain.model;
 
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.app.domain.model.enums.DeviceType;
import com.app.web.utils.Documentation;

//--------------------------------------------------------------------------------------------------------------------------------
/** Device entity. */
//--------------------------------------------------------------------------------------------------------------------------------
@Entity
@Table(name="device")
@XmlRootElement(name="device")
@XmlAccessorType(XmlAccessType.FIELD)
@Documentation(caption = "Device", comment = "Device entity.")
public class Device extends AbstractBase<Device>
{
	// Default serial version ID
	private static final long serialVersionUID = 1L;

	@Basic
	@Column(name = "devIp", unique=true)
	@Documentation(caption = "IP", comment = "Device's IP address.")
	@NotNull
	@Size(min = 7, max = 15)
	@XmlElement
	private String devIp;

	@Basic
	@Column(name = "devMac", unique=true)
	@Documentation(caption = "MAC address", comment = "MAC address of the device.")
	@Size(min = 17, max = 17)
	@XmlElement
	private String devMac;

	@Basic
	@Column(name = "devType")
	@Enumerated(EnumType.ORDINAL)
	@Documentation(caption = "Device type", comment = "Type (category) of the device.")
	@NotNull
	@XmlElement
	private DeviceType devType;
	
	@Basic
	@Column(name = "devName")
	@Documentation(caption = "Device Name", comment = "Name of the device.")
	@Size(max = 80)
	@XmlElement
	private String devName;

	@Basic
	@Column(name = "devDescription")
	@Documentation(caption = "Description", comment = "Device description containing optional details about the device.")
	@Size(max = 240)
	@XmlElement
	private String devDescription;

	// --------------------------------------------------------------------------------------------------------------------------------
	//setters & getters
	// --------------------------------------------------------------------------------------------------------------------------------

	// --------------------------------------------------------------------------------------------------------------------------------
	public String getDevIp()
	{
		return devIp;
	}

	public void setDevIp(String ip)
	{
		this.devIp = ip;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public String getDevMac()
	{
		return devMac;
	}

	public void setDevMac(String mac)
	{
		this.devMac = mac;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public DeviceType getDevType()
	{
		return devType;
	}

	public void setDevType(DeviceType dType)
	{
		this.devType = dType;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public String getDevName()
	{
		return devName;
	}

	public void setDevName(String name)
	{
		this.devName = name;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public String getDevDescription()
	{
		return devDescription;
	}

	public void setDevDescription(String description)
	{
		this.devDescription = description;
	}
}