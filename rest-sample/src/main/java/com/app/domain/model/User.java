package com.app.domain.model;
 
import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.validator.constraints.Email;

import com.app.domain.dto.PasswordReset;
import com.app.domain.services.UserService;
import com.app.security.PasswordEncoder;
import com.app.security.PasswordEncoderFactory;
import com.app.web.utils.Documentation;

//--------------------------------------------------------------------------------------------------------------------------------
/** User entity. */
//--------------------------------------------------------------------------------------------------------------------------------

@Entity
@Table(name="user")
@Documentation(caption = "User", comment = "Rest-sample application user.")
public class User implements Serializable
{
	// Default serial version ID
	private static final long serialVersionUID = 1L;

	@Id
	@Basic
	@Column(name = "primaryKey")
	@Documentation(caption = "Primary Key", comment = "Primary key assigned by the database.")
	@GeneratedValue(strategy = GenerationType.AUTO)
	//@XmlElement
	private Long primaryKey;

	@Basic
	@Column(name = "firstName")
	@Documentation(caption = "First Name", comment = "User's first name.")
	@NotNull
	@Size(max = 80)
	private String firstName;

	@Basic
	@Column(name = "lastName")
	@Documentation(caption = "Last Name", comment = "User's last name.")
	@NotNull
	@Size(max = 80)
	private String lastName;

	@Basic
	@Column(name = "userName", unique = true)
	@Documentation(caption = "Name", comment = "User's identification name.")
	@NotNull
	@Size(max = 40)
	private String userName;

	@Basic
	@Column(name = "password")
	@Documentation(caption = "Password", comment = "User's password used to login.")
	@NotNull
	@Size(max = 40)
	//@JsonIgnore
	private String password;

	@Basic
	@Column(name = "email")
	@Documentation(caption = "Email", comment = "User's email.")
	@NotNull
	@Size(max = 120)
	@Email
	private String email;

	@Basic
	@Column(name = "isEnabled")
	@Documentation(caption = "Is enabled", comment = "Enables the user.")
	@NotNull
	private Boolean isEnabled;
	
	@Basic
	@Column(name = "canLogin")
	@Documentation(caption = "Can Login", comment = "Grants user the ability to login.")
	@NotNull
	private Boolean canLogin;

	@Basic
	@Column(name = "isAdmin")
	@Documentation(caption = "Is Administrator", comment = "Grants user administrator access rights.")
	@NotNull
	private Boolean isAdmin;

	/** Used by UI and transformed into a password by passwordEncoder */
	@Transient
	private String newPassword;

	@Transient
	private String confirmPassword;

	@Transient
	@JsonIgnore
	protected PasswordEncoder passwordEncoder;

	// TODO: implement resetPassword and changePassword
	// --------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Resets password for user, observing standard password changing rules.
	 * TODO: Make this method available to 'administrators' only	 * 
	 * @param passwordReset data from the UI, which is currently mocked using the password field from the User payload
	 * @throws InvalidRequestException if the password update fails
	 * 
	 * @see UserService#resetPassword(Long, PasswordReset)
	 */
	// --------------------------------------------------------------------------------------------------------------------------------
	public void resetPassword(PasswordReset passwordReset)
	{
		// TODO: add password validation

		String encodedPassword = this.getPasswordEncoder().encodePassword(this.getPassword());
		this.setPassword(encodedPassword);

		this.setNewPassword(null);
		this.setConfirmPassword(null);
	}
	// --------------------------------------------------------------------------------------------------------------------------------
	//setters & getters
	// --------------------------------------------------------------------------------------------------------------------------------

	public Long getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(Long primaryKey) {
		this.primaryKey = primaryKey;
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
	public Boolean getIsEnabled()
	{
		return isEnabled;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public void setIsEnabled(Boolean isEnabled)
	{
		this.isEnabled = isEnabled;
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

	// --------------------------------------------------------------------------------------------------------------------------------
	public String getNewPassword()
	{
		return newPassword;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public void setNewPassword(String newPassword)
	{
		this.newPassword = newPassword;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public String getConfirmPassword()
	{
		return confirmPassword;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public void setConfirmPassword(String confirmPassword)
	{
		this.confirmPassword = confirmPassword;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public PasswordEncoder getPasswordEncoder()
	{
		if (passwordEncoder == null)
			passwordEncoder = PasswordEncoderFactory.getPasswordEncoder();

		return passwordEncoder;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public void setPasswordEncoder(PasswordEncoder passwordEncoder)
	{
		this.passwordEncoder = passwordEncoder;
	}
}