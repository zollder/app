package com.app.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.UserDao;
import com.app.domain.User;
import com.app.dto.PasswordReset;
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
		if (user.getPrimaryKey() == null)
		{
			PasswordReset passwordReset = new PasswordReset();
			passwordReset.setNewPassword(user.getPassword());
			passwordReset.setConfirmPassword(user.getPassword());
			user.resetPassword(passwordReset);
		}

		User savedUser = userDao.save(user);

		return savedUser;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	@Transactional
	public User update(User user)
	{
		// TODO: implement password update/reset (once the UI is ready)
		String password = loadWithPrimaryKey(user.getPrimaryKey()).getPassword();
		user.setPassword(password);
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

	// --------------------------------------------------------------------------------------------------------------------------------
	@Transactional
	public User resetPassword(Long primaryKey, PasswordReset resetPassword)
	{
		User entity = loadWithPrimaryKey(primaryKey);
		entity.resetPassword(resetPassword);
		update(entity);

		return entity;
	}
}
