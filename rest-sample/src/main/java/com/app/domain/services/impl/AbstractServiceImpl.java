package com.app.domain.services.impl;

import java.io.Serializable;

import org.springframework.transaction.annotation.Transactional;

import com.app.dao.AbstractDao;
import com.app.domain.model.AbstractBase;
import com.app.domain.services.AbstractService;

//--------------------------------------------------------------------------------------------------------------------------------
/**
* Provides implementation for standard business services around any {@link AbstractBase} entity
*/
//--------------------------------------------------------------------------------------------------------------------------------
public abstract class AbstractServiceImpl<T extends Serializable, D extends AbstractDao<T>> implements AbstractService<T>
{
	protected AbstractDao<T> entityDao;

	// --------------------------------------------------------------------------------------------------------------------------------
	public AbstractServiceImpl(AbstractDao<T> entityDao)
	{
		this.entityDao = entityDao;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	@SuppressWarnings("unchecked")
	protected D getDao()
	{
		return (D) entityDao;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	@Transactional(readOnly = true)
	public T loadWithPrimaryKey(Long key)
	{
		T entity = (T) entityDao.loadWithPrimaryKey(key);
	
		return entity;
	}

    // --------------------------------------------------------------------------------------------------------------------------------
	@Transactional
	public T save(T entity)
	{
		T savedEntity = entityDao.save(entity);

		return (T) savedEntity;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	@Transactional
	public T update(T entity)
	{
		T updatedEntity = entityDao.update(entity);
		
		return updatedEntity;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	@Transactional
	public void delete(Long key)
	{
		entityDao.delete(key);
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Refreshes saved Entity so ensure correct serialisation/marshalling (especially for @ManyToOne relations) */
	// --------------------------------------------------------------------------------------------------------------------------------
	@Transactional
	public void refresh(T savedEntity)
	{
		entityDao.refresh(savedEntity);		
	}
}
