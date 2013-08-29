package com.app.domain.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.app.dao.DeviceDao;
import com.app.domain.exceptions.DataNotFoundException;
import com.app.domain.model.Device;
import com.app.domain.model.TypeF;
import com.app.domain.model.enums.DeviceType;
import com.app.domain.model.mocks.MockDevice;
import com.app.domain.model.mocks.MockTypeF;
import com.app.domain.services.impl.DeviceServiceImpl;

public class DeviceServiceImplTest
{
	private Device testDevice = null;
	private Device deviceEntity = null;

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Prerequisites */
	// --------------------------------------------------------------------------------------------------------------------------------

	private DeviceDao getSpiedInstance()
	{
		DeviceDao mockDeviceDao = mock(DeviceDao.class);
		when(mockDeviceDao.getModelClass()).thenReturn(Device.class);
		return mockDeviceDao;
	}

	@Before
	public void setUp() throws Exception
	{
		testDevice = new MockDevice();
		deviceEntity = new Device();
	}
	
	@After
	public void cleanUp()
	{
		testDevice = null;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Test successful load with IP address. */
	// --------------------------------------------------------------------------------------------------------------------------------
	@Test
	public void testSuccessfulLoadWithIp()
	{
		DeviceDao deviceDao = mock(DeviceDao.class);
		when(deviceDao.findByIp(testDevice.getDevIp())).thenReturn(testDevice);

		DeviceServiceImpl deviceServices = new DeviceServiceImpl(deviceDao);

		assertEquals(testDevice, deviceServices.loadWithDeviceIp(testDevice.getDevIp()));
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Test failing loadWithDeviceIp */
	// --------------------------------------------------------------------------------------------------------------------------------
	@Test(expected = DataNotFoundException.class)
	public void testFailingLoadDeviceIp()
	{
		DeviceDao deviceDao = getSpiedInstance();
		when(deviceDao.findByIp(testDevice.getDevIp())).thenReturn(null);

		DeviceServiceImpl deviceServices = new DeviceServiceImpl(deviceDao);
		deviceServices.loadWithDeviceIp(testDevice.getDevIp());
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Test successful load with MAC address. */
	// --------------------------------------------------------------------------------------------------------------------------------
	@Test
	public void testSuccessfulLoadWithMac()
	{
		DeviceDao deviceDao = mock(DeviceDao.class);
		when(deviceDao.findByMac(testDevice.getDevMac())).thenReturn(testDevice);

		DeviceServiceImpl deviceServices = new DeviceServiceImpl(deviceDao);

		assertEquals(testDevice, deviceServices.loadWithDeviceMac(testDevice.getDevMac()));
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Test failing loadWithDeviceMac */
	// --------------------------------------------------------------------------------------------------------------------------------
	@Test(expected = DataNotFoundException.class)
	public void testFailingLoadDeviceMac()
	{
		DeviceDao deviceDao = getSpiedInstance();
		when(deviceDao.findByMac(testDevice.getDevMac())).thenReturn(null);

		DeviceServiceImpl deviceServices = new DeviceServiceImpl(deviceDao);
		deviceServices.loadWithDeviceMac(testDevice.getDevMac());
	}
	// --------------------------------------------------------------------------------------------------------------------------------
	/** Test successful load all by type. */
	// --------------------------------------------------------------------------------------------------------------------------------
	@Test
	public void testSuccessfulLoadByType()
	{
		DeviceDao deviceDao = mock(DeviceDao.class);
		List<Device> devices = new ArrayList<Device>();
		devices.add(testDevice);
		when(deviceDao.findAllByType(testDevice.getDevType().toString())).thenReturn(devices);

		DeviceServiceImpl deviceServices = new DeviceServiceImpl(deviceDao);

		String devType = testDevice.getDevType().toString();
		assertTrue(deviceServices.loadAllByType(devType).contains(testDevice));
	}
	
	// --------------------------------------------------------------------------------------------------------------------------------
	/** Test failing loadAllByType */
	// --------------------------------------------------------------------------------------------------------------------------------
	@Test(expected = IllegalArgumentException.class)
	public void testFailingLoadAllByType()
	{
		DeviceDao deviceDao = getSpiedInstance();
		DeviceServiceImpl deviceServices = new DeviceServiceImpl(deviceDao);
		when(deviceDao.getModelClass()).thenReturn(Device.class);

		deviceServices.loadAllByType(null);
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Test successful save with not-null primary key */
	// --------------------------------------------------------------------------------------------------------------------------------
	@Test
	public void testSuccesfulSavePrimaryKeyNotNull()
	{
		deviceEntity.setPrimaryKey(12345L);
		deviceEntity.setDevType(DeviceType.TYPE_F);

		DeviceDao deviceDao = getSpiedInstance();
		Object mockObject = mock(Object.class);
		TypeF typeFdevice = new MockTypeF();

		// spy device service instance for save operation
		DeviceServiceImpl deviceService = spy(new DeviceServiceImpl(deviceDao){});

		when(deviceService.getDeviceTypeInstance(deviceEntity)).thenReturn(mockObject);
		when(deviceDao.save(mockObject)).thenReturn(typeFdevice);

		Device savedDevice = deviceService.save(deviceEntity);

		assertEquals(savedDevice, (Device) typeFdevice);
		verify(deviceService, times(1)).getDeviceTypeInstance(any(Device.class));
		verify(deviceDao, times(1)).save(any(Object.class));
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Test successful save with primary key set to null */
	// --------------------------------------------------------------------------------------------------------------------------------
	@Test
	public void testSuccesfulSavePrimaryKeyNull()
	{
		deviceEntity.setPrimaryKey(null);
		deviceEntity.setDevType(DeviceType.TYPE_F);

		DeviceDao deviceDao = getSpiedInstance();
		Object mockObject = mock(Object.class);
		TypeF typeFdevice = new MockTypeF();

		// spy device service instance for save operation
		DeviceServiceImpl deviceService = spy(new DeviceServiceImpl(deviceDao){});

		when(deviceService.getDeviceTypeInstance(deviceEntity)).thenReturn(mockObject);
		when(deviceDao.save(mockObject)).thenReturn(typeFdevice);

		Device savedDevice = deviceService.save(deviceEntity);

		assertEquals(savedDevice, (Device) typeFdevice);
		verify(deviceService, times(1)).getDeviceTypeInstance(any(Device.class));
		verify(deviceDao, times(1)).save(any(Object.class));
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Test failed save due to missing devType */
	// --------------------------------------------------------------------------------------------------------------------------------
	@Test(expected = IllegalArgumentException.class)
	public void testFailingSaveMissingDevType()
	{
		DeviceDao deviceDao = getSpiedInstance();

		// spy device service instance for save operation
		DeviceServiceImpl deviceService = spy(new DeviceServiceImpl(deviceDao){});
		deviceService.save(deviceEntity);
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Test successful save with not-null primary key */
	// --------------------------------------------------------------------------------------------------------------------------------
	@Test
	public void testSuccesfulUpdatePrimaryKeyNotNull()
	{
		deviceEntity.setPrimaryKey(12345L);
		deviceEntity.setDevType(DeviceType.TYPE_F);

		DeviceDao deviceDao = getSpiedInstance();
		Object mockObject = mock(Object.class);
		TypeF typeFdevice = new MockTypeF();

		// spy device service instance for save operation
		DeviceServiceImpl deviceService = spy(new DeviceServiceImpl(deviceDao){});

		when(deviceService.getDeviceTypeInstance(deviceEntity)).thenReturn(mockObject);
		when(deviceDao.update(mockObject)).thenReturn(typeFdevice);

		Device updatedDevice = deviceService.update(deviceEntity);

		assertEquals(updatedDevice, (Device) typeFdevice);
		verify(deviceService, times(1)).getDeviceTypeInstance(any(Device.class));
		verify(deviceDao, times(1)).update(any(Object.class));
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Test failed save due to missing peimary key */
	// --------------------------------------------------------------------------------------------------------------------------------
	@Test(expected = IllegalArgumentException.class)
	public void testFailingUpdateMissingPrimaryKey()
	{
		DeviceDao deviceDao = getSpiedInstance();

		// spy device service instance for save operation
		DeviceServiceImpl deviceService = spy(new DeviceServiceImpl(deviceDao){});
		deviceService.update(deviceEntity);
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Test failed save due to missing devType */
	// --------------------------------------------------------------------------------------------------------------------------------
	@Test(expected = IllegalArgumentException.class)
	public void testFailingUpdateMissingDevType()
	{
		deviceEntity.setPrimaryKey(12345L);
		DeviceDao deviceDao = getSpiedInstance();

		// spy device service instance for save operation
		DeviceServiceImpl deviceService = spy(new DeviceServiceImpl(deviceDao){});
		deviceService.update(deviceEntity);
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Test successful update of device parameters. */
	// --------------------------------------------------------------------------------------------------------------------------------
	@Test
	public void testSuccesfulUpdateDeviceParams()
	{
		@SuppressWarnings("unchecked")
		HashMap<String, String> mockParams = mock(HashMap.class);

		DeviceDao mockDeviceDao = mock(DeviceDao.class);
		Object typeFdevice = new MockTypeF();

		// spy device service instance for save operation
		DeviceServiceImpl deviceService = spy(new DeviceServiceImpl(mockDeviceDao){});

		when(mockParams.get("primaryKey")).thenReturn(String.valueOf(1L));
		when(mockParams.get("devType")).thenReturn("TYPE_F");
		when(mockDeviceDao.update(any(Object.class))).thenReturn(typeFdevice);

		Device updatedDevice = deviceService.updateDeviceParameters(mockParams);

		assertEquals(updatedDevice, (Device) typeFdevice);
		verify(mockParams, times(2)).get("primaryKey");
		verify(mockParams, times(2)).get("devType");
		verify(mockDeviceDao, times(1)).update(any(Object.class));
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Test failed parameters update due to null primary key */
	// --------------------------------------------------------------------------------------------------------------------------------
	@Test(expected = IllegalArgumentException.class)
	public void testFailingParamsUpdateNullPrimaryKey()
	{
		@SuppressWarnings("unchecked")
		HashMap<String, String> mockParams = mock(HashMap.class);
		DeviceDao deviceDao = getSpiedInstance();

		when(mockParams.get("primaryKey")).thenReturn(null);

		// spy device service instance for save operation
		DeviceServiceImpl deviceService = spy(new DeviceServiceImpl(deviceDao){});
		deviceService.updateDeviceParameters(mockParams);
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Test failed parameters update due to empty primary key */
	// --------------------------------------------------------------------------------------------------------------------------------
	@Test(expected = IllegalArgumentException.class)
	public void testFailingParamsUpdateEmptyPrimaryKey()
	{
		@SuppressWarnings("unchecked")
		HashMap<String, String> mockParams = mock(HashMap.class);
		DeviceDao deviceDao = getSpiedInstance();

		when(mockParams.get("primaryKey")).thenReturn("");

		// spy device service instance for save operation
		DeviceServiceImpl deviceService = spy(new DeviceServiceImpl(deviceDao){});
		deviceService.updateDeviceParameters(mockParams);
	}
}
