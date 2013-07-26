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
	@Column(name = "ledOnColor")
	@Enumerated(EnumType.ORDINAL)
	@Documentation(caption = "LED color", comment = "Status LED color in ON state.")
	@XmlElement
	private LedOnColorEnum ledOnColor = LedOnColorEnum.NONE;

	@Basic
	@Column(name = "ledOffColor")
	@Enumerated(EnumType.ORDINAL)
	@Documentation(caption = "LED color", comment = "Status LED color in OFF state.")
	@XmlElement
	private LedOffColorEnum ledOffColor = LedOffColorEnum.NONE;

	@Basic
	@Column(name = "switchStatus")
	@Enumerated(EnumType.ORDINAL)
	@Documentation(caption = "Switch status", comment = "Device switch statuses (refer to TypeFSwitchStatus enum definition for details).")
	@NotNull
	@XmlElement
	private SwitchStatusEnum switchStatus = SwitchStatusEnum.OFF;

	@Basic
	@Column(name = "networkOn")
	@Documentation(caption = "Network status", comment = "Status of the network device is connected to: 0(off), 1(on).")
	@XmlElement
	private Boolean networkOn = Boolean.FALSE;

	
	// --------------------------------------------------------------------------------------------------------------------------------
	// Default constructor.
	// --------------------------------------------------------------------------------------------------------------------------------

	public TypeG(){}

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