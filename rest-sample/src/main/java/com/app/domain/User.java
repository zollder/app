package com.app.domain;
 
import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.app.web.utils.Documentation;

//--------------------------------------------------------------------------------------------------------------------------------
/** User entity. */
//--------------------------------------------------------------------------------------------------------------------------------
@Entity
@Table(name="user", uniqueConstraints = { @UniqueConstraint(columnNames = { "userName" }) })
public class User implements Serializable
{
	// Default serial version ID
	private static final long serialVersionUID = 1L;

	@Id
	@Basic
	@Column(name = "primaryKey")
	@Documentation(caption = "Primary Key", comment = "Primary key assigned by the database.")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long primaryKey;
	
	@Basic
	@Column(name = "firstName")
	@Documentation(caption = "First Name", comment = "User's first name.")
	private String firstName;
	
	@Basic
	@Column(name = "lastName")
	@Documentation(caption = "Last Name", comment = "User's last name.")
	private String lastName;

	@Basic
	@Column(name = "userName")
	@Documentation(caption = "Name", comment = "User's identification name.")
	private String userName;

	@Basic
	@Column(name = "password")
	@Documentation(caption = "Password", comment = "User's password used to login.")
	@JsonIgnore
	private String password;

	@Basic
	@Column(name = "email")
	@Documentation(caption = "Email", comment = "User's email.")
	private String email;

	@Basic
	@Column(name = "canLogin")
	@Documentation(caption = "Can Login", comment = "Grants user the ability to login.")
	private Boolean canLogin;

	@Basic
	@Column(name = "isAdmin")
	@Documentation(caption = "Is Administrator", comment = "Grants user administrator access rights.")
	private Boolean isAdmin;

	// --------------------------------------------------------------------------------------------------------------------------------
	//setters & getters
	// --------------------------------------------------------------------------------------------------------------------------------

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
	public String getFirstName()
	{
		return firstName;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public void setFirstName(String fname)
	{
		this.firstName = fname;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public String getLastName()
	{
		return lastName;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public void setLastName(String lname)
	{
		this.lastName = lname;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public String getUserName()
	{
		return userName;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public void setUserName(String username)
	{
		this.userName = username;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public String getPassword()
	{
		return password;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public void setPassword(String password)
	{
		this.password = password;
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
	public Boolean getCanLogin()
	{
		return canLogin;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public void setCanLogin(Boolean canLogin)
	{
		this.canLogin = canLogin;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public Boolean getIsAdmin()
	{
		return isAdmin;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public void setIsAdmin(Boolean isAdmin)
	{
		this.isAdmin = isAdmin;
	}
}