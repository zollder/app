package com.app.dao;

import java.util.List;

import com.app.domain.model.AbstractBase;


public interface AbstractDao<T extends AbstractBase<T>>
{
	// --------------------------------------------------------------------------------------------------------------------------------
	/** Returns current class. */
	// --------------------------------------------------------------------------------------------------------------------------------
	public Class<T> getModelClass();

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Loads given entity by primary key (id). Returns loaded entity. */
	// --------------------------------------------------------------------------------------------------------------------------------
	public T loadWithPrimaryKey(Long key);

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Loads all entities. Returns a list of entities. */
	// --------------------------------------------------------------------------------------------------------------------------------
	public List<T> findAll();

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Saves given entity. Returns saved entity. */
	// --------------------------------------------------------------------------------------------------------------------------------
	public T save(T entity);

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Updates given entity. Returns updated entity. */
	// --------------------------------------------------------------------------------------------------------------------------------
	public T update(T entity);

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Deletes given entity. */
	// --------------------------------------------------------------------------------------------------------------------------------
	public boolean delete(Long key);

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Refreshes entity to ensure correct serialisation/marshalling (especially for @ManyToOne relations) */
	// --------------------------------------------------------------------------------------------------------------------------------
	public void refresh(T savedEntity);
}