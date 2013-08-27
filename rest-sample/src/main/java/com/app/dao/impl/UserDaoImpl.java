package com.app.dao.impl;
 
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.app.dao.UserDao;
import com.app.domain.dto.UserCriteria;
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
	public List<User> findByCriteria(UserCriteria userCriteria)
	{
		User userExample = new User();
		userExample.setFirstName(userCriteria.getFirstName());
		userExample.setLastName(userCriteria.getLastName());
		userExample.setUserName(userCriteria.getUserName());
		userExample.setEmail(userCriteria.getEmail());
		userExample.setIsAdmin(userCriteria.getIsAdmin());
		userExample.setIsEnabled(userCriteria.getIsEnabled());
		userExample.setCanLogin(userCriteria.getCanLogin());

		List<User> userList = this.findByExample(userExample);

		return userList;
	}
}