package com.app.dao;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.junit.Test;

import com.app.dao.impl.UserDaoImpl;
import com.app.domain.dto.UserCriteria;
import com.app.domain.model.User;

public class UserDaoImplTest
{	
	// --------------------------------------------------------------------------------------------------------------------------------
	/** Tests constructor & modelClass accessors */
	// --------------------------------------------------------------------------------------------------------------------------------
	@Test
	public void testConstructor()
	{
		SessionFactory mockSessionFactory = mock(SessionFactory.class);
		UserDaoImpl userDaoInstance = new UserDaoImpl(mockSessionFactory);
		userDaoInstance.setModelClass(User.class);

		assertEquals(User.class, userDaoInstance.getModelClass());
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Verify findByUserName */
	// --------------------------------------------------------------------------------------------------------------------------------
	@Test
	public void testFindByUserName()
	{
		final User userEntity = new User();
		String userName = "testUsername";
		userEntity.setUserName(userName);

		SessionFactory mockSessionFactory = mock(SessionFactory.class);
		Session mockSession = mock(Session.class);
		Query mockQuery = mock(Query.class);
		UserDaoImpl spiedUserInstance = spy(new UserDaoImpl(mockSessionFactory));

		when(spiedUserInstance.getCurrentSession()).thenReturn(mockSession);
		when(mockSession.createQuery(any(String.class))).thenReturn(mockQuery);
		when(mockQuery.setString(0, userName)).thenReturn(mockQuery);
		when(mockQuery.uniqueResult()).thenReturn(userEntity);

		assertEquals(userEntity, spiedUserInstance.findByUserName(userName));
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Verify findByCriteria */
	// --------------------------------------------------------------------------------------------------------------------------------
	@Test
	public void testFindByCriteria()
	{
		final UserCriteria userCriteria = new UserCriteria();
		String userName = "testUsername";
		userCriteria.setUserName(userName);

		final User userEntity = new User();
		userEntity.setUserName(userName);

		final List<User> userList = new ArrayList<User>();
		userList.add(userEntity);

		SessionFactory mockSessionFactory = mock(SessionFactory.class);
		Session mockSession = mock(Session.class);
		UserDaoImpl spiedUserInstance = spy(new UserDaoImpl(mockSessionFactory));
		final Criteria mockCriteria = mock(Criteria.class);

		when(spiedUserInstance.getModelClass()).thenReturn(User.class);
		when(spiedUserInstance.getCurrentSession()).thenReturn(mockSession);
		when(mockSession.createCriteria(User.class)).thenReturn(mockCriteria);
		when(mockCriteria.add((Example) anyObject())).thenReturn(mockCriteria);
		when (mockCriteria.list()).thenReturn(userList);

		assertEquals(userList, spiedUserInstance.findByCriteria(userCriteria));
		verify(spiedUserInstance, times(1)).findByExample(any(User.class));
		verify(spiedUserInstance, times(1)).findByExample(any(User.class), any(Criteria.class));
		verify(mockCriteria, times(1)).add((Criterion) anyObject());
	}
}
