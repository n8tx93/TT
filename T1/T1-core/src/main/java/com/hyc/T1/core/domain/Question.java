package com.hyc.T1.core.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "DDA_QUESTION")
public class Question extends DdaAbstractEntity
{
	private static final long serialVersionUID = 4154820811016992362L;

	private String correctAnswer;

	private String status;

	private Rule rule;

	public String getCorrectAnswer()
	{
		return correctAnswer;
	}

	public void setCorrectAnswer(String correctAnswer)
	{
		this.correctAnswer = correctAnswer;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
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
