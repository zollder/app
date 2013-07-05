package com.app.domain.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.app.dao.UserDao;
import com.app.dao.impl.UserDaoImpl;
import com.app.domain.dto.PasswordReset;
import com.app.domain.exceptions.DataNotFoundException;
import com.app.domain.model.MockUser;
import com.app.domain.model.User;
import com.app.domain.services.impl.UserServiceImpl;
import com.app.security.PasswordEncoder;

public class UserServiceImplTest
{
	private User testUser = null;

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Prerequisites */
	// --------------------------------------------------------------------------------------------------------------------------------
	@Before
	public void setUp() throws Exception
	{
		testUser = new MockUser();
	}
	
	@After
	public void cleanUp()
	{
		testUser = null;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Test successful password reset */
	// --------------------------------------------------------------------------------------------------------------------------------
	@Test
	public void testSuccessfulPasswordReset()
	{
		// prepare: initialize testUser object with new PasswordEncoder
		final PasswordEncoder passwordEncoder = new PasswordEncoder();
		final String newPassword = "__new_P@ssW0rd__";

		testUser.setPasswordEncoder(passwordEncoder);

		// prepare: spy UserServiceImplementation instance
		UserServiceImpl userServices = spy(new UserServiceImpl(mock(UserDaoImpl.class)));

		// prepare: return testUser instance when a service tries to fetch it by primaryKey
		doReturn(testUser).when(userServices).loadWithPrimaryKey(testUser.getPrimaryKey());
		doAnswer(new Answer<User>()
		{
			public User answer(InvocationOnMock invocation)
			{
				// verify correct user object is invoked and it contains correctly encoded password
				User user = ((User) invocation.getArguments()[0]);
				assertEquals(testUser, user);
				assertEquals(passwordEncoder.encodePassword(newPassword), user.getPassword());
				return user;
			}
		}).when(userServices).save(testUser);

		// perform the actual work
		PasswordReset passwordReset = new PasswordReset();
		passwordReset.setNewPassword(newPassword);
		passwordReset.setConfirmPassword(newPassword);

		userServices.resetPassword(testUser.getPrimaryKey(), passwordReset);
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Test if the password is hashed before save. */
	// --------------------------------------------------------------------------------------------------------------------------------
	@Test
	public void testCheckPasswordEncodingWhenSaving()
	{
		final PasswordEncoder passwordEncoder = new PasswordEncoder();

		final String newPassword = "__new_P@ssW0rd__";
		testUser.setPrimaryKey(null);
		testUser.setPasswordEncoder(passwordEncoder);
		testUser.setPassword(newPassword);

		final Long primaryKey = 1234l;
		
		UserDaoImpl userDao = mock(UserDaoImpl.class);
		when(userDao.getModelClass()).thenReturn(User.class);
		when(userDao.loadWithPrimaryKey(testUser.getPrimaryKey())).thenReturn(testUser);

		when(userDao.save(testUser)).thenAnswer(new Answer<User>()
		{
			public User answer(InvocationOnMock invocation)
			{
				User user = ((User) invocation.getArguments()[0]);
				assertEquals(testUser, user);
				assertEquals(passwordEncoder.encodePassword(newPassword), user.getPassword());
				user.setPrimaryKey(primaryKey);
				return user;
			}
		});

		UserServiceImpl userServices = new UserServiceImpl(userDao);
		userServices.save(testUser);
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Test successful loadWithUserName */
	// --------------------------------------------------------------------------------------------------------------------------------
	@Test
	public void testSuccessfulLoadWithUserName()
	{
		UserDao userDao = mock(UserDao.class);
		when(userDao.findByUserName(testUser.getUserName())).thenReturn(testUser);

		UserServiceImpl userServices = new UserServiceImpl(userDao);

		assertEquals(testUser, userServices.loadWithUserName(testUser.getUserName()));
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Test failing loadWithUserName */
	// --------------------------------------------------------------------------------------------------------------------------------
	@Test(expected = DataNotFoundException.class)
	public void testFailingLoadWithUserName()
	{
		UserDao userDao = mock(UserDao.class);
		when(userDao.findByUserName(testUser.getUserName())).thenReturn(null);
		when(userDao.getModelClass()).thenReturn(User.class);

		UserServiceImpl userServices = new UserServiceImpl(userDao);

		userServices.loadWithUserName(testUser.getUserName());
	}
}
