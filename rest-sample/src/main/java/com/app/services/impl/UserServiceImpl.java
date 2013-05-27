package com.app.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.UserDao;
import com.app.domain.User;
import com.app.services.UserService;

@Service("userService")
public class UserServiceImpl implements UserService
{
    @Autowired
    private UserDao userDao;

    // --------------------------------------------------------------------------------------------------------------------------------
    @Transactional(readOnly = true)
	public User loadWithPrimaryKey(Long key)
	{
		User userEntity = userDao.loadWithPrimaryKey(key);

		return userEntity;
	}

    // --------------------------------------------------------------------------------------------------------------------------------
    @Transactional(readOnly = true)
	public User loadWithUserName(String username)
	{
		User user = userDao.findByUserName(username);
		return user;
	}

    // --------------------------------------------------------------------------------------------------------------------------------
	@Transactional
	public User save(User user)
	{
		User savedUser = userDao.save(user);

		return savedUser;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	@Transactional
	public User update(User user)
	{
		User updatedUser = userDao.update(user);

		return updatedUser;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	@Transactional
	public void delete(Long key)
	{
			userDao.delete(key);
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Refreshes saved User so ensure correct serialisation/marshalling (especially for @ManyToOne relations) */
	// --------------------------------------------------------------------------------------------------------------------------------
	public void refresh(User savedUser)
	{
		userDao.refresh(savedUser);		
	}
}
