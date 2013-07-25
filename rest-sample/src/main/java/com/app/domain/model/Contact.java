package com.app.domain.model;
 
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.app.web.utils.Documentation;
import com.app.web.utils.JsonDateSerializer;

//--------------------------------------------------------------------------------------------------------------------------------
/** Contact entity. */
//--------------------------------------------------------------------------------------------------------------------------------

@Entity
@Table(name="contacts")
@XmlRootElement(name="contact")
@XmlAccessorType(XmlAccessType.FIELD)
public class Contact implements Serializable
{
	// TODO: add validation annotations
	// Default serial version ID
	private static final long serialVersionUID = 1L;

	 @Id
	 @Basic
	 @Column(name = "id")
	 @Documentation(caption = "id", comment = "identification code.")
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 @XmlElement
	 private Long id;

	 @Basic
	 @Column(name = "name")
	 @XmlElement
	 private String name;

	 @Basic
	 @Column(name = "address")
	 @XmlElement
	 private String address;

	 @Basic
	 @Column(name = "gender")
	 @XmlElement
	 private String gender;

	 @Column(name = "dob")
	 @JsonSerialize(using = JsonDateSerializer.class)
	 @XmlElement
	 @XmlSchemaType(name = "date")
	 private Date dob;

	 @Basic
	 @Column(name = "email")
	 @XmlElement
	 private String email;

	 @Basic
	 @Column(name = "mobile")
	 @XmlElement
	 private String mobile;

	 @Basic
	 @Column(name = "phone")
	 @XmlElement
	 private String phone;

	// --------------------------------------------------------------------------------------------------------------------------------
	//setters & getters
	// --------------------------------------------------------------------------------------------------------------------------------
	public Long getId()
	{
		return id;
	}
	
	// --------------------------------------------------------------------------------------------------------------------------------
	public void setId(Long id)
	{
		this.id = id;
	}
	
	// --------------------------------------------------------------------------------------------------------------------------------
	public String getName()
	{
		return name;
	}
	
	// --------------------------------------------------------------------------------------------------------------------------------
	public void setName(String name)
	{
		this.name = name;
	}
	
	// --------------------------------------------------------------------------------------------------------------------------------
	public String getAddress()
	{
		return address;
	}
	
	// --------------------------------------------------------------------------------------------------------------------------------
	public void setAddress(String address)
	{
		this.address = address;
	}
	
	// --------------------------------------------------------------------------------------------------------------------------------
	public String getGender()
	{
		return gender;
	}
	
	// --------------------------------------------------------------------------------------------------------------------------------
	public void setGender(String gender)
	{
		this.gender = gender;
	}
	
	// --------------------------------------------------------------------------------------------------------------------------------
	public Date getDob()
	{
		return dob;
	}
	
	// --------------------------------------------------------------------------------------------------------------------------------
	public void setDob(Date dob)
	{
		this.dob = dob;
	}
	
	// --------------------------------------------------------------------------------------------------------------------------------
	public String getEmail()
	{
		return email;
	}
	
	// --------------------------------------------------------------------------------------------------------------------------------
	public void setEmail(String email)
	{
		this.email = email;
	}
	
	// --------------------------------------------------------------------------------------------------------------------------------
	public String getMobile()
	{
		return mobile;
	}
	
	// --------------------------------------------------------------------------------------------------------------------------------
	public void setMobile(String mobile)
	{
		this.mobile = mobile;
	}
	
	// --------------------------------------------------------------------------------------------------------------------------------
	public String getPhone()
	{
		return phone;
	}
	
	// --------------------------------------------------------------------------------------------------------------------------------
	public void setPhone(String phone)
	{
		this.phone = phone;
	}
}