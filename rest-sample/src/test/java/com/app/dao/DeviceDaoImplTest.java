package com.app.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;
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
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.app.dao.impl.DeviceDaoImpl;
import com.app.dao.impl.UserDaoImpl;
import com.app.domain.model.Device;
import com.app.domain.model.User;
import com.app.domain.model.enums.DeviceType;

public class DeviceDaoImplTest
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
	/** Verify findByIp */
	// --------------------------------------------------------------------------------------------------------------------------------
	@Test
	public void testFindByIp()
	{
		final Device deviceEntity = new Device();
		String ipAddress = "10.10.10.10";
		deviceEntity.setDevIp(ipAddress);

		SessionFactory mockSessionFactory = mock(SessionFactory.class);
		Session mockSession = mock(Session.class);
		Query mockQuery = mock(Query.class);

		DeviceDaoImpl spiedDeviceInstance = spy(new DeviceDaoImpl(mockSessionFactory));

		when(spiedDeviceInstance.getCurrentSession()).thenReturn(mockSession);
		when(mockSession.createQuery(any(String.class))).thenReturn(mockQuery);
		doReturn(mockQuery).when(mockQuery).setString(0, ipAddress);
		when(mockQuery.uniqueResult()).thenReturn(deviceEntity);

		assertEquals(deviceEntity, spiedDeviceInstance.findByIp(ipAddress));
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Verify findByMac */
	// --------------------------------------------------------------------------------------------------------------------------------
	@Test
	public void testFindByMac()
	{
		final Device deviceEntity = new Device();
		String macAddress = "11:22:33:44:55:66";
		deviceEntity.setDevIp(macAddress);

		SessionFactory mockSessionFactory = mock(SessionFactory.class);
		Session mockSession = mock(Session.class);
		Query mockQuery = mock(Query.class);

		DeviceDaoImpl spiedDeviceInstance = spy(new DeviceDaoImpl(mockSessionFactory));

		when(spiedDeviceInstance.getCurrentSession()).thenReturn(mockSession);
		when(mockSession.createQuery(any(String.class))).thenReturn(mockQuery);
		doReturn(mockQuery).when(mockQuery).setString(0, macAddress);
		when(mockQuery.uniqueResult()).thenReturn(deviceEntity);

		assertEquals(deviceEntity, spiedDeviceInstance.findByIp(macAddress));
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Verify findByType */
	// --------------------------------------------------------------------------------------------------------------------------------
	@Test
	public void testFindByType()
	{
		final Device deviceEntity = new Device();
		String devType = DeviceType.TYPE_F.name();
		deviceEntity.setDevType(DeviceType.valueOf(devType));

		List<Device> deviceList = new ArrayList<Device>();
		deviceList.add(deviceEntity);

		SessionFactory mockSessionFactory = mock(SessionFactory.class);
		Session mockSession = mock(Session.class);
		final Criteria mockCriteria = mock(Criteria.class);

		DeviceDaoImpl spiedDeviceInstance = spy(new DeviceDaoImpl(mockSessionFactory));

		when(spiedDeviceInstance.getCurrentSession()).thenReturn(mockSession);
		when(spiedDeviceInstance.getModelClass()).thenReturn(Device.class);
		when(mockSession.createCriteria(Device.class)).thenReturn(mockCriteria);

		doAnswer(new Answer<Criteria>()
		{
			public Criteria answer(InvocationOnMock invocation)
			{
				return mockCriteria;
			}
		}).when(mockCriteria).add((Criterion) anyObject());

		when(mockCriteria.list()).thenReturn(deviceList);

		assertEquals(deviceList, spiedDeviceInstance.findAllByType(devType));
		assertTrue(deviceList.contains(deviceEntity));
		verify(mockSession, times(1)).createCriteria(Device.class);
		verify(mockCriteria, times(1)).add((Criterion) anyObject());
		verify(mockCriteria, times(1)).list();
	}
}
