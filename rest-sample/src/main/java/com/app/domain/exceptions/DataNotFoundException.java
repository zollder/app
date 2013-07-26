package com.app.domain.exceptions;

import java.util.Set;
import javax.validation.ConstraintViolation;

import com.app.domain.dto.Violation;

//--------------------------------------------------------------------------------------------------------------------------------
/** Data not found exception class. To be used when a data retrieval fails because the requested information doesn't exist. */
//--------------------------------------------------------------------------------------------------------------------------------

public class DataNotFoundException extends BaseException
{
	private static final long serialVersionUID = 2470837262313645109L;

	//--------------------------------------------------------------------------------------------------------------------------------
	/** Default constructor */
	//--------------------------------------------------------------------------------------------------------------------------------
	public DataNotFoundException()
	{
		super();
	}

	//--------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Constructor with message
	 */
	//--------------------------------------------------------------------------------------------------------------------------------
	public DataNotFoundException(String message)
	{
		super(message);
	}
	
	//--------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Constructor with message and violation
	 */
	//--------------------------------------------------------------------------------------------------------------------------------
	public DataNotFoundException(String message, Violation violation)
	{
		super(message, violation);
	}
	
	//--------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Constructor with message and violation message
	 */
	//--------------------------------------------------------------------------------------------------------------------------------
	public DataNotFoundException(String message, String violationMessageKey, Object... args)
	{
		super(message, violationMessageKey, args);
	}
	
	//--------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Constructor with message and violation message
	 */
	//--------------------------------------------------------------------------------------------------------------------------------
	public DataNotFoundException(String message, Throwable cause, String violationMessageKey, Object... args)
	{
		super(message, cause, violationMessageKey, args);
	}

	//--------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Constructor with message and root cause
	 */
	//--------------------------------------------------------------------------------------------------------------------------------
	public DataNotFoundException(String message, Throwable cause)
	{
		super(message, cause);
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Constructor with message and reported constraint violations
	 */
	// --------------------------------------------------------------------------------------------------------------------------------
	public DataNotFoundException(String message, Set<ConstraintViolation<?>> pViolations)
	{
		super(message, pViolations);
	}
}
