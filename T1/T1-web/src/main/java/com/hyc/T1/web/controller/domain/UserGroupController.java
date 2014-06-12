
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
import com.hyc.T1.application.domain.UserGroupApplication;
import com.hyc.T1.application.dto.*;

@Controller
@RequestMapping("/UserGroup")
public class UserGroupController {
		
	@Inject
	private UserGroupApplication userGroupApplication;
	
	@ResponseBody
	@RequestMapping("/add")
	public Map<String, Object> add(UserGroupDTO userGroupDTO) {
		Map<String, Object> result = new HashMap<String, Object>();
		userGroupApplication.saveUserGroup(userGroupDTO);
		result.put("result", "success");
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/update")
	public Map<String, Object> update(UserGroupDTO userGroupDTO) {
		Map<String, Object> result = new HashMap<String, Object>();
		userGroupApplication.updateUserGroup(userGroupDTO);
		result.put("result", "success");
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/pageJson")
	public Page pageJson(UserGroupDTO userGroupDTO, @RequestParam int page, @RequestParam int pagesize) {
		Page<UserGroupDTO> all = userGroupApplication.pageQueryUserGroup(userGroupDTO, page, pagesize);
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
        userGroupApplication.removeUserGroups(idArrs);
		result.put("result", "success");
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/get/{id}")
	public Map<String, Object> get(@PathVariable Long id) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", userGroupApplication.getUserGroup(id));
		return result;
	}
	
		@ResponseBody
	@RequestMapping("/findUserByUserGroup/{id}")
	public Map<String, Object> findUserByUserGroup(@PathVariable Long id) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", userGroupApplication.findUserByUserGroup(id));
		return result;
	}

	@ResponseBody
	@RequestMapping("/findGroupByUserGroup/{id}")
	public Map<String, Object> findGroupByUserGroup(@PathVariable Long id) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", userGroupApplication.findGroupByUserGroup(id));
		return result;
	}

	
}
