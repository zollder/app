package com.app.domain.model;

import com.app.security.SecurityPasswordEncoderMock;

public class MockUser extends User
{
	private static final long serialVersionUID = 1L;

	private static Long key = new Long(12345L);
	private static String firstName = "first";
	private static String lastName = "last";
	private static String userName = "mock_user";
	private static String rawPassword = "P@55w()D";
	private static String email = "test@email.org";
	private static Boolean isEnabled = Boolean.TRUE;
	private static Boolean canLogin = Boolean.TRUE;
	private static Boolean isAdmin = Boolean.FALSE;

	// prepare mock user object with predefined field values
	private static User testUser = new User();
	static
	{
		testUser.setPrimaryKey(key);
		testUser.setFirstName(firstName);
		testUser.setLastName(lastName);
		testUser.setPassword(rawPassword);
		testUser.setUserName(userName);
		testUser.setEmail(email);
		testUser.setIsEnabled(isEnabled);
		testUser.setCanLogin(canLogin);
		testUser.setIsAdmin(isAdmin);
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Constructor */
	// --------------------------------------------------------------------------------------------------------------------------------
	public MockUser()
	{
		// Inject mock encoder by Simulate Spring injection
		setPasswordEncoder(new SecurityPasswordEncoderMock());

		// Set valid field values
		setPrimaryKey(key);
		setFirstName(firstName);
		setLastName(lastName);
		setUserName(userName);
		setPassword(getPasswordEncoder().encodePassword(rawPassword));
		setEmail(email);
		setCanLogin(canLogin);
		setIsAdmin(isAdmin);
		setIsEnabled(isEnabled);
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Accessor for test purposes */
	// --------------------------------------------------------------------------------------------------------------------------------
	public static String getIntialRawPassword()
	{
		return rawPassword;
	}
}
