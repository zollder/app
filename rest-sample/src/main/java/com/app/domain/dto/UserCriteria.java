package com.app.domain.dto;

//--------------------------------------------------------------------------------------------------------------------------------
/** UserCriteria concrete implementation. */
//--------------------------------------------------------------------------------------------------------------------------------

public class UserCriteria extends AbstractCriteria
{
	private static final long serialVersionUID = 1L;

	private String firstName;
	private String lastName;
	private String userName;
	private String email;
	private Boolean canLogin;
	private Boolean isAdmin;
	private Boolean isEnabled;

	// --------------------------------------------------------------------------------------------------------------------------------
	// default constructor
	// --------------------------------------------------------------------------------------------------------------------------------

	public UserCriteria()
	{}

	// --------------------------------------------------------------------------------------------------------------------------------
	// setters & getters
	// --------------------------------------------------------------------------------------------------------------------------------

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	// -------------------------------------------------------------------------------------------------------------------------------
	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	// -------------------------------------------------------------------------------------------------------------------------------
	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	// -------------------------------------------------------------------------------------------------------------------------------
	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	// -------------------------------------------------------------------------------------------------------------------------------
	public Boolean getCanLogin()
	{
		return canLogin;
	}

	public void setCanLogin(Boolean canLogin)
	{
		this.canLogin = canLogin;
	}

	// -------------------------------------------------------------------------------------------------------------------------------
	public Boolean getIsAdmin()
	{
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin)
	{
		this.isAdmin = isAdmin;
	}

	// -------------------------------------------------------------------------------------------------------------------------------
	public Boolean getIsEnabled()
	{
		return isEnabled;
	}

	public void setIsEnabled(Boolean isEnabled)
	{
		this.isEnabled = isEnabled;
	}
}