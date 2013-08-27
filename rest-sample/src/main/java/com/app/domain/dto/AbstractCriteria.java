package com.app.domain.dto;

import java.io.Serializable;

//--------------------------------------------------------------------------------------------------------------------------------
/** AbstractCriteria base class implementation. */
//--------------------------------------------------------------------------------------------------------------------------------

public class AbstractCriteria implements Serializable
{
	private static final long serialVersionUID = 1L;

	private Long[] primaryKey;

	// --------------------------------------------------------------------------------------------------------------------------------
	public static boolean isTrue(Boolean theBigBoolean)
	{
		return theBigBoolean != null && theBigBoolean.booleanValue();
	}

	public Long[] getPrimaryKey()
	{
		return primaryKey;
	}

	public void setPrimaryKey(Long[] primaryKey)
	{
		this.primaryKey = primaryKey;
	}
}