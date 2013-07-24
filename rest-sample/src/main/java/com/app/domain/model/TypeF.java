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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.FetchType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.app.domain.model.enums.TypeFDimMode;
import com.app.domain.model.enums.TypeFSwitchStatus;
import com.app.web.utils.Documentation;

//--------------------------------------------------------------------------------------------------------------------------------
/** Device entity. */
//--------------------------------------------------------------------------------------------------------------------------------
@Entity
@Table(name="type-f")
@XmlRootElement(name="type-f")
@XmlAccessorType(XmlAccessType.FIELD)
@Documentation(caption = "Type-F device", comment = "Type-F device: device ceiling mount oBIX variables.")
public class TypeF implements Serializable
{
	// Default serial version ID
	private static final long serialVersionUID = 1L;

	@Id
	@Basic
	@Column(name = "primaryKey")
	@Documentation(caption = "Primary Key", comment = "Primary key assigned by the database.")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@XmlElement
	private Long primaryKey;

	@Basic
	@OneToOne(targetEntity = Device.class, fetch = FetchType.LAZY)
	@Documentation(caption = "Device ID", comment = "Primary key of the associated device record (reference).")
	@NotNull
	@XmlElement
	private Long devId;

	@Basic
	@Column(name = "latchActive")
	@Documentation(caption = "Latch status", comment = "Status of the latch: 1(active), 0(inactive).")
	@XmlElement
	private Boolean latchActive = Boolean.FALSE;

	@Basic
	@Column(name = "bPressLapse")
	@Documentation(caption = "Lapse", comment = "Button press delay (TODO: provide more details here).")
	@XmlElement
	private Integer bPressLapse = null;

	@Basic
	@Column(name = "flickWarn")
	@Documentation(caption = "Flick warn", comment = "Flick warn (TODO: provide more details here).")
	@XmlElement
	private Boolean flickWarn = Boolean.FALSE;

	@Basic
	@Column(name = "flickReps")
	@Documentation(caption = "Flick repetitions", comment = "Flick repetitions (TODO: provide more details here).")
	@XmlElement
	private Integer flickReps = null;

	@Basic
	@Column(name = "offDelay")
	@Documentation(caption = "Turn-off delay", comment = "Time delay before the device is turned off.")
	@XmlElement
	private Integer offDelay = null;
	
	@Basic
	@Column(name = "motionMuteDelay")
	@Documentation(caption = "Motion mute delay", comment = "Time delay before the motion sensor is muted.")
	@XmlElement
	private Integer motionMuteDelay = null;

	@Basic
	@Column(name = "dim")
	@Documentation(caption = "Dim level", comment = "Dim level of the device.")
	@XmlElement
	private Integer dim = null;

	@Basic
	@Column(name = "dimMin")
	@Documentation(caption = "Minimal dim level", comment = "Minimal dim level of the device.")
	@XmlElement
	private Integer dimMin = null;

	@Basic
	@Column(name = "dimMode")
	@Enumerated(EnumType.ORDINAL)
	@Documentation(caption = "Dim mode", comment = "Device dim modes (refer to TypeFDimMode enum definition for details).")
	@NotNull
	@XmlElement
	private TypeFDimMode dimMode = TypeFDimMode.OFF;

	@Basic
	@Column(name = "input")
	@Documentation(caption = "Input status", comment = "Device input status: 0(input off), 1(input on).")
	@XmlElement
	private Boolean input = null;

	@Basic
	@Column(name = "switchStatus")
	@Enumerated(EnumType.ORDINAL)
	@Documentation(caption = "Switch status", comment = "Device switch statuses (refer to TypeFSwitchStatus enum definition for details).")
	@NotNull
	@XmlElement
	private TypeFSwitchStatus switchStatus = TypeFSwitchStatus.OFF;

	@Basic
	@Column(name = "networkOn")
	@Documentation(caption = "Network status", comment = "Status of the network device is connected to: 0(off), 1(on).")
	@XmlElement
	private Boolean networkOn = Boolean.FALSE;

	// --------------------------------------------------------------------------------------------------------------------------------
	//setters & getters
	// --------------------------------------------------------------------------------------------------------------------------------

	// --------------------------------------------------------------------------------------------------------------------------------
	public Long getPrimaryKey()
	{
		return primaryKey;
	}

	public void setPrimaryKey(Long primaryKey)
	{
		this.primaryKey = primaryKey;
	}


	// --------------------------------------------------------------------------------------------------------------------------------
	public Long getDevId()
	{
		return devId;
	}

	public void setDevId(Long id)
	{
		this.devId = id;
	}


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
	public TypeFDimMode getDimMode()
	{
		return dimMode;
	}

	public void setDimMode(TypeFDimMode dimMode)
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
	public TypeFSwitchStatus getSwitchStatus()
	{
		return switchStatus;
	}

	public void setSwitchStatus(TypeFSwitchStatus switchStatus)
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