
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
import com.hyc.T1.application.domain.RuleApplication;
import com.hyc.T1.application.dto.*;

@Controller
@RequestMapping("/Rule")
public class RuleController {
		
	@Inject
	private RuleApplication ruleApplication;
	
	@ResponseBody
	@RequestMapping("/add")
	public Map<String, Object> add(RuleDTO ruleDTO) {
		Map<String, Object> result = new HashMap<String, Object>();
		ruleApplication.saveRule(ruleDTO);
		result.put("result", "success");
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/update")
	public Map<String, Object> update(RuleDTO ruleDTO) {
		Map<String, Object> result = new HashMap<String, Object>();
		ruleApplication.updateRule(ruleDTO);
		result.put("result", "success");
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/pageJson")
	public Page pageJson(RuleDTO ruleDTO, @RequestParam int page, @RequestParam int pagesize) {
		Page<RuleDTO> all = ruleApplication.pageQueryRule(ruleDTO, page, pagesize);
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
        ruleApplication.removeRules(idArrs);
		result.put("result", "success");
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/get/{id}")
	public Map<String, Object> get(@PathVariable Long id) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", ruleApplication.getRule(id));
		return result;
	}
	
		@ResponseBody
	@RequestMapping("/findCreaterByRule/{id}")
	public Map<String, Object> findCreaterByRule(@PathVariable Long id) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", ruleApplication.findCreaterByRule(id));
		return result;
	}

	
}
