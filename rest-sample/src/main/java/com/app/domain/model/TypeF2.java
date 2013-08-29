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
import com.app.domain.model.enums.LightModeEnum;
import com.app.domain.model.enums.SwitchStatusEnum;
import com.app.domain.model.enums.DeviceAutoModeEnum;
import com.app.web.utils.Documentation;



//--------------------------------------------------------------------------------------------------------------------------------
/** Type-F2 entity. */
//--------------------------------------------------------------------------------------------------------------------------------

@Entity
@Table(name="type_f2")
@XmlRootElement(name="type_f2")
@XmlAccessorType(XmlAccessType.FIELD)
@Documentation(caption = "Type-F2 device", comment = "Type-F2 device: device ceiling mount oBIX variables.")
public class TypeF2 extends Device
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
	
	@Basic
	@Column(name = "switchStatus2")
	@Enumerated(value = EnumType.STRING)
	@Documentation(caption = " Second Switch status", comment = "Device switch status 2: Read/Write (refer to TypeFSwitchStatus enum definition for details).")
	@NotNull
	@XmlElement
	private SwitchStatusEnum switchStatus2 = SwitchStatusEnum.OFF;
	
	@Basic
	@Column(name = "latchActive2")
	@Documentation(caption = "Second Relay status", comment = "Status of the relay latch 2: Read/Write ( true= closed Relay , false= Open Relay ).")
	@NotNull
	@XmlElement
	private Boolean latchActive2 = Boolean.FALSE;
	
	@Basic
	@Column(name = "offDelay2")
	@Documentation(caption = "Turn-off delay2", comment = "Turn-off delay2: Read/Write ( Amount of Seconds with no movement before lights turn OFF, min= 0 , max= 65535).")
	@XmlElement
	private Integer offDelay2 = null;
	
	@Basic
	@Column(name = "flickWarn2")
	@Documentation(caption = "Flick warn second relay", comment = "Flick warn 2: Read/Write (Perform a flick warn by toggling the relay, true = enable , false = disable ).")
	@XmlElement
	private Boolean flickWarn2 = Boolean.FALSE;

	@Basic
	@Column(name = "flickReps2")
	@Documentation(caption = "Flick repetitions second flick warn", comment = "Flick repetitions 2: Read/Write ( Counter for the FlickWarn, min= 0 , max= 65535 ).")
	@XmlElement
	private Integer flickReps2 = 2;
	
	@Basic
	@Column( name = "autoMode2")
	@Documentation( caption = "device mode", comment = "Auto mode 2: Read/Write (refer to the deviceAutoMode enum definition for details).")
	@NotNull
	@XmlElement
	private DeviceAutoModeEnum autoMode2 = DeviceAutoModeEnum.MAN;
	
	@Basic
	@Column( name = "lightMode")
	@Documentation( caption = "Light mode", comment = "Light Mode: Read/Write (refer to the typeFLightModeEnum enum definition for details).")
	@NotNull
	@XmlElement
	private LightModeEnum lightMode = LightModeEnum.CALI24;
	
	// --------------------------------------------------------------------------------------------------------------------------------
	// Default constructor.
	// --------------------------------------------------------------------------------------------------------------------------------

	public TypeF2(){}
	
	// --------------------------------------------------------------------------------------------------------------------------------
	// Custom device constructor: initializes superclass values before persisting device type object.
	// --------------------------------------------------------------------------------------------------------------------------------
	public TypeF2(Device device)
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

	// --------------------------------------------------------------------------------------------------------------------------------
	public SwitchStatusEnum getSwitchStatus2()
	{
		return switchStatus2;
	}

	public void setSwitchStatus2(SwitchStatusEnum switchStatus2)
	{
		this.switchStatus2 = switchStatus2;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public Boolean getLatchActive2()
	{
		return latchActive2;
	}

	public void setLatchActive2(Boolean latchActive2)
	{
		this.latchActive2 = latchActive2;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public Integer getOffDelay2()
	{
		return offDelay2;
	}

	public void setOffDelay2(Integer offDelay2)
	{
		this.offDelay2 = offDelay2;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public Boolean getFlickWarn2()
	{
		return flickWarn2;
	}

	public void setFlickWarn2(Boolean flickWarn2)
	{
		this.flickWarn2 = flickWarn2;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public Integer getFlickReps2()
	{
		return flickReps2;
	}

	public void setFlickReps2(Integer flickReps2)
	{
		this.flickReps2 = flickReps2;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public DeviceAutoModeEnum getAutoMode2()
	{
		return autoMode2;
	}

	public void setAutoMode2(DeviceAutoModeEnum autoMode2)
	{
		this.autoMode2 = autoMode2;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public LightModeEnum getLightMode()
	{
		return lightMode;
	}

	public void setLightMode(LightModeEnum lightMode)
	{
		this.lightMode = lightMode;
	}
	
}//END
