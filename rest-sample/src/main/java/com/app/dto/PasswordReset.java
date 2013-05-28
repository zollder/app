package com.app.dto;
 
import java.io.Serializable;

//--------------------------------------------------------------------------------------------------------------------------------
/** ResetPassword DTO entity. */
//--------------------------------------------------------------------------------------------------------------------------------
public class PasswordReset implements Serializable
{
	private static final long serialVersionUID = 1327585362175925002L;

	public String newPassword;
	private String confirmPassword;

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
}