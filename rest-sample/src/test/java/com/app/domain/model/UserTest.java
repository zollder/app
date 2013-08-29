package com.app.domain.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.app.domain.model.mocks.MockUser;

public class UserTest extends BaseTest
{
	private static Validator validator;

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Static initialization */
	// --------------------------------------------------------------------------------------------------------------------------------
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		// Prepare validator
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	private User testUser = null;

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Class instance initialization */
	// --------------------------------------------------------------------------------------------------------------------------------
	@Before
	public void setUp() throws Exception
	{
		// Valid User entity that will be altered field by field in the test functions below
		testUser = new MockUser();
	}

	// TODO: don't forget to add passwordChange & passwordReset tests once those features are implemented

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Verifies an empty User instance is rejected */
	// --------------------------------------------------------------------------------------------------------------------------------
	@Test
	public void testConstructor()
	{
		User freshUser = new User();

		Set<ConstraintViolation<User>> constraintViolations = validator.validate(freshUser);

		// Expects 8 constraint violations
		assertEquals(8, constraintViolations.size());
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Verifies a valid User is accepted */
	// --------------------------------------------------------------------------------------------------------------------------------
	@Test
	public void testValidUser()
	{
		Set<ConstraintViolation<User>> constraintViolations = validator.validate(testUser);

		// 0 constraint violations
		assertEquals(0, constraintViolations.size());
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Verifies a User without primakyKey is accepted. */
	// --------------------------------------------------------------------------------------------------------------------------------
	@Test
	public void testUserWithoutPrimaryKey()
	{
		testUser.setPrimaryKey(null);
		assertNull(testUser.getPrimaryKey());

		Set<ConstraintViolation<User>> constraintViolations = validator.validate(testUser);

		// 0 constraint violations, as the PrimaryKey can be <null> at creation
		assertEquals(0, constraintViolations.size());
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Verifies a User without firstName is rejected (decorated with @NotNull). */
	// --------------------------------------------------------------------------------------------------------------------------------
	@Test
	public void testUserWithoutFirstName()
	{
		testUser.setFirstName(null);
		assertNull(testUser.getFirstName());

		Set<ConstraintViolation<User>> constraintViolations = validator.validate(testUser);

		// 1 constraint violation
		assertEquals(1, constraintViolations.size());
		ConstraintViolation<User> violation = constraintViolations.iterator().next();
		assertEquals("firstName", violation.getPropertyPath().toString());
		assertEquals("may not be null", violation.getMessage());
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Verifies a User without lastName is rejected (decorated with @NotNull) */
	// --------------------------------------------------------------------------------------------------------------------------------
	@Test
	public void testUserWithoutLastName()
	{
		testUser.setLastName(null);
		assertNull(testUser.getLastName());
		
		Set<ConstraintViolation<User>> constraintViolations = validator.validate(testUser);
		
		// 1 constraint violation
		assertEquals(1, constraintViolations.size());
		ConstraintViolation<User> violation = constraintViolations.iterator().next();
		assertEquals("lastName", violation.getPropertyPath().toString());
		assertEquals("may not be null", violation.getMessage());
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Verifies a User without userName is rejected (decorated with @NotNull) */
	// --------------------------------------------------------------------------------------------------------------------------------
	@Test
	public void testUserWithoutUserName()
	{
		testUser.setUserName(null);
		assertNull(testUser.getUserName());

		Set<ConstraintViolation<User>> constraintViolations = validator.validate(testUser);

		// 1 constraint violation
		assertEquals(1, constraintViolations.size());
		ConstraintViolation<User> violation = constraintViolations.iterator().next();
		assertEquals("userName", violation.getPropertyPath().toString());
		assertEquals("may not be null", violation.getMessage());
	}


	// --------------------------------------------------------------------------------------------------------------------------------
	/** Verifies a User without email is rejected (decorated with @NotNull) */
	// --------------------------------------------------------------------------------------------------------------------------------
	@Test
	public void testUserWithoutEmail()
	{
		testUser.setEmail(null);
		assertNull(testUser.getEmail());

		Set<ConstraintViolation<User>> constraintViolations = validator.validate(testUser);

		// 1 constraint violation
		assertEquals(1, constraintViolations.size());
		ConstraintViolation<User> violation = constraintViolations.iterator().next();
		assertEquals("email", violation.getPropertyPath().toString());
		assertEquals("may not be null", violation.getMessage());
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Verifies a User with invalid email is rejected (decorated with @Email) */
	// --------------------------------------------------------------------------------------------------------------------------------
	@Test
	public void testUserWithWrongEmail()
	{
		testUser.setEmail("invalid-email-address");
		assertNotNull(testUser.getEmail());

		Set<ConstraintViolation<User>> constraintViolations = validator.validate(testUser);

		// 1 constraint violation
		assertEquals(1, constraintViolations.size());
		ConstraintViolation<User> violation = constraintViolations.iterator().next();
		assertEquals("email", violation.getPropertyPath().toString());
		assertEquals("not a well-formed email address", violation.getMessage());

		// INFO: Violation message is defined by org.hibernate.validator.constraints.Email
		// Can be modified in org.hibernate.validator.ValidationMessages.properties
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Verifies a User without password is rejected (decorated with @NotNull) */
	// --------------------------------------------------------------------------------------------------------------------------------
	@Test
	public void testUserWithoutPassword()
	{
		testUser.setPassword(null);
		assertNull(testUser.getPassword());

		Set<ConstraintViolation<User>> constraintViolations = validator.validate(testUser);

		// 1 constraint violation
		assertEquals(1, constraintViolations.size());
		ConstraintViolation<User> violation = constraintViolations.iterator().next();
		assertEquals("password", violation.getPropertyPath().toString());
		assertEquals("may not be null", violation.getMessage());
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Verifies a User with the password longer than expected is rejected (decorated with @Size) */
	// --------------------------------------------------------------------------------------------------------------------------------
	@Test
	public void testUserWithLongPassword()
	{
		 // 90-characters password
		String longPwd =
				"0123456789" +
				"0123456789" +
				"0123456789" +
				"0123456789" +
				"0123456789" +
				"0123456789" +
				"0123456789" +
				"0123456789" +
				"0123456789";

		testUser.setPassword(longPwd);
		assertEquals(longPwd, testUser.getPassword());

		Set<ConstraintViolation<User>> constraintViolations = validator.validate(testUser);

		// 1 constraint violation
		assertEquals(1, constraintViolations.size());
		ConstraintViolation<User> violation = constraintViolations.iterator().next();
		assertEquals("password", violation.getPropertyPath().toString());
		assertEquals("size must be between 0 and 40", violation.getMessage());

		// INFO: Violation message defined by javax.validation.constraints.Size
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Verifies a User without isEnabled is rejected (decorated with @NotNull) */
	// --------------------------------------------------------------------------------------------------------------------------------
	@Test
	public void testUserWithoutIsEnabled()
	{
		testUser.setIsEnabled(null);
		assertNull(testUser.getIsEnabled());

		Set<ConstraintViolation<User>> constraintViolations = validator.validate(testUser);

		// 1 constraint violation
		assertEquals(1, constraintViolations.size());
		ConstraintViolation<User> violation = constraintViolations.iterator().next();
		assertEquals("isEnabled", violation.getPropertyPath().toString());
		assertEquals("may not be null", violation.getMessage());
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Verifies a User without canLogin is rejected (decorated with @NotNull) */
	// --------------------------------------------------------------------------------------------------------------------------------
	@Test
	public void testUserWithoutCanLogin()
	{
		testUser.setCanLogin(null);
		assertNull(testUser.getCanLogin());

		Set<ConstraintViolation<User>> constraintViolations = validator.validate(testUser);

		// 1 constraint violation
		assertEquals(1, constraintViolations.size());
		ConstraintViolation<User> violation = constraintViolations.iterator().next();
		assertEquals("canLogin", violation.getPropertyPath().toString());
		assertEquals("may not be null", violation.getMessage());
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Verifies a User without isAdmin is rejected (decorated with @NotNull) */
	// --------------------------------------------------------------------------------------------------------------------------------
	@Test
	public void testUserWithoutIsAdmnin()
	{
		testUser.setIsAdmin(null);
		assertNull(testUser.getIsAdmin());

		Set<ConstraintViolation<User>> constraintViolations = validator.validate(testUser);

		// 1 constraint violation
		assertEquals(1, constraintViolations.size());
		ConstraintViolation<User> violation = constraintViolations.iterator().next();
		assertEquals("isAdmin", violation.getPropertyPath().toString());
		assertEquals("may not be null", violation.getMessage());
	}
}