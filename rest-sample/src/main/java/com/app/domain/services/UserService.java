package com.app.domain.services;

import com.app.domain.model.User;
import com.app.domain.dto.PasswordReset;

public interface UserService extends AbstractService<User>
{
	// --------------------------------------------------------------------------------------------------------------------------------
	/** Loads given user entity by userName. Returns fetched user. */
	// --------------------------------------------------------------------------------------------------------------------------------
	public User loadWithUserName(String userName);

	// --------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Resets the password for the User entity having the given primary key.
	 * @param primaryKey - user account identifier.
	 * @param resetPassword - data provided by the UI for the password reset
	 */
	// --------------------------------------------------------------------------------------------------------------------------------
	public User resetPassword(Long primaryKey, PasswordReset resetPassword);
}