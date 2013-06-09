package com.app.dao;

import com.app.domain.model.User;

public interface UserDao extends AbstractDao<User>
{
	// --------------------------------------------------------------------------------------------------------------------------------
	/** Loads given User by userName. Returns fetched User. */
	// --------------------------------------------------------------------------------------------------------------------------------
	public User findByUserName(String username);

	// --------------------------------------------------------------------------------------------------------------------------------
	/** TODO: revise to make it generic. Loads given User by criteria. Returns fetched User. */
	// --------------------------------------------------------------------------------------------------------------------------------
	public User findByCriteria(String username);
}