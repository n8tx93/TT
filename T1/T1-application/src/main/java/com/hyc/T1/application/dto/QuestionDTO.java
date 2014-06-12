package com.hyc.T1.application.dto;

import java.io.Serializable;

import org.springframework.format.annotation.DateTimeFormat;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.openkoala.koala.springmvc.JsonTimestampSerializer;
import org.openkoala.koala.springmvc.JsonDateSerializer;


public class QuestionDTO implements Serializable {

	private Long id;

		
	private String correctAnswer;
	
		
	private String status;
	
		
	private String description;
	
		
	private String name;
	
			
		

	public void setCorrectAnswer(String correctAnswer) { 
		this.correctAnswer = correctAnswer;
	}

	public String getCorrectAnswer() {
		return this.correctAnswer;
	}
	
			
		

	public void setStatus(String status) { 
		this.status = status;
	}

	public String getStatus() {
		return this.status;
	}
	
			
		

	public void setDescription(String description) { 
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}
	
			
		

	public void setName(String name) { 
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
	

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		QuestionDTO other = (QuestionDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}