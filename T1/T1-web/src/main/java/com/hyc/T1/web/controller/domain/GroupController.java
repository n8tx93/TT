
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
import com.hyc.T1.application.domain.GroupApplication;
import com.hyc.T1.application.dto.*;

@Controller
@RequestMapping("/Group")
public class GroupController {
		
	@Inject
	private GroupApplication groupApplication;
	
	@ResponseBody
	@RequestMapping("/add")
	public Map<String, Object> add(GroupDTO groupDTO) {
		Map<String, Object> result = new HashMap<String, Object>();
		groupApplication.saveGroup(groupDTO);
		result.put("result", "success");
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/update")
	public Map<String, Object> update(GroupDTO groupDTO) {
		Map<String, Object> result = new HashMap<String, Object>();
		groupApplication.updateGroup(groupDTO);
		result.put("result", "success");
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/pageJson")
	public Page pageJson(GroupDTO groupDTO, @RequestParam int page, @RequestParam int pagesize) {
		Page<GroupDTO> all = groupApplication.pageQueryGroup(groupDTO, page, pagesize);
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
        groupApplication.removeGroups(idArrs);
		result.put("result", "success");
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/get/{id}")
	public Map<String, Object> get(@PathVariable Long id) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", groupApplication.getGroup(id));
		return result;
	}
	
		@ResponseBody
	@RequestMapping("/findCreaterByGroup/{id}")
	public Map<String, Object> findCreaterByGroup(@PathVariable Long id) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", groupApplication.findCreaterByGroup(id));
		return result;
	}

	
}
