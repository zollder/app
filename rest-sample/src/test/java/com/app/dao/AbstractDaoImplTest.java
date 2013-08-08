package com.app.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.app.dao.impl.AbstractDaoImpl;
import com.app.domain.model.User;

public class AbstractDaoImplTest
{	
	// --------------------------------------------------------------------------------------------------------------------------------
	/** Tests constructor & modelClass accessors */
	// --------------------------------------------------------------------------------------------------------------------------------
	@Test
	public void testConstructor()
	{
		AbstractDaoImpl<User> instance = getInstance();
		instance.setModelClass(User.class);
		assertEquals(User.class, instance.getModelClass());
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Verify the loadByPrimaryKey for an independent object */
	// --------------------------------------------------------------------------------------------------------------------------------
	@Test
	public void testLoadWithPrimaryKey()
	{
		Long primaryKey = Long.valueOf(123456L);
		User userEntity = new User();

		SessionFactory mockSessionFactory = mock(SessionFactory.class);
		Session mockSession = mock(Session.class);
		AbstractDaoImpl<User> spiedInstance = spy(new AbstractDaoImpl<User>(mockSessionFactory){});

		when(mockSession.get(User.class, primaryKey)).thenReturn(userEntity);
		when(spiedInstance.getCurrentSession()).thenReturn(mockSession);
		when(spiedInstance.getModelClass()).thenReturn(User.class);

		assertEquals(userEntity, spiedInstance.loadWithPrimaryKey(primaryKey));
		verify(mockSession, times(1)).get(User.class, primaryKey);
		verify(spiedInstance, times(1)).getModelClass();
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Verify findAll */
	// --------------------------------------------------------------------------------------------------------------------------------
	@Test
	public void testFindAll()
	{
		User user1 = new User();
		User user2 = new User();
		final List<User> userList = new ArrayList<User>();
		userList.add(user1);
		userList.add(user2);

		SessionFactory mockSessionFactory = mock(SessionFactory.class);
		Session mockSession = mock(Session.class);
		Query mockQuery = mock(Query.class);
		AbstractDaoImpl<User> spiedInstance = spy(new AbstractDaoImpl<User>(mockSessionFactory){});

		when(spiedInstance.getCurrentSession()).thenReturn(mockSession);
		when(spiedInstance.getModelClass()).thenReturn(User.class);
		when(mockSession.createQuery(any(String.class))).thenReturn(mockQuery);
		when(mockQuery.list()).thenReturn(userList);

		List<User> returnedList = spiedInstance.findAll();
		assertEquals(userList, returnedList);
		assertTrue(returnedList.contains(user1));
		assertTrue(returnedList.contains(user2));
		verify(spiedInstance, times(1)).getModelClass();
		verify(mockSession, times(1)).createQuery(any(String.class));
		verify(mockQuery, times(1)).list();

	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Verify save */
	// --------------------------------------------------------------------------------------------------------------------------------
	@Test
	public void testSave()
	{
		final User userEntity = new User();

		SessionFactory mockSessionFactory = mock(SessionFactory.class);
		Session mockSession = mock(Session.class);

		AbstractDaoImpl<User> spiedInstance = spy(new AbstractDaoImpl<User>(mockSessionFactory){});
		when(spiedInstance.getCurrentSession()).thenReturn(mockSession);
		when(mockSession.save(any(User.class))).thenReturn(userEntity);

		assertEquals(userEntity, spiedInstance.save(userEntity));
		verify(mockSession).save(userEntity);
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Verify update */
	// --------------------------------------------------------------------------------------------------------------------------------
	@Test
	public void testUpdate()
	{
		final User userEntity = new User();

		SessionFactory mockSessionFactory = mock(SessionFactory.class);
		Session mockSession = mock(Session.class);

		AbstractDaoImpl<User> spiedInstance = spy(new AbstractDaoImpl<User>(mockSessionFactory){});
		when(spiedInstance.getCurrentSession()).thenReturn(mockSession);
		when(mockSession.merge(any(User.class))).thenReturn(userEntity);

		assertEquals(userEntity, spiedInstance.update(userEntity));
		verify(mockSession).merge(userEntity);
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	private AbstractDaoImpl<User> getInstance()
	{
		SessionFactory sessionFactory = mock(SessionFactory.class);
		AbstractDaoImpl<User> instance = new AbstractDaoImpl<User>(sessionFactory){};
		return instance;
	}


	// --------------------------------------------------------------------------------------------------------------------------------
	/** Verify delete non-existing entity */
	// --------------------------------------------------------------------------------------------------------------------------------
	@Test
	public void testDeleteNonExistingEntity()
	{
		SessionFactory mockSessionFactory = mock(SessionFactory.class);
		Session mockSession = mock(Session.class);
		AbstractDaoImpl<User> spiedInstance = spy(new AbstractDaoImpl<User>(mockSessionFactory){});

		when(spiedInstance.getCurrentSession()).thenReturn(mockSession);
		when(spiedInstance.getModelClass()).thenReturn(User.class);
		when(spiedInstance.loadWithPrimaryKey(any(Long.class))).thenReturn(null);

		assertFalse(spiedInstance.delete(1L));
		verify(mockSession, never()).delete(any(User.class));
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Verify delete existing entity */
	// --------------------------------------------------------------------------------------------------------------------------------
	@Test
	public void testDeleteExistingEntity()
	{
		final User userEntity = new User();
		userEntity.setPrimaryKey(1L);

		SessionFactory mockSessionFactory = mock(SessionFactory.class);
		Session mockSession = mock(Session.class);
		AbstractDaoImpl<User> spiedInstance = spy(new AbstractDaoImpl<User>(mockSessionFactory){});

		when(spiedInstance.getCurrentSession()).thenReturn(mockSession);
		when(spiedInstance.getModelClass()).thenReturn(User.class);
		doReturn(userEntity).when(spiedInstance).loadWithPrimaryKey(any(Long.class));

		doAnswer(new Answer<Void>()
				{
					public Void answer(InvocationOnMock arg0) throws Throwable
					{ return null; }
				}).when(mockSession).delete(userEntity);

		assertTrue(spiedInstance.delete(1L));
		verify(mockSession).delete(userEntity);
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Verify save object */
	// --------------------------------------------------------------------------------------------------------------------------------
	@Test
	public void testSaveGenericObject()
	{
		final Object userObject = new User();

		SessionFactory mockSessionFactory = mock(SessionFactory.class);
		Session mockSession = mock(Session.class);

		AbstractDaoImpl<User> spiedInstance = spy(new AbstractDaoImpl<User>(mockSessionFactory){});
		when(spiedInstance.getCurrentSession()).thenReturn(mockSession);
		when(mockSession.save(any(String.class), any(Object.class))).thenReturn((Serializable) userObject);

		assertEquals(userObject, spiedInstance.save(userObject));
		verify(mockSession).save(any(String.class), any(Object.class));
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Verify update object */
	// --------------------------------------------------------------------------------------------------------------------------------
	@Test
	public void testUpdateGenericObject()
	{
		final Object userEntity = new User();

		SessionFactory mockSessionFactory = mock(SessionFactory.class);
		Session mockSession = mock(Session.class);

		AbstractDaoImpl<User> spiedInstance = spy(new AbstractDaoImpl<User>(mockSessionFactory){});
		when(spiedInstance.getCurrentSession()).thenReturn(mockSession);
		when(mockSession.merge(any(String.class), any(Object.class))).thenReturn(userEntity);

		assertEquals(userEntity, spiedInstance.update(userEntity));
		verify(mockSession).merge(any(String.class), any(Object.class));
	}
}
