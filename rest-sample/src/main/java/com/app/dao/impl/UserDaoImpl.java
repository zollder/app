package com.app.dao.impl;
 
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.app.dao.UserDao;
import com.app.domain.model.User;

//--------------------------------------------------------------------------------------------------------------------------------
/** User DAO concrete implementation. */
//--------------------------------------------------------------------------------------------------------------------------------

@Repository("userDao")
public class UserDaoImpl extends AbstractDaoImpl<User> implements UserDao
{
	@Autowired
	public UserDaoImpl(@Qualifier("sessionFactory") SessionFactory sessionFactory)
	{
		super(sessionFactory);

		// this is a hack, find a better way
		this.setModelClass(User.class);
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public User findByUserName(String username)
	{
		// TODO: revise the code: use criteria approach
		Session session = this.getCurrentSession();
		User user = (User) session.createQuery("from User usr where usr.userName = ?").setString(0, username).uniqueResult();
		return user;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	// TODO: revise to make it generic
	public User findByCriteria(String username)
	{
		Session session = this.getCurrentSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("userName", username));
		criteria.setMaxResults(1);

		User user = (User) criteria.uniqueResult();
		return user;
	}
}