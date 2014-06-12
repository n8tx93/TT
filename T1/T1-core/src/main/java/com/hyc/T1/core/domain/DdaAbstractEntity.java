package com.hyc.T1.core.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.openkoala.koala.auth.core.domain.User;
import org.openkoala.koala.commons.domain.KoalaAbstractEntity;

@Entity
public abstract class DdaAbstractEntity extends KoalaAbstractEntity
{

	private static final long serialVersionUID = 7934889042089226446L;

	private String name;

	private String description;

	private User creater;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "createrId")
	public User getCreater()
	{
		return creater;
	}

	public void setCreater(User creater)
	{
		this.creater = creater;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	@Override
	public String[] businessKeys()
	{
		return new String[]
		{ "id" };
	}
}
