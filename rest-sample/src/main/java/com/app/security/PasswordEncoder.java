package com.app.security;

import java.security.MessageDigest;
import net.iharder.Base64;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

//--------------------------------------------------------------------------------------------------------------------------------
/** Used by Spring Security to encode user password values. */
//--------------------------------------------------------------------------------------------------------------------------------
public class PasswordEncoder extends Md5PasswordEncoder
{
	// --------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Utility main method that encodes a given password.
	 * @param args - the password to be encoded as first element.
	 */
	// --------------------------------------------------------------------------------------------------------------------------------
	public static void main(String[] args)
	{
		if (args.length != 1)
		{
			System.out.println("Usage:PasswordEncoder <password>");
			System.out.println("where <password> is the password for which you want to obtain the associated MD5 hash.");
			return;
		}

		PasswordEncoder passwordEncoder = PasswordEncoderFactory.getPasswordEncoder();
		System.out.println(passwordEncoder.encodePassword(args[0]));
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Encodes given password. Overrides spring's default PasswordEncoder implementation.
	 * @param rawPassword
	 * @param noise
	 * @return The string containing the encoded password
	 */
	// --------------------------------------------------------------------------------------------------------------------------------
	@Override
	public String encodePassword(String rawPassword, Object noise)
	{
		return encodePassword(rawPassword);
	}

	// -------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Encodes the given password.
	 * @param rawPassword
	 * @return The string containing the encoded password
	 */
	// -------------------------------------------------------------------------------------------------------------------------------
	public String encodePassword(String rawPassword)
	{
		final char[] salt = { 'S', 't', 'i', 'N', 'c', 'K', 'y', '+', 'n', 'O', 'i', 'Z', 'z', 'E' };

		try
		{
			// add some noise (s-a-l-t) to raw password
			StringBuffer saltedPassword = new StringBuffer();

			saltedPassword.append(salt);
			saltedPassword.append(Base64.encodeBytes(rawPassword.getBytes("UTF8")));
			saltedPassword.append(salt);

			// convert password to byte array
			byte[] passwordWithSaltAsByteArray = saltedPassword.toString().getBytes("UTF8");

			// obtain MD5 value for password
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] encodedPassword = md.digest(passwordWithSaltAsByteArray);

			// convert MD5 bytes to hex string
			StringBuffer hexString = new StringBuffer();
			for (int i = 0; i < encodedPassword.length; ++i)
			{
				String hex = Integer.toHexString(0xff & encodedPassword[i]);

				// ensure each hex byte always gets represented using 2 positions
				if (hex.length() == 1)
					hexString.append('0');

				hexString.append(hex);
			}

			return hexString.toString();
		}
		catch (Exception e)
		{
			throw new RuntimeException("Failed to encode password", e);
		}
	}
}