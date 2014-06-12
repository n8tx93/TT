
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
import com.hyc.T1.application.domain.RuleGroupApplication;
import com.hyc.T1.application.dto.*;

@Controller
@RequestMapping("/RuleGroup")
public class RuleGroupController {
		
	@Inject
	private RuleGroupApplication ruleGroupApplication;
	
	@ResponseBody
	@RequestMapping("/add")
	public Map<String, Object> add(RuleGroupDTO ruleGroupDTO) {
		Map<String, Object> result = new HashMap<String, Object>();
		ruleGroupApplication.saveRuleGroup(ruleGroupDTO);
		result.put("result", "success");
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/update")
	public Map<String, Object> update(RuleGroupDTO ruleGroupDTO) {
		Map<String, Object> result = new HashMap<String, Object>();
		ruleGroupApplication.updateRuleGroup(ruleGroupDTO);
		result.put("result", "success");
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/pageJson")
	public Page pageJson(RuleGroupDTO ruleGroupDTO, @RequestParam int page, @RequestParam int pagesize) {
		Page<RuleGroupDTO> all = ruleGroupApplication.pageQueryRuleGroup(ruleGroupDTO, page, pagesize);
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
        ruleGroupApplication.removeRuleGroups(idArrs);
		result.put("result", "success");
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/get/{id}")
	public Map<String, Object> get(@PathVariable Long id) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", ruleGroupApplication.getRuleGroup(id));
		return result;
	}
	
		@ResponseBody
	@RequestMapping("/findGroupByRuleGroup/{id}")
	public Map<String, Object> findGroupByRuleGroup(@PathVariable Long id) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", ruleGroupApplication.findGroupByRuleGroup(id));
		return result;
	}

	@ResponseBody
	@RequestMapping("/findRuleByRuleGroup/{id}")
	public Map<String, Object> findRuleByRuleGroup(@PathVariable Long id) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", ruleGroupApplication.findRuleByRuleGroup(id));
		return result;
	}

	
}
