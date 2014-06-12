package com.hyc.T1.core.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.openkoala.koala.auth.core.domain.User;
import org.openkoala.koala.commons.domain.KoalaAbstractEntity;

@Entity
@Table(name = "DDA_USER_GROUP")
public class UserGroup extends KoalaAbstractEntity
{

	private static final long serialVersionUID = 2647342847663218128L;

	private User user;

	private Group group;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "userId")
	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "groupId")
	public Group getGroup()
	{
		return group;
	}

	public void setGroup(Group group)
	{
		this.group = group;
	}

	@Override
	public String[] businessKeys()
	{
		return new String[]
		{ "id" };
	}

}
