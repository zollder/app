package com.app.domain.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.TypeFDao;
import com.app.domain.model.TypeF;
import com.app.domain.services.TypeFService;

@Service("typeFService")
public class TypeFServiceImpl extends AbstractServiceImpl<TypeF, TypeFDao> implements TypeFService
{
	@Autowired
	public TypeFServiceImpl(TypeFDao typeFDao)
	{
		super(typeFDao);
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public Class<TypeF> getModelClass()
	{
		return this.getModelClass();
	}
}
