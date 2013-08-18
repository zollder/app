package com.app.web.rest;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.app.domain.dto.PasswordReset;
import com.app.domain.model.User;
import com.app.domain.services.UserService;

//-------------------------------------------------------------------------------------------------------------------------------------
@Controller
@RequestMapping(value = "/user")
public class UserResource
{
	private static Logger logger = Logger.getLogger(UserResource.class);
	// --------------------------------------------------------------------------------------------------------------------------------
	@Autowired
	private UserService userService;

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Retrieves {@link User} resource associated to the given key (JSON). */
	// --------------------------------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/{key}", method = { RequestMethod.GET })
	@ResponseBody
	public User loadWithPrimaryKey(@PathVariable Long key)
	{
		logger.info("load user with primary key:" + key);
		User user = userService.loadWithPrimaryKey(key);

		return user;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Retrieves {@link User} resource associated to the given key (JSON).
	 * TODO: This resource is required for testing purpose only and must not be exposed to client.
	 */
	// --------------------------------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/usr/{username}", method = { RequestMethod.GET })
	@ResponseBody
	public User loadWithUserName(@PathVariable String username)
	{
		logger.info("load user with username:" + username);
		User user = userService.loadWithUserName(username);

		return user;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Retrieves all Users. Returns a collection of {@link User} entities.
	 */
	// --------------------------------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/all", method = { RequestMethod.GET })
	@ResponseBody
	public List<User> loadAll()
	{
		List<User> user = userService.findAll();

		return user;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Inserts the {@link User} resource received in the payload. */
	// --------------------------------------------------------------------------------------------------------------------------------
	@RequestMapping(method = { RequestMethod.POST })
	@ResponseBody
	public User save(@RequestBody User user)
	{
		logger.info("saving user with username:" + user.getUserName());
		User savedUser = userService.save(user);
		userService.refresh(savedUser);

		return savedUser;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Updates the {@link User} resource. */
	// --------------------------------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/{key}", method = { RequestMethod.PUT })
	@ResponseBody
	public User update(@PathVariable Long key, @RequestBody User user)
	{
		logger.info("update user with username:" + user.getUserName());
		user.setPrimaryKey(key);
		User updatedUser = userService.update(user);
		userService.refresh(updatedUser);

		return updatedUser;
	}


	// --------------------------------------------------------------------------------------------------------------------------------
	/** Deletes the {@link User} resource associated to a given key. */
	// --------------------------------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/{key}", method = { RequestMethod.DELETE })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long key)
	{
		logger.info("delete user with primary key:" + key);
		userService.delete(key);
	}


	// --------------------------------------------------------------------------------------------------------------------------------
	/** Resets {@link User}'s password resource having the given key.
	 */
	// --------------------------------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/{key}/password", params= {"reset=true"}, method = RequestMethod.PUT)
	@ResponseBody
	public User resetPassword(@PathVariable Long key, @RequestBody PasswordReset passwordReset)
	{
		logger.info("resetting password for user with primary key:" + key);
		User user = userService.resetPassword(key, passwordReset);
		userService.refresh(user);
		return user;
	}
}