package com.app.domain.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.app.dao.AbstractDao;
import com.app.dao.UserDao;
import com.app.dao.impl.AbstractDaoImpl;
import com.app.dao.impl.UserDaoImpl;
import com.app.domain.dto.PasswordReset;
import com.app.domain.exceptions.DataNotFoundException;
import com.app.domain.model.MockUser;
import com.app.domain.model.User;
import com.app.domain.services.impl.AbstractServiceImpl;
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
	public void testCheckEncodingPasswordWhenInserting()
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

//
//	// --------------------------------------------------------------------------------------------------------------------------------
//	/**
//	 * Verifies the password reset fails if the new password is not confirmed
//	 */
//	// --------------------------------------------------------------------------------------------------------------------------------
//	@Test(expected = InvalidRequestException.class)
//	public void testUserPasswordResetII()
//	{
//		final SecurityPasswordEncoder passwordEncoder = new SecurityPasswordEncoder();
//
//		final String newPassword = "__new_P@ssW0rd__";
//
//		_testUser.setPasswordEncoder(passwordEncoder);
//
//		UserDao userDao = mock(UserDao.class);
//		when(userDao.loadByPrimaryKey(_testUser.getPrimaryKey())).thenReturn(_testUser);
//		when(userDao.save(_testUser)).thenThrow(new AssertionFailedError("Unexpected call"));
//
//		UserServiceImpl userServices = new UserServiceImpl(userDao);
//
//		PasswordReset passwordReset = new PasswordReset();
//		passwordReset.setNewPassword(newPassword);
//		passwordReset.setConfirmPassword(newPassword + "_garbage_");
//
//		userServices.resetPassword(_testUser.getPrimaryKey(), passwordReset);
//	}
//
//	// --------------------------------------------------------------------------------------------------------------------------------
//	/**
//	 * Verifies the password reset fails if the user is not found
//	 */
//	// --------------------------------------------------------------------------------------------------------------------------------
//	@Test(expected = DataNotFoundException.class)
//	public void testUserPasswordResetIII()
//	{
//		UserDao userDao = mock(UserDao.class);
//		when(userDao.getModelClass()).thenReturn(User.class);
//		when(userDao.loadByPrimaryKey(_testUser.getPrimaryKey())).thenReturn(null);
//		when(userDao.save(_testUser)).thenThrow(new AssertionFailedError("Unexpected call"));
//
//		UserServiceImpl userServices = new UserServiceImpl(userDao);
//
//		userServices.resetPassword(_testUser.getPrimaryKey(), new PasswordReset());
//	}
//
//	// --------------------------------------------------------------------------------------------------------------------------------
//	/**
//	 * Verifies a normal password update does not fail
//	 */
//	// --------------------------------------------------------------------------------------------------------------------------------
//	@Test
//	public void testUserPasswordChangeI()
//	{
//		final SecurityPasswordEncoder passwordEncoder = new SecurityPasswordEncoder();
//
//		String oldPassword = "__old_P@ssW0rd__";
//		final String newPassword = "__new_P@ssW0rd__";
//
//		_testUser.setPasswordEncoder(passwordEncoder);
//		_testUser.setPassword(passwordEncoder.encodePassword(oldPassword));
//
//		UserServiceImpl userServices = spy(new UserServiceImpl(mock(UserDao.class)));
//
//		doReturn(_testUser).when(userServices).loadWithPrimaryKeyByPassAccessRights(_testUser.getPrimaryKey());
//
//		doAnswer(new Answer<User>()
//		{
//			public User answer(InvocationOnMock invocation)
//			{
//				User user = ((User) invocation.getArguments()[0]);
//				assertEquals(_testUser, user);
//				assertEquals(passwordEncoder.encodePassword(newPassword), user.getPassword());
//				return user;
//			}
//		}).when(userServices).save(_testUser);
//		
//		PasswordChange passwordChange = new PasswordChange();
//		passwordChange.setOldPassword(oldPassword);
//		passwordChange.setNewPassword(newPassword);
//		passwordChange.setConfirmPassword(newPassword);
//
//		userServices.changePassword(_testUser.getPrimaryKey(), passwordChange);
//		verify(userServices, times(1)).save(any(User.class));
//	}
//
//	// --------------------------------------------------------------------------------------------------------------------------------
//	/**
//	 * Verifies the password update fails if the old password does not match
//	 */
//	// --------------------------------------------------------------------------------------------------------------------------------
//	@Test(expected = InvalidRequestException.class)
//	public void testUserPasswordChangeII()
//	{
//		final SecurityPasswordEncoder passwordEncoder = new SecurityPasswordEncoder();
//
//		String oldPassword = "__old_P@ssW0rd__";
//		final String newPassword = "__new_P@ssW0rd__";
//
//		_testUser.setPasswordEncoder(passwordEncoder);
//		_testUser.setPassword(passwordEncoder.encodePassword(oldPassword));
//
//		UserDao userDao = mock(UserDao.class);
//		when(userDao.loadByPrimaryKey(_testUser.getPrimaryKey())).thenReturn(_testUser);
//		when(userDao.save(_testUser)).thenThrow(new AssertionFailedError("Unexpected call"));
//
//		UserServiceImpl userServices = new UserServiceImpl(userDao);
//
//		PasswordChange passwordChange = new PasswordChange();
//		passwordChange.setOldPassword(oldPassword + "_garbage_");
//		passwordChange.setNewPassword(newPassword);
//		passwordChange.setConfirmPassword(newPassword);
//
//		userServices.changePassword(_testUser.getPrimaryKey(), passwordChange);
//	}
//
//	// --------------------------------------------------------------------------------------------------------------------------------
//	/**
//	 * Verifies the password update fails if the new password is not confirmed
//	 */
//	// --------------------------------------------------------------------------------------------------------------------------------
//	@Test(expected = InvalidRequestException.class)
//	public void testUserPasswordChangeIII()
//	{
//		final SecurityPasswordEncoder passwordEncoder = new SecurityPasswordEncoder();
//
//		String oldPassword = "__old_P@ssW0rd__";
//		final String newPassword = "__new_P@ssW0rd__";
//
//		_testUser.setPasswordEncoder(passwordEncoder);
//		_testUser.setPassword(passwordEncoder.encodePassword(oldPassword));
//
//		UserDao userDao = mock(UserDao.class);
//		when(userDao.loadByPrimaryKey(_testUser.getPrimaryKey())).thenReturn(_testUser);
//		when(userDao.save(_testUser)).thenThrow(new AssertionFailedError("Unexpected call"));
//
//		UserServiceImpl userServices = new UserServiceImpl(userDao);
//
//		PasswordChange passwordChange = new PasswordChange();
//		passwordChange.setOldPassword(oldPassword);
//		passwordChange.setNewPassword(newPassword);
//		passwordChange.setConfirmPassword(newPassword + "_garbage_");
//
//		userServices.changePassword(_testUser.getPrimaryKey(), passwordChange);
//	}
//
//	// --------------------------------------------------------------------------------------------------------------------------------
//	/**
//	 * Verifies the password reset fails if the user is not found
//	 */
//	// --------------------------------------------------------------------------------------------------------------------------------
//	@Test(expected = DataNotFoundException.class)
//	public void testUserPasswordChangeIV()
//	{
//		UserDao userDao = mock(UserDao.class);
//		when(userDao.getModelClass()).thenReturn(User.class);
//		when(userDao.loadByPrimaryKey(_testUser.getPrimaryKey())).thenReturn(null);
//		when(userDao.save(_testUser)).thenThrow(new AssertionFailedError("Unexpected call"));
//
//		UserServiceImpl userServices = new UserServiceImpl(userDao);
//
//		userServices.changePassword(_testUser.getPrimaryKey(), new PasswordChange());
//	}
//
//	// --------------------------------------------------------------------------------------------------------------------------------
//	/**
//	 * Verifies successful loadWithUserName
//	 */
//	// --------------------------------------------------------------------------------------------------------------------------------
//	@Test
//	public void successfulLoadWithUserName()
//	{
//		UserDao userDao = mock(UserDao.class);
//		when(userDao.findByUserName(_testUser.getUserName())).thenReturn(_testUser);
//
//		UserServiceImpl userServices = new UserServiceImpl(userDao);
//
//		assertEquals(_testUser, userServices.loadWithUserName(_testUser.getUserName()));
//	}
//
//	// --------------------------------------------------------------------------------------------------------------------------------
//	/**
//	 * Verifies unsuccessful loadWithUserName
//	 */
//	// --------------------------------------------------------------------------------------------------------------------------------
//	@Test(expected = DataNotFoundException.class)
//	public void failingLoadWithUserName()
//	{
//		UserDao userDao = mock(UserDao.class);
//		when(userDao.findByUserName(_testUser.getUserName())).thenReturn(null);
//
//		UserServiceImpl userServices = new UserServiceImpl(userDao);
//
//		userServices.loadWithUserName(_testUser.getUserName());
//	}
//
//	// --------------------------------------------------------------------------------------------------------------------------------
//	/**
//	 * Verifies successful loadWithCriteria
//	 */
//	// --------------------------------------------------------------------------------------------------------------------------------
//	@Test
//	public void successfulLoadWithCriteria()
//	{
//		UserCriteria userCriteria = new UserCriteria();
//		final List<User> selection = new ArrayList<User>();
//		Sorting sorting = mock(Sorting.class);
//		
//		UserDao userDao = mock(UserDao.class);
//		when(userDao.findByCriteria(userCriteria, null, null, (ExclusionStrategy)null)).thenReturn(selection);
//		UserServiceImpl userServices = new UserServiceImpl(userDao);
//
//		assertEquals(selection, userServices.loadWithCriteria(userCriteria, null, sorting, null));
//		verify(userDao, times(1)).findByCriteria(any(UserCriteria.class), any(Range.class), any(Sorting.class), any(ExclusionStrategy.class));
//		verify(userDao, times(1)).findByCriteria(userCriteria, null, sorting, (ExclusionStrategy)null);
//	}
//
//	// --------------------------------------------------------------------------------------------------------------------------------
//	/**
//	 * Verifies successful update
//	 */
//	// --------------------------------------------------------------------------------------------------------------------------------
//	@Test
//	@Ignore
//	public void successfulSaveForUpdate()
//	{
//		final String userName = "__username_for_test__";
//
//		final User previousVersion = new User();
//		previousVersion.setPrimaryKey(_testUser.getPrimaryKey());
//		previousVersion.setCode(_testUser.getCode());
//		previousVersion.setVersion(_testUser.getVersion());
//		previousVersion.setUserName(userName);
//
//		User example = new User();
//		example.setCode(_testUser.getCode());
//
//		UserDao userDao = mock(UserDao.class);
//		when(userDao.findByExampleUnique(example)).thenReturn(previousVersion); // Old copy that will have its user name field reset
//		when(userDao.save(_testUser)).thenAnswer(new Answer<User>()
//		{
//			public User answer(InvocationOnMock invocation)
//			{
//				User user = ((User) invocation.getArguments()[0]);
//				// Verify the given entity is the one updated
//				assertEquals(_testUser.getPrimaryKey(), user.getPrimaryKey());
//				assertEquals(_testUser.getCode(), user.getCode());
//				assertNotSame(userName, user.getUserName());
//				assertEquals(_testUser.getUserName(), user.getUserName());
//				// Increment the version number ;)
//				user.setVersion(Integer.valueOf(user.getVersion().intValue() + 1));
//				return user;
//			}
//		});
//
//		UserServiceImpl userServices = new UserServiceImpl(userDao);
//
//		// Prevent the password reset
//		_testUser.setPassword(null);
//
//		assertEquals(_testUser.getPrimaryKey(), userServices.save(example, _testUser, UpdateMode.full).getPrimaryKey());
//		verify(userDao, times(1)).findByExample(any(User.class));
//	}
}
