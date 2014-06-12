package com.hyc.T1.core.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.openkoala.koala.commons.domain.KoalaAbstractEntity;

@Entity
@Table(name = "DDA_RULE_GROUP")
public class RuleGroup extends KoalaAbstractEntity
{

	private static final long serialVersionUID = 4122402259676637784L;

	private Group group;

	private Rule rule;

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

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "ruleId")
	public Rule getRule()
	{
		return rule;
	}

	public void setRule(Rule rule)
	{
		this.rule = rule;
	}

	@Override
	public String[] businessKeys()
	{
		return new String[]
		{ "id" };
	}

}
