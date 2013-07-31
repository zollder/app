package com.app.domain.model;
 
import java.util.HashMap;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.app.domain.model.enums.DeviceType;
import com.app.domain.model.enums.LedOffColorEnum;
import com.app.domain.model.enums.LedOnColorEnum;
import com.app.domain.model.enums.SwitchStatusEnum;
import com.app.web.utils.Documentation;

//--------------------------------------------------------------------------------------------------------------------------------
/** Type-G entity. */
//--------------------------------------------------------------------------------------------------------------------------------

@Entity
@Table(name="type_g")
@XmlRootElement(name="type_g")
@XmlAccessorType(XmlAccessType.FIELD)
@Documentation(caption = "Type-G device", comment = "Type-G device.")
public class TypeG extends Device
{
	// Default serial version ID
	private static final long serialVersionUID = 1L;

	@Basic
	@Column(name = "latchActive")
	@Documentation(caption = "Latch status", comment = "Status of the latch: 1(active), 0(inactive).")
	@XmlElement
	private Boolean latchActive = null;

	@Basic
	@Column(name = "bPressLapse")
	@Documentation(caption = "Lapse", comment = "Button press delay (TODO: provide more details here).")
	@XmlElement
	private Integer bPressLapse = null;

	@Basic
	@Column(name = "flickWarn")
	@Documentation(caption = "Flick warn", comment = "Flick warn (TODO: provide more details here).")
	@XmlElement
	private Boolean flickWarn = null;

	@Basic
	@Column(name = "flickReps")
	@Documentation(caption = "Flick repetitions", comment = "Flick repetitions (TODO: provide more details here).")
	@XmlElement
	private Integer flickReps = null;
	
	@Basic
	@Column(name = "ledOnColor")
	@Enumerated(value = EnumType.STRING)
	@Documentation(caption = "LED color", comment = "Status LED color in ON state.")
	@XmlElement
	private LedOnColorEnum ledOnColor = LedOnColorEnum.NONE;

	@Basic
	@Column(name = "ledOffColor")
	@Enumerated(value = EnumType.STRING)
	@Documentation(caption = "LED color", comment = "Status LED color in OFF state.")
	@XmlElement
	private LedOffColorEnum ledOffColor = LedOffColorEnum.NONE;

	@Basic
	@Column(name = "switchStatus")
	@Enumerated(value = EnumType.STRING)
	@Documentation(caption = "Switch status", comment = "Device switch statuses (refer to TypeFSwitchStatus enum definition for details).")
	@NotNull
	@XmlElement
	private SwitchStatusEnum switchStatus = SwitchStatusEnum.OFF;

	@Basic
	@Column(name = "networkOn")
	@Documentation(caption = "Network status", comment = "Status of the network device is connected to: 0(off), 1(on).")
	@XmlElement
	private Boolean networkOn = null;

	
	// --------------------------------------------------------------------------------------------------------------------------------
	// Default constructor.
	// --------------------------------------------------------------------------------------------------------------------------------

	public TypeG(){}

	// --------------------------------------------------------------------------------------------------------------------------------
	// Custom device constructor: initializes superclass values before persisting device type object.
	// --------------------------------------------------------------------------------------------------------------------------------
	public TypeG(Device device)
	{
		if (device.getPrimaryKey() != null)
			this.setPrimaryKey(device.getPrimaryKey());

		this.setDevIp(device.getDevIp());
		this.setDevMac(device.getDevMac());
		this.setDevType(device.getDevType());
		this.setDevName(device.getDevName());
		this.setDevLocation(device.getDevLocation());
		this.setFirmwareVersion(device.getFirmwareVersion());
		this.setDevDescription(device.getDevDescription());
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	// Custom constructor: initializes super/sub class variables from HashMap (update parameters).
	// --------------------------------------------------------------------------------------------------------------------------------
	public TypeG(HashMap<String,String> params)
	{
		String value ="";

		// map {@link Device} mandatory fields
		this.setPrimaryKey(Long.valueOf(params.get("primaryKey").toString()));
		this.setDevIp(params.get("devIp"));
		this.setDevMac(params.get("devMac"));
		this.setDevType(DeviceType.valueOf(params.get("devType")));
		this.setDevName(params.get("devName"));

		// map {@link Device} optional fields
		value = params.get("devLocation");
		if (value != null && value != "")	this.setDevLocation(value);
		value = params.get("firmwareVersion");
		if (value != null && value != "")	this.setFirmwareVersion(value);
		value = params.get("devDescription");
		if (value != null && value != "")	this.setDevDescription(value);

		// map {@link TypeF} optional fields
		value = params.get("latchActive");
		if (value != null && value != "")	this.setLatchActive(Boolean.valueOf(value));
		value = params.get("bPressLapse");
		if (value != null && value != "")	this.setbPressLapse(Integer.valueOf(value));
		value = params.get("flickWarn");
		if (value != null && value != "")	this.setFlickWarn(Boolean.valueOf(value));
		value = params.get("flickReps"); 
		if (value != null && value != "")	this.setFlickReps(Integer.valueOf(value));
		value = params.get("ledOnColor"); 
		if (value != null && value != "")	this.setLedOnColor(LedOnColorEnum.valueOf(value));
		value = params.get("ledOffColor"); 
		if (value != null && value != "")	this.setLedOffColor(LedOffColorEnum.valueOf(value));
		value = params.get("switchStatus"); 
		if (value != null && value != "")	this.setSwitchStatus(SwitchStatusEnum.valueOf(value));
		value = params.get("networkOn"); 
		if (value != null && value != "")	this.setNetworkOn(Boolean.valueOf(value));
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	// Setters & getters
	// --------------------------------------------------------------------------------------------------------------------------------

	public Boolean getLatchActive()
	{
		return latchActive;
	}

	public void setLatchActive(Boolean latchActive)
	{
		this.latchActive = latchActive;
	}


	// --------------------------------------------------------------------------------------------------------------------------------
	public Integer getbPressLapse()
	{
		return bPressLapse;
	}

	public void setbPressLapse(Integer bPressLapse)
	{
		this.bPressLapse = bPressLapse;
	}


	// --------------------------------------------------------------------------------------------------------------------------------
	public Boolean getFlickWarn()
	{
		return flickWarn;
	}

	public void setFlickWarn(Boolean flickWarn)
	{
		this.flickWarn = flickWarn;
	}


	// --------------------------------------------------------------------------------------------------------------------------------
	public Integer getFlickReps()
	{
		return flickReps;
	}

	public void setFlickReps(Integer flickReps)
	{
		this.flickReps = flickReps;
	}


	// --------------------------------------------------------------------------------------------------------------------------------
	public LedOnColorEnum getLedOnColor()
	{
		return ledOnColor;
	}

	public void setLedOnColor(LedOnColorEnum ledOnColor)
	{
		this.ledOnColor = ledOnColor;
	}


	// --------------------------------------------------------------------------------------------------------------------------------
	public LedOffColorEnum getLedOffColor()
	{
		return ledOffColor;
	}

	public void setLedOffColor(LedOffColorEnum ledOffColor)
	{
		this.ledOffColor = ledOffColor;
	}


	// --------------------------------------------------------------------------------------------------------------------------------
	public SwitchStatusEnum getSwitchStatus()
	{
		return switchStatus;
	}

	public void setSwitchStatus(SwitchStatusEnum switchStatus)
	{
		this.switchStatus = switchStatus;
	}


	// --------------------------------------------------------------------------------------------------------------------------------
	public Boolean getNetworkOn()
	{
		return networkOn;
	}

	public void setNetworkOn(Boolean networkOn)
	{
		this.networkOn = networkOn;
	}
}