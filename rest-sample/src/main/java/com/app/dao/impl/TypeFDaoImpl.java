package com.app.dao.impl;
 
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.app.dao.TypeFDao;
import com.app.domain.model.TypeF;

//--------------------------------------------------------------------------------------------------------------------------------
/** TypeF DAO concrete implementation. */
//--------------------------------------------------------------------------------------------------------------------------------

@Repository("typeFDao")
public class TypeFDaoImpl extends AbstractDaoImpl<TypeF> implements TypeFDao
{
	@Autowired
	public TypeFDaoImpl(@Qualifier("sessionFactory") SessionFactory sessionFactory)
	{
		super(sessionFactory);
		this.setModelClass(TypeF.class);
	}
}