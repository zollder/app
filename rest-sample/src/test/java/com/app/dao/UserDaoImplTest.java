package com.app.dao;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

import com.app.dao.impl.UserDaoImpl;
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
	public void verifyFindByUserName()
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
}
