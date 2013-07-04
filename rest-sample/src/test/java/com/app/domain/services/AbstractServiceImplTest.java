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
import com.app.domain.exceptions.DataNotFoundException;
import com.app.domain.model.User;
import com.app.domain.services.impl.AbstractServiceImpl;

public class AbstractServiceImplTest
{
	// --------------------------------------------------------------------------------------------------------------------------------
	private AbstractDaoImpl<User> getSpiedInstance()
	{
		@SuppressWarnings("unchecked")
		AbstractDaoImpl<User> mockUser = mock(AbstractDaoImpl.class);
		when(mockUser.getModelClass()).thenReturn(User.class);
		return mockUser;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	@Before
	public void setUp()
	{
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	@After
	public void cleanUp()
	{}

	// --------------------------------------------------------------------------------------------------------------------------------
	@Test
	public void testConstructor()
	{
		AbstractDaoImpl<User> dao = getSpiedInstance();
		AbstractServiceImpl<User, UserDao> abstractService = spy(new AbstractServiceImpl<User, UserDao>(dao)
		{});
		assertEquals(dao, abstractService.getDao());
		assertEquals(User.class, abstractService.getModelClazz());
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Test refresh */
	// --------------------------------------------------------------------------------------------------------------------------------
	@Test
	@SuppressWarnings("unchecked")
	public void testRefresh()
	{
		User userEntity = new User();

		AbstractDaoImpl<User> mockDao = mock(AbstractDaoImpl.class);
		AbstractServiceImpl<User, AbstractDao<User>> abstractService = spy(new AbstractServiceImpl<User, AbstractDao<User>>(mockDao){});
		abstractService.refresh(userEntity);

		verify(mockDao, times(1)).refresh(userEntity);
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Test successful save */
	// --------------------------------------------------------------------------------------------------------------------------------
	@Test
	public void testSuccesfulSave()
	{
		User userEntity = new User();
		Long primaryKey = 12345L;
		userEntity.setPrimaryKey(primaryKey);

		AbstractDao<User> abstractDao = getSpiedInstance();

		doAnswer(new Answer<User>()
		{
			public User answer(InvocationOnMock invocation) throws Throwable
			{
				User user = (User) invocation.getArguments()[0];
				return user;
			}
		}).when(abstractDao).save(userEntity);

		// spy abstractService instance for save operation
		AbstractServiceImpl<User, UserDao> abstractService = spy(new AbstractServiceImpl<User, UserDao>(abstractDao){});

		User savedUser = abstractService.save(userEntity);
		assertEquals(savedUser, userEntity);
		assertEquals(primaryKey, savedUser.getPrimaryKey());
		verify(abstractDao, times(1)).save(any(User.class));
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Test successful save */
	// --------------------------------------------------------------------------------------------------------------------------------
	@Test
	public void testSuccesfulUpdate()
	{
		User userEntity = new User();
		Long primaryKey = 12345L;
		userEntity.setPrimaryKey(primaryKey);

		AbstractDao<User> abstractDao = getSpiedInstance();

		doAnswer(new Answer<User>()
		{
			public User answer(InvocationOnMock invocation) throws Throwable
			{
				User user = (User) invocation.getArguments()[0];
				return user;
			}
		}).when(abstractDao).update(userEntity);

		// spy abstractService instance for update operation
		AbstractServiceImpl<User, UserDao> abstractService = spy(new AbstractServiceImpl<User, UserDao>(abstractDao){});

		User updatedUser = abstractService.update(userEntity);
		assertEquals(updatedUser, userEntity);
		assertEquals(primaryKey, updatedUser.getPrimaryKey());
		verify(abstractDao, times(1)).update(any(User.class));
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Test unsuccessful delete due to wrong primary key - throws DataNotFoundException */
	// --------------------------------------------------------------------------------------------------------------------------------
	@Test(expected = DataNotFoundException.class)
	public void testFailingDeleteWrongPrimaryKeyDataNotFound()
	{
		Long primaryKey = Long.valueOf(12345);

		AbstractDao<User> abstractDao = getSpiedInstance();
		when(abstractDao.delete(primaryKey)).thenReturn(Boolean.FALSE);

		AbstractServiceImpl<User, UserDao> abstractService = new AbstractServiceImpl<User, UserDao>(abstractDao){};
		abstractService.delete(primaryKey);
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Test successful delete */
	// --------------------------------------------------------------------------------------------------------------------------------
	public void testSuccessfulDelete()
	{
		Long primaryKey = Long.valueOf(12345);

		AbstractDao<User> userDao = getSpiedInstance();
		when(userDao.delete(primaryKey)).thenReturn(Boolean.TRUE);

		AbstractServiceImpl<User, UserDao> abstractService = new AbstractServiceImpl<User, UserDao>(userDao){};

		abstractService.delete(primaryKey);
		verify(userDao, times(1)).delete(primaryKey);
	}


	// --------------------------------------------------------------------------------------------------------------------------------
	/** Test unsuccessful loadWithPrimaryKey due to wrong primary key - throws DataNotFoundException */
	// --------------------------------------------------------------------------------------------------------------------------------
	@Test(expected = DataNotFoundException.class)
	public void testFailingLoadWithPrimaryKeyDataNotFound()
	{
		final User userEntity = new User();
		userEntity.setPrimaryKey(Long.valueOf(1));

		AbstractDao<User> abstractDao = getSpiedInstance();
		when(abstractDao.loadWithPrimaryKey(userEntity.getPrimaryKey())).thenReturn(null);

		AbstractServiceImpl<User, UserDao> abstractService = new AbstractServiceImpl<User, UserDao>(abstractDao){};
		abstractService.loadWithPrimaryKey(userEntity.getPrimaryKey());
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Test successful loadWithPrimaryKey */
	// --------------------------------------------------------------------------------------------------------------------------------
	@Test
	public void testLoadWithAlternateKey()
	{
		final User userEntity = new User();
		Long primaryKey = Long.valueOf(1);

		userEntity.setPrimaryKey(primaryKey);

		AbstractDao<User> abstractDao = getSpiedInstance();
		AbstractServiceImpl<User, UserDao> abstractService = spy(new AbstractServiceImpl<User, UserDao>(abstractDao){});

		doReturn(userEntity).when(abstractService).loadWithPrimaryKey(primaryKey);

		assertEquals(userEntity, abstractService.loadWithPrimaryKey(primaryKey));
		verify(abstractService, times(1)).loadWithPrimaryKey(primaryKey);
	}
}
