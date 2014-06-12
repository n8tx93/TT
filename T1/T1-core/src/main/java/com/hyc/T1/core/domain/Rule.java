package com.hyc.T1.core.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "DDA_RULE")
public class Rule extends DdaAbstractEntity
{
	private static final long serialVersionUID = 1841601594134671580L;

	private String password;

	private String qType;

	private String kType;

	private String releaseRule;

	private int answerCount;

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getqType()
	{
		return qType;
	}

	public void setqType(String qType)
	{
		this.qType = qType;
	}

	public String getkType()
	{
		return kType;
	}

	public void setkType(String kType)
	{
		this.kType = kType;
	}

	public String getReleaseRule()
	{
		return releaseRule;
	}

	public void setReleaseRule(String releaseRule)
	{
		this.releaseRule = releaseRule;
	}

	public int getAnswerCount()
	{
		return answerCount;
	}

	public void setAnswerCount(int answerCount)
	{
		this.answerCount = answerCount;
	}

}
