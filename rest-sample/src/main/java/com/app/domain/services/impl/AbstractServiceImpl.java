package com.app.domain.services.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.app.dao.AbstractDao;
import com.app.domain.exceptions.DataNotFoundException;
import com.app.domain.services.AbstractService;

//--------------------------------------------------------------------------------------------------------------------------------
/** Provides implementation for standard business services around any entity */
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
	public Class<T> getModelClazz()
	{
		return entityDao.getModelClass();
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public String getClazzName()
	{
		return getModelClazz().getSimpleName();
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	@SuppressWarnings("unchecked")
	public D getDao()
	{
		return (D) entityDao;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	@Transactional(readOnly = true)
	public T loadWithPrimaryKey(Long key)
	{
		T entity = (T) entityDao.loadWithPrimaryKey(key);

		if (entity == null)
			throw new DataNotFoundException(String.format(getClazzName() + " with primarykey '%s' not found", key));

		return entity;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	@Transactional(readOnly = true)
	public List<T> findAll()
	{
		return entityDao.findAll();
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
		boolean deleted = entityDao.delete(key);

		if (!deleted)
			throw new DataNotFoundException(String.format("entity with key '%s' not found", key));
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Refreshes saved Entity so ensure correct serialization/marshaling (especially for @ManyToOne relations) */
	// --------------------------------------------------------------------------------------------------------------------------------
	@Transactional
	public void refresh(T savedEntity)
	{
		entityDao.refresh(savedEntity);		
	}
}
