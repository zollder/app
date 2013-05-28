package com.app.security;

//--------------------------------------------------------------------------------------------------------------------------------
/** PasswordEncoder factory class. */
// --------------------------------------------------------------------------------------------------------------------------------
public class PasswordEncoderFactory
{
	private static PasswordEncoder passwordEncoder;

	// --------------------------------------------------------------------------------------------------------------------------------
	/**
	 * @return an instance of PasswordEncoder.
	 */
	// --------------------------------------------------------------------------------------------------------------------------------
	public static PasswordEncoder getPasswordEncoder()
	{
		if (passwordEncoder == null)
			passwordEncoder = new PasswordEncoder();

		return passwordEncoder;
	}
}
