
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
import com.hyc.T1.application.domain.QuestionApplication;
import com.hyc.T1.application.dto.*;

@Controller
@RequestMapping("/Question")
public class QuestionController {
		
	@Inject
	private QuestionApplication questionApplication;
	
	@ResponseBody
	@RequestMapping("/add")
	public Map<String, Object> add(QuestionDTO questionDTO) {
		Map<String, Object> result = new HashMap<String, Object>();
		questionApplication.saveQuestion(questionDTO);
		result.put("result", "success");
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/update")
	public Map<String, Object> update(QuestionDTO questionDTO) {
		Map<String, Object> result = new HashMap<String, Object>();
		questionApplication.updateQuestion(questionDTO);
		result.put("result", "success");
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/pageJson")
	public Page pageJson(QuestionDTO questionDTO, @RequestParam int page, @RequestParam int pagesize) {
		Page<QuestionDTO> all = questionApplication.pageQueryQuestion(questionDTO, page, pagesize);
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
        questionApplication.removeQuestions(idArrs);
		result.put("result", "success");
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/get/{id}")
	public Map<String, Object> get(@PathVariable Long id) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", questionApplication.getQuestion(id));
		return result;
	}
	
		@ResponseBody
	@RequestMapping("/findCreaterByQuestion/{id}")
	public Map<String, Object> findCreaterByQuestion(@PathVariable Long id) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", questionApplication.findCreaterByQuestion(id));
		return result;
	}

	@ResponseBody
	@RequestMapping("/findRuleByQuestion/{id}")
	public Map<String, Object> findRuleByQuestion(@PathVariable Long id) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", questionApplication.findRuleByQuestion(id));
		return result;
	}

	
}
