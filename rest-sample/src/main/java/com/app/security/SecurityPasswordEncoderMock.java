package com.app.security;

public class SecurityPasswordEncoderMock extends PasswordEncoder
{
	private final static String prefix = "encoded_";

	@Override
	public String encodePassword(String rawPassword)
	{
		// Nothing fancy, just something different from the input ;)
		return prefix + rawPassword;
	}

}
