package com.app.domain.model;
 
import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
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
@Inheritance(strategy=InheritanceType.JOINED)
@XmlRootElement(name="device")
@XmlAccessorType(XmlAccessType.FIELD)
@Documentation(caption = "Device", comment = "Device entity: superclass for device type entities.")
public class Device implements Serializable
{
	// Default serial version ID
	private static final long serialVersionUID = 1L;

	@Id
	@Basic
	@Column(name = "primaryKey", unique = true)
	@Documentation(caption = "Primary key",	comment = "Primary key assigned by the database, FK for device type tables.")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@XmlElement
	private Long primaryKey;

	@Basic
	@Column(name = "devIp", unique = true)
	@Documentation(caption = "IP", comment = "Device's IP address (unique).")
	@Size(min = 7, max = 15)
	@NotNull
	@XmlElement
	private String devIp;

	@Basic
	@Column(name = "devMac", unique = true)
	@Documentation(caption = "MAC", comment = "MAC address of the device (unique).")
	@Size(min = 17, max = 17)
	@XmlElement
	private String devMac;

	@Basic
	@Column(name = "devType", updatable = false)
	@Enumerated(EnumType.ORDINAL)
	@Documentation(caption = "Type", comment = "Type (category) of the device.")
	@NotNull
	@XmlElement
	private DeviceType devType;
	
	@Basic
	@Column(name = "devName", unique = true)
	@Documentation(caption = "Name", comment = "Name of the device (unique).")
	@Size(max = 20)
	@NotNull
	@XmlElement
	private String devName;

	@Basic
	@Column(name = "devLocation")
	@Documentation(caption = "Location", comment = "Device Location.")
	@Size(max = 128)
	@XmlElement
	private String devLocation;

	@Basic
	@Column(name = "firmwareVersion")
	@Documentation(caption = "Version", comment = "Version of the device firmware.")
	@Size(max = 4)
	@XmlElement
	private String firmwareVersion;
	
	@Basic
	@Column(name = "devDescription")
	@Documentation(caption = "Description", comment = "Device description containing optional details about the device.")
	@Size(max = 250)
	@XmlElement
	private String devDescription;


	// --------------------------------------------------------------------------------------------------------------------------------
	// Setters & getters
	// --------------------------------------------------------------------------------------------------------------------------------

	public Long getPrimaryKey()
	{
		return primaryKey;
	}

	public void setPrimaryKey(Long pk)
	{
		this.primaryKey = pk;
	}

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
	public String getDevLocation()
	{
		return devLocation;
	}

	public void setDevLocation(String devLocation)
	{
		this.devLocation = devLocation;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public String getFirmwareVersion()
	{
		return firmwareVersion;
	}

	public void setFirmwareVersion(String firmwareVersion)
	{
		this.firmwareVersion = firmwareVersion;
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