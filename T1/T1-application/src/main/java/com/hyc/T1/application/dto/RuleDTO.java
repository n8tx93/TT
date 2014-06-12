package com.hyc.T1.application.dto;

import java.io.Serializable;

import org.springframework.format.annotation.DateTimeFormat;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.openkoala.koala.springmvc.JsonTimestampSerializer;
import org.openkoala.koala.springmvc.JsonDateSerializer;


public class RuleDTO implements Serializable {

	private Long id;

						
	private Integer answerCount;
	
		
	private String qType;
	
		
	private String description;
	
		
	private String name;
	
		
	private String kType;
	
		
	private String releaseRule;
	
		
	private String password;
	
								
		

	public void setAnswerCount(Integer answerCount) { 
		this.answerCount = answerCount;
	}

	public Integer getAnswerCount() {
		return this.answerCount;
	}
	
			
		

	public void setQType(String qType) { 
		this.qType = qType;
	}

	public String getQType() {
		return this.qType;
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
	
			
		

	public void setKType(String kType) { 
		this.kType = kType;
	}

	public String getKType() {
		return this.kType;
	}
	
			
		

	public void setReleaseRule(String releaseRule) { 
		this.releaseRule = releaseRule;
	}

	public String getReleaseRule() {
		return this.releaseRule;
	}
	
			
		

	public void setPassword(String password) { 
		this.password = password;
	}

	public String getPassword() {
		return this.password;
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
		RuleDTO other = (RuleDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}