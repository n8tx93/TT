
package com.hyc.T1.application.domain;

import java.util.List;
import org.dayatang.querychannel.Page;
import com.hyc.T1.application.dto.*;

public interface AnswerApplication {

	public AnswerDTO getAnswer(Long id);
	
	public AnswerDTO saveAnswer(AnswerDTO answer);
	
	public void updateAnswer(AnswerDTO answer);
	
	public void removeAnswer(Long id);
	
	public void removeAnswers(Long[] ids);
	
	public List<AnswerDTO> findAllAnswer();
	
	public Page<AnswerDTO> pageQueryAnswer(AnswerDTO answer, int currentPage, int pageSize);
	
	public UserDTO findCreaterByAnswer(Long id);


		public QuestionDTO findQuestionByAnswer(Long id);


	
}

