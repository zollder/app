package com.app.security;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.app.domain.User;

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
    @Override
    public Collection<GrantedAuthority> getAuthorities()
    {
        return this.authorities;
    }

	// --------------------------------------------------------------------------------------------------------------------------------
	@Override
	public String getPassword()
	{
		return this.password;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	@Override
	public String getUsername()
	{
		return this.username;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	@Override
	public boolean isAccountNonExpired()
	{
		return true;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	@Override
	public boolean isAccountNonLocked()
	{
		return true;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	@Override
	public boolean isCredentialsNonExpired()
	{
		return true;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	@Override
	public boolean isEnabled()
	{
		return this.enabled;
	}
}
