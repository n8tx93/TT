
package com.hyc.T1.application.domain;

import java.util.List;
import org.dayatang.querychannel.Page;
import com.hyc.T1.application.dto.*;

public interface QuestionApplication {

	public QuestionDTO getQuestion(Long id);
	
	public QuestionDTO saveQuestion(QuestionDTO question);
	
	public void updateQuestion(QuestionDTO question);
	
	public void removeQuestion(Long id);
	
	public void removeQuestions(Long[] ids);
	
	public List<QuestionDTO> findAllQuestion();
	
	public Page<QuestionDTO> pageQueryQuestion(QuestionDTO question, int currentPage, int pageSize);
	
	public UserDTO findCreaterByQuestion(Long id);


		public RuleDTO findRuleByQuestion(Long id);


	
}

