package com.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.domain.model.User;
import com.app.domain.services.UserService;

//--------------------------------------------------------------------------------------------------------------------------------
/**
 * Security User service. Used by Spring Security to load UserDetails object by userName.
 */
// --------------------------------------------------------------------------------------------------------------------------------
@Service("userSecurityService")
public class UserAuthenticationService implements UserDetailsService
{
	@Autowired
	UserService userService;

	// --------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Spring Security authentication-provider. See security-context.xml for configuration details. Loads a user by userName.
	 * @param userName - embedded in the request header.
	 * @return spring-adapted UserDetails object or throws UsernameNotFoundException
	 */
	// --------------------------------------------------------------------------------------------------------------------------------
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException
	{
			// load user from db
			User user = userService.loadWithUserName(username);
			if (user == null)
				throw new UsernameNotFoundException("User with username " + username + "  has not been found.");

			// and wrap it for Spring Security.
			SpringUserAdapter userToAuthenticate = new SpringUserAdapter(user);

			return userToAuthenticate;
	}
}
