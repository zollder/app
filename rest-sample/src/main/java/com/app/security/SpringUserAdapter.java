package com.app.security;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.app.domain.model.User;

//--------------------------------------------------------------------------------------------------------------------------------
/**
 * Adapts an application user into a Spring UserDetails/
 */
// --------------------------------------------------------------------------------------------------------------------------------
public class SpringUserAdapter implements UserDetails
{
	private static final long serialVersionUID = 157489563221541L;

	/** User (from the database) **/
	private User dbUser;

	/** Spring attributes needed in the UserDetails contract **/
	private String username;
	private String password;
	private boolean enabled;
	private HashSet<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();

	// --------------------------------------------------------------------------------------------------------------------------------
	public SpringUserAdapter(User user)
	{
		this.dbUser = user;
		this.username = user.getUserName();
		this.password = user.getPassword();
		this.enabled = user.getIsEnabled();
		this.authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
	}

    public User getUser()
    {
        return this.dbUser;
    }
	
	// --------------------------------------------------------------------------------------------------------------------------------
    public Collection<GrantedAuthority> getAuthorities()
    {
        return this.authorities;
    }

	// --------------------------------------------------------------------------------------------------------------------------------
	public String getPassword()
	{
		return this.password;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public String getUsername()
	{
		return this.username;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public boolean isAccountNonExpired()
	{
		return true;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public boolean isAccountNonLocked()
	{
		return true;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public boolean isCredentialsNonExpired()
	{
		return true;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public boolean isEnabled()
	{
		return this.enabled;
	}
}
