package com.app.domain.model;
 
import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.app.domain.model.interfaces.PrimaryKeySupport;
import com.app.domain.model.interfaces.VersionSupport;
import com.app.security.LoggedUserFactory;
import com.app.web.utils.Documentation;

//--------------------------------------------------------------------------------------------------------------------------------
/** Abstract base class for entities that require implementing PrimaryKey and Version (coming soon) support. */
//--------------------------------------------------------------------------------------------------------------------------------

// @MappedSuperclass tells Hibernate to apply all mappings defined in the base class to all classes which extend from it.
// It makes the superclass properties persistent.
@MappedSuperclass
@XmlAccessorType(XmlAccessType.FIELD)
@Documentation(caption = "Abstract Base", comment = "Base class for model entities.")
public abstract class AbstractBase<T> implements PrimaryKeySupport, VersionSupport, Serializable
{
	// Default serial version ID
	private static final long serialVersionUID = 1L;

	// TODO: take care about cloning the object, it may cause circular reference while serializing JSON
	
	@Transient
	@JsonIgnore
	protected User loggedUser; // Loaded on demand

	/** Entity attributes */

	@Id
	@Basic
	@Column(name = "primaryKey")
	@Documentation(caption = "Primary Key", comment = "Primary key assigned by the database.")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@XmlElement
	private Long primaryKey;
	

	// --------------------------------------------------------------------------------------------------------------------------------
	//setters & getters
	// --------------------------------------------------------------------------------------------------------------------------------
	
	// --------------------------------------------------------------------------------------------------------------------------------
	public User getLoggedUser()
	{
		if (loggedUser == null)
			loggedUser = LoggedUserFactory.get();
		
		return loggedUser;
	}
	
	// --------------------------------------------------------------------------------------------------------------------------------
	public void setLoggedUser(User loggedUser)
	{
		this.loggedUser = loggedUser;
	}

	public Long getPrimaryKey()
	{
		return primaryKey;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public void setPrimaryKey(Long key)
	{
		this.primaryKey = key;
	}


	// --------------------------------------------------------------------------------------------------------------------------------
	/** Helper functions (for the tests) */
	// --------------------------------------------------------------------------------------------------------------------------------

	@SuppressWarnings("unchecked")
	protected Class<T> indirectGetClass()
	{
		return (Class<T>) this.getClass();
	}
}