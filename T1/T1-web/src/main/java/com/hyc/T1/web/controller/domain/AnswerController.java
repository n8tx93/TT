
package com.hyc.T1.web.controller.domain;

import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.dayatang.querychannel.Page;
import com.hyc.T1.application.domain.AnswerApplication;
import com.hyc.T1.application.dto.*;

@Controller
@RequestMapping("/Answer")
public class AnswerController {
		
	@Inject
	private AnswerApplication answerApplication;
	
	@ResponseBody
	@RequestMapping("/add")
	public Map<String, Object> add(AnswerDTO answerDTO) {
		Map<String, Object> result = new HashMap<String, Object>();
		answerApplication.saveAnswer(answerDTO);
		result.put("result", "success");
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/update")
	public Map<String, Object> update(AnswerDTO answerDTO) {
		Map<String, Object> result = new HashMap<String, Object>();
		answerApplication.updateAnswer(answerDTO);
		result.put("result", "success");
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/pageJson")
	public Page pageJson(AnswerDTO answerDTO, @RequestParam int page, @RequestParam int pagesize) {
		Page<AnswerDTO> all = answerApplication.pageQueryAnswer(answerDTO, page, pagesize);
		return all;
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public Map<String, Object> delete(@RequestParam String ids) {
		Map<String, Object> result = new HashMap<String, Object>();
		String[] value = ids.split(",");
        Long[] idArrs = new Long[value.length];
        for (int i = 0; i < value.length; i ++) {
        	
        	        					idArrs[i] = Long.parseLong(value[i]);
						        	
        }
        answerApplication.removeAnswers(idArrs);
		result.put("result", "success");
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/get/{id}")
	public Map<String, Object> get(@PathVariable Long id) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", answerApplication.getAnswer(id));
		return result;
	}
	
		@ResponseBody
	@RequestMapping("/findCreaterByAnswer/{id}")
	public Map<String, Object> findCreaterByAnswer(@PathVariable Long id) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", answerApplication.findCreaterByAnswer(id));
		return result;
	}

	@ResponseBody
	@RequestMapping("/findQuestionByAnswer/{id}")
	public Map<String, Object> findQuestionByAnswer(@PathVariable Long id) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", answerApplication.findQuestionByAnswer(id));
		return result;
	}

	
}
