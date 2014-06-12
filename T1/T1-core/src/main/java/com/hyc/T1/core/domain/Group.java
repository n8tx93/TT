package com.hyc.T1.core.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "DDA_GROUP")
public class Group extends DdaAbstractEntity
{
	private static final long serialVersionUID = -1909306578841897576L;

	private String type;

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	@Override
	public String[] businessKeys()
	{
		return new String[]
		{ "id" };
	}

}
