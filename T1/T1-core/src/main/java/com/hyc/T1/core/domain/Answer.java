package com.hyc.T1.core.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "DDA_ANSWER")
public class Answer extends DdaAbstractEntity
{

	private static final long serialVersionUID = 7185428382715953164L;

	private Question question;

	private String answer;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "questionId")
	public Question getQuestion()
	{
		return question;
	}

	public void setQuestion(Question question)
	{
		this.question = question;
	}

	public String getAnswer()
	{
		return answer;
	}

	public void setAnswer(String answer)
	{
		this.answer = answer;
	}

	@Override
	public String[] businessKeys()
	{
		return new String[]
		{ "id" };
	}

}
