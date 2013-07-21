package com.app.dao.impl;
 
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.app.dao.DeviceDao;
import com.app.domain.model.Device;

//--------------------------------------------------------------------------------------------------------------------------------
/** Device DAO concrete implementation. */
//--------------------------------------------------------------------------------------------------------------------------------

@Repository("deviceDao")
public class DeviceDaoImpl extends AbstractDaoImpl<Device> implements DeviceDao
{
	@Autowired
	public DeviceDaoImpl(@Qualifier("sessionFactory") SessionFactory sessionFactory)
	{
		super(sessionFactory);

		// this is a hack, find a better way
		this.setModelClass(Device.class);
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public Device findByIp(String ip)
	{
		Session session = this.getCurrentSession();
		Device device = (Device) session.createQuery("from Device device where device.devIp = ?").setString(0, ip).uniqueResult();
		return device;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public Device findByMac(String mac)
	{
		Session session = this.getCurrentSession();
		Device device = (Device) session.createQuery("from Device device where device.devMac = ?").setString(0, mac).uniqueResult();
		return device;
	}
}