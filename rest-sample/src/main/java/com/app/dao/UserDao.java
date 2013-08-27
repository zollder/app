package com.app.dao;

import java.util.List;

import com.app.domain.dto.UserCriteria;
import com.app.domain.model.User;

public interface UserDao extends AbstractDao<User>
{
	// --------------------------------------------------------------------------------------------------------------------------------
	/** Loads given User by userName. Returns fetched User. */
	// --------------------------------------------------------------------------------------------------------------------------------
	public User findByUserName(String username);

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Loads a collection of users by specified criteria. */
	// --------------------------------------------------------------------------------------------------------------------------------
	public List<User> findByCriteria(UserCriteria userCriteria);
}