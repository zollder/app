package com.app.domain.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Basic;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.app.domain.model.enums.DeviceType;
import com.app.web.utils.Documentation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

//--------------------------------------------------------------------------------------------------------------------------------
/** Device entity. */
//--------------------------------------------------------------------------------------------------------------------------------
@Entity
@Table(name = "device")
@XmlRootElement(name="devices")
@XmlAccessorType(XmlAccessType.FIELD)
public class Device extends AbstractBase<Device>
{
	// Default serial version ID
	private static final long serialVersionUID = 1L;

	@Size(min = 7, max = 15)
	@Basic
	@Column(name = "dev_ip",unique=true)
	@NotNull
	@Documentation(caption = "IP", comment = "Device IP address.")
	@XmlElement
    private String dev_ip;
  
	@Size(min = 17, max = 1)
	@Basic
	@Column(name = "dev_mac",unique=true)
	@Documentation(caption = "MAC", comment = "Device MAC address.")
	@XmlElement
    private String dev_mac;
	
	@Basic
	@Column(name="dev_type")
	@Enumerated(EnumType.ORDINAL)
	@NotNull
	@Documentation(caption = "Type", comment = "Device Type")
	@XmlElement
    private DeviceType dev_type;


	// --------------------------------------------------------------------------------------------------------------------------------
	public String getDev_ip()
	{
		return dev_ip;
	}

	public void setDev_ip(String ip)
	{
		this.dev_ip = ip;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public String getDev_mac()
	{
		return dev_mac;
	}

	public void setDev_mac(String mac)
	{
		this.dev_mac = mac;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public DeviceType getDev_type()
	{
		return dev_type;
	}

	public void setDev_type(DeviceType type)
	{
		this.dev_type = type;
	}
}