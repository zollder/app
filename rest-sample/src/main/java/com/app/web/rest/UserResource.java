package com.app.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.app.domain.Contact;
import com.app.domain.User;
import com.app.services.UserService;

//-------------------------------------------------------------------------------------------------------------------------------------
@Controller
@RequestMapping(value = "/user")
public class UserResource
{
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
		User user = userService.loadWithPrimaryKey(key);

		return user;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Retrieves {@link User} resource associated to the given key (JSON).
	 * TODO: This resource is required for testing purpose and must not be exposed to client.
	 */
	// --------------------------------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/usr/{username}", method = { RequestMethod.GET })
	@ResponseBody
	public User loadWithUserName(@PathVariable String username)
	{
		User user = userService.loadWithUserName(username);

		return user;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Inserts the {@link User} resource received in the payload. */
	// --------------------------------------------------------------------------------------------------------------------------------
	@RequestMapping(method = { RequestMethod.POST })
	@ResponseBody
	public User save(@RequestBody User user)
	{
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
		user.setPrimaryKey(key);
		User updatedUser = userService.update(user);
		userService.refresh(updatedUser);

		return updatedUser;
	}


	// --------------------------------------------------------------------------------------------------------------------------------
	/** Deletes the {@link Contact} resource associated to a given key. */
	// --------------------------------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/{key}", method = { RequestMethod.DELETE })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long key)
	{
		userService.delete(key);
	}
}