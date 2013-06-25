package com.app.domain.dto;

import java.util.ArrayList;
import java.util.List;


public class ViolationList
{
	private List<Violation> violations = new ArrayList<Violation>();
	
	// --------------------------------------------------------------------------------------------------------------------------------
	public ViolationList()
	{}

	// --------------------------------------------------------------------------------------------------------------------------------
	public ViolationList(List<Violation> violations)
	{
		this.violations = violations;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public List<Violation> getViolations()
	{
		return violations;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public void setViolations(List<Violation> violations)
	{
		this.violations = violations;
	}

}