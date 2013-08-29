package com.app.domain.model;
 
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

import com.app.domain.model.enums.DimModeEnum;
import com.app.domain.model.enums.SwitchStatusEnum;
import com.app.web.utils.Documentation;

//--------------------------------------------------------------------------------------------------------------------------------
/** Type-F entity. */
//--------------------------------------------------------------------------------------------------------------------------------

@Entity
@Table(name="type_f")
@XmlRootElement(name="type_f")
@XmlAccessorType(XmlAccessType.FIELD)
@Documentation(caption = "Type-F device", comment = "Type-F device: device ceiling mount oBIX variables.")
public class TypeF extends Device
{
	// Default serial version ID
	private static final long serialVersionUID = 1L;

	@Basic
	@Column(name = "latchActive")
	@Documentation(caption = "Latch status", comment = "Status of the latch: ( true= closed Relay , false= Open Relay ).")
	@NotNull
	@XmlElement
	private Boolean latchActive = Boolean.FALSE;

	@Basic
	@Column(name = "bPressLapse")
	@Documentation(caption = "Lapse", comment = "Button press delay: ReadOnly ( min= 0 , max= 65535 ).")
	@XmlElement
	private Integer bPressLapse = null;

	@Basic
	@Column(name = "flickWarn")
	@Documentation(caption = "Flick warn", comment = "Flick warn: Read/Write (Perform a flick warn by toggling the relay, true = enable , false = disable ).")
	@XmlElement
	private Boolean flickWarn = Boolean.FALSE;

	@Basic
	@Column(name = "flickReps")
	@Documentation(caption = "Flick repetitions", comment = "Flick repetitions: Read/Write ( Counter for the FlickWarn, min= 0 , max= 65535 ).")
	@XmlElement
	private Integer flickReps = 2;

	@Basic
	@Column(name = "offDelay")
	@Documentation(caption = "Turn-off delay", comment = "Turn-off delay: Read/Write ( Amount of Seconds with no movement before lights turn OFF, min= 0 , max= 65535).")
	@XmlElement
	private Integer offDelay = null;
	
	@Basic
	@Column(name = "motionMuteDelay")
	@Documentation(caption = "Motion mute delay", comment = "Motion mute delay: Read/Write( Amount of seconds before accepting occupansy sensor readings,  min= 0 , max= 65535).")
	@XmlElement
	private Integer motionMuteDelay = null;

	@Basic
	@Column(name = "dim")
	@Documentation(caption = "Dim to level", comment = "Dim: Read/Write ( Dims the lights, Outputs voltage to the corresponding value, min= 0 , max= 1000 , 1000 = 10 volt).")
	@XmlElement
	private Integer dim = null;

	@Basic
	@Column(name = "dimMin")
	@Documentation(caption = "Minimum dimming level", comment = "Dim Minimum: Read/Write ( Does not dim the light below this value when relay OFF, min= 0 , max= 1000 , 1000 ).")
	@XmlElement
	private Integer dimMin = null;

	@Basic
	@Column(name = "dimMode")
	@Enumerated(value = EnumType.STRING)
	@Documentation(caption = "Dim mode", comment = "Device dim modes: Read/Write (refer to TypeFDimMode enum definition for details).")
	@NotNull
	@XmlElement
	private DimModeEnum dimMode = DimModeEnum.OFF;

	@Basic
	@Column(name = "input")
	@Documentation(caption = "Input Occupancy Sensor", comment = "Device input: Read Only ( Status: 0 = no motion detected, 1= motion detected by external sensor).")
	@XmlElement
	private Boolean input = null;

	@Basic
	@Column(name = "switchStatus")
	@Enumerated(value = EnumType.STRING)
	@Documentation(caption = "Switch status", comment = "Device switch status: Read/Write (refer to TypeFSwitchStatus enum definition for details).")
	@NotNull
	@XmlElement
	private SwitchStatusEnum switchStatus = SwitchStatusEnum.OFF;

	@Basic
	@Column(name = "networkOn")
	@Documentation(caption = "Network status", comment = "Network Status: Read Only ( Status of the device, 0 = Offline, 1 = Online).")
	@XmlElement
	private Boolean networkOn = Boolean.FALSE;
	
	// --------------------------------------------------------------------------------------------------------------------------------
	// Default constructor.
	// --------------------------------------------------------------------------------------------------------------------------------

	public TypeF(){}

	// --------------------------------------------------------------------------------------------------------------------------------
	// Custom device constructor: initializes superclass values before persisting device type object.
	// --------------------------------------------------------------------------------------------------------------------------------
	public TypeF(Device device)
	{
		if (device.getPrimaryKey() != null)
			this.setPrimaryKey(device.getPrimaryKey());

		this.setDevIp(device.getDevIp());
		this.setDevMac(device.getDevMac());
		this.setDevType(device.getDevType());
		this.setDevName(device.getDevName());
		this.setDevLocation(device.getDevLocation());
		this.setDevDescription(device.getDevDescription());
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
	public Integer getOffDelay()
	{
		return offDelay;
	}

	public void setOffDelay(Integer offDelay)
	{
		this.offDelay = offDelay;
	}


	// --------------------------------------------------------------------------------------------------------------------------------
	public Integer getMotionMuteDelay()
	{
		return motionMuteDelay;
	}

	public void setMotionMuteDelay(Integer motionMuteDelay)
	{
		this.motionMuteDelay = motionMuteDelay;
	}


	// --------------------------------------------------------------------------------------------------------------------------------
	public Integer getDim()
	{
		return dim;
	}

	public void setDim(Integer dim)
	{
		this.dim = dim;
	}


	// --------------------------------------------------------------------------------------------------------------------------------
	public Integer getDimMin()
	{
		return dimMin;
	}

	public void setDimMin(Integer dimMin)
	{
		this.dimMin = dimMin;
	}


	// --------------------------------------------------------------------------------------------------------------------------------
	public DimModeEnum getDimMode()
	{
		return dimMode;
	}

	public void setDimMode(DimModeEnum dimMode)
	{
		this.dimMode = dimMode;
	}


	// --------------------------------------------------------------------------------------------------------------------------------
	public Boolean getInput()
	{
		return input;
	}

	public void setInput(Boolean input)
	{
		this.input = input;
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