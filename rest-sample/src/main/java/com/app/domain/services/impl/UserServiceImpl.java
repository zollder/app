package com.app.domain.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.UserDao;
import com.app.domain.dto.PasswordReset;
import com.app.domain.model.User;
import com.app.domain.services.UserService;

@Service("userService")
public class UserServiceImpl extends AbstractServiceImpl<User, UserDao> implements UserService
{
	@Autowired
	public UserServiceImpl(UserDao userDao)
	{
		super(userDao);
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public Class<User> getModelClass()
	{
		return this.getModelClass();
	}

    // --------------------------------------------------------------------------------------------------------------------------------
    @Override
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

		return super.save(user);
	}
    
    // --------------------------------------------------------------------------------------------------------------------------------
    @Override
    @Transactional
    public User update(User user)
    {
    	// TODO: implement password update/reset (once the UI is ready)
    	String password = loadWithPrimaryKey(user.getPrimaryKey()).getPassword();
    	user.setPassword(password);

    	return super.update(user);
    }
    
    // --------------------------------------------------------------------------------------------------------------------------------
    @Transactional(readOnly = true)
    public User loadWithUserName(String username)
    {
    	User user = getDao().findByUserName(username);
    	return user;
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
