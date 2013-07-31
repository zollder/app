package com.app.domain.model;
 
import java.util.HashMap;
import java.lang.Long;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import com.app.domain.model.enums.DeviceType;
import com.app.domain.model.enums.DimModeEnum;
import com.app.domain.model.enums.SwitchStatusEnum;
import com.app.web.utils.Documentation;

//--------------------------------------------------------------------------------------------------------------------------------
/** Type-F entity. */
//--------------------------------------------------------------------------------------------------------------------------------

@Entity
@Table(name="type_f")
@Documentation(caption = "Type-F device", comment = "Type-F device: device ceiling mount oBIX variables.")
public class TypeF extends Device
{
	// Default serial version ID
	private static final long serialVersionUID = 1L;

	@Basic
	@Column(name = "latchActive")
	@Documentation(caption = "Latch status", comment = "Status of the latch: 1(active), 0(inactive).")
	private Boolean latchActive = null;

	@Basic
	@Column(name = "bPressLapse")
	@Documentation(caption = "Lapse", comment = "Button press delay (TODO: provide more details here).")
	private Integer bPressLapse = null;

	@Basic
	@Column(name = "flickWarn")
	@Documentation(caption = "Flick warn", comment = "Flick warn (TODO: provide more details here).")
	private Boolean flickWarn = null;

	@Basic
	@Column(name = "flickReps")
	@Documentation(caption = "Flick repetitions", comment = "Flick repetitions (TODO: provide more details here).")
	private Integer flickReps = null;

	@Basic
	@Column(name = "offDelay")
	@Documentation(caption = "Turn-off delay", comment = "Time delay before the device is turned off.")
	private Integer offDelay = null;
	
	@Basic
	@Column(name = "motionMuteDelay")
	@Documentation(caption = "Motion mute delay", comment = "Time delay before the motion sensor is muted.")
	private Integer motionMuteDelay = null;

	@Basic
	@Column(name = "dim")
	@Documentation(caption = "Dim level", comment = "Dim level of the device.")
	private Integer dim = null;

	@Basic
	@Column(name = "dimMin")
	@Documentation(caption = "Minimal dim level", comment = "Minimal dim level of the device.")
	private Integer dimMin = null;

	@Basic
	@Column(name = "dimMode")
	@Enumerated(value = EnumType.STRING)
	@Documentation(caption = "Dim mode", comment = "Device dim modes (refer to TypeFDimMode enum definition for details).")
	@NotNull
	private DimModeEnum dimMode = DimModeEnum.OFF;

	@Basic
	@Column(name = "input")
	@Documentation(caption = "Input status", comment = "Device input status: 0(input off), 1(input on).")
	private Boolean input = null;

	@Basic
	@Column(name = "switchStatus")
	@Enumerated(value = EnumType.STRING)
	@Documentation(caption = "Switch status", comment = "Device switch statuses (refer to TypeFSwitchStatus enum definition for details).")
	@NotNull
	private SwitchStatusEnum switchStatus = SwitchStatusEnum.OFF;

	@Basic
	@Column(name = "networkOn")
	@Documentation(caption = "Network status", comment = "Status of the network device is connected to: 0(off), 1(on).")
	private Boolean networkOn = null;

	
	// --------------------------------------------------------------------------------------------------------------------------------
	// Default constructor.
	// --------------------------------------------------------------------------------------------------------------------------------

	public TypeF(){}

	// --------------------------------------------------------------------------------------------------------------------------------
	// Custom constructor: initializes superclass variables from {@link Device}.
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
		this.setFirmwareVersion(device.getFirmwareVersion());
		this.setDevDescription(device.getDevDescription());
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	// Custom constructor: initializes super/sub class variables from HashMap (update parameters).
	// --------------------------------------------------------------------------------------------------------------------------------
	public TypeF(HashMap<String,String> params)
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
		value = params.get("offDelay");
		if (value != null && value != "")	this.setOffDelay(Integer.valueOf(value));
		value = params.get("motionMuteDelay");
		if (value != null && value != "")	this.setMotionMuteDelay(Integer.valueOf(value));
		value = params.get("dim");
		if (value != null && value != "")	this.setDim(Integer.valueOf(value));
		value = params.get("dimMin");
		if (value != null && value != "")	this.setDimMin(Integer.valueOf(value));
		value = params.get("dimMode");
		if (value != null && value != "")	this.setDimMode(DimModeEnum.valueOf(value));
		value = params.get("input");
		if (value != null && value != "")	this.setInput(Boolean.valueOf(value));
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