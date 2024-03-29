package com.hyc.T1.web.controller.organization;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import org.dayatang.querychannel.Page;
import org.openkoala.organisation.HasPrincipalPostYetException;
import org.openkoala.organisation.IdNumberIsExistException;
import org.openkoala.organisation.SnIsExistException;
import org.openkoala.organisation.EmployeeMustHaveAtLeastOnePostException;
import org.openkoala.organisation.application.EmployeeApplication;
import org.openkoala.organisation.application.dto.EmployeeDTO;
import org.openkoala.organisation.application.dto.ResponsiblePostDTO;
import org.openkoala.organisation.domain.Employee;
import org.openkoala.organisation.domain.Gender;
import org.openkoala.organisation.domain.Organization;
import org.openkoala.organisation.domain.Post;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * 员工管理controller
 * @author xmfang
 *
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController extends BaseController {
	
	@Inject
	private EmployeeApplication employeeApplication;
	
	/**
	 * 分页查询员工
	 * @param page
	 * @param pagesize
	 * @param example
	 * @return
	 */
	@ResponseBody
    @RequestMapping("/pagingquery")
	public Page pagingQuery(int page, int pagesize, EmployeeDTO example) {
		Page<EmployeeDTO> employees = employeeApplication.pagingQueryEmployees(example, page, pagesize);
		return employees;
	}
	
	/**
	 * 分页查询某个机构下的员工
	 * @param page
	 * @param pagesize
	 * @param example
	 * @param organizationId
	 * @return
	 */
	@ResponseBody
    @RequestMapping("/pagingquery-by-org")
	public Page pagingQueryByOrganization(int page, int pagesize, EmployeeDTO example, Long organizationId, boolean queryAllChildren) {
		
		Page<EmployeeDTO> employees = null;
		if (organizationId == 0) {
			employees = employeeApplication.pagingQueryEmployeesWhoNoPost(example, page, pagesize);
		} else {
			Organization organization = getBaseApplication().getEntity(Organization.class, organizationId);
			if (queryAllChildren) {
				employees = employeeApplication.pagingQueryEmployeesByOrganizationAndChildren(example, organization, page, pagesize);
			} else {
				employees = employeeApplication.pagingQueryEmployeesByOrganization(example, organization, page, pagesize);
			}
		}
		
		return employees;
	}

	/**
	 * 创建一个员工
	 * @param employee
	 * @param jobId
	 * @param organizationId
	 * @return
	 */
	@ResponseBody
    @RequestMapping("/create")
	public Map<String, Object> createEmployee(Employee employee, Long postId) {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		try {
			Post post = null;
			if (postId != null) {
				post = getBaseApplication().getEntity(Post.class, postId);
			}
			
			employeeApplication.createEmployeeWithPost(employee, post);
			dataMap.put("result", "success");
		} catch (SnIsExistException exception) {
			dataMap.put("result", "员工编号: " + employee.getSn() + " 已被使用！");
		} catch (IdNumberIsExistException exception) {
			dataMap.put("result", "不能使用与其他人一样的证件号码！");
		} catch (Exception e) {
			dataMap.put("result", "保存失败！");
			e.printStackTrace();
		}
		return dataMap;
	}

	/**
	 * 更新某个员工的信息
	 * @param employee
	 * @return
	 */
	@ResponseBody
    @RequestMapping("/update")
	public Map<String, Object> updateEmployee(Employee employee) {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		try {
			getBaseApplication().updateParty(employee);
			dataMap.put("result", "success");
		} catch (SnIsExistException exception) {
			dataMap.put("result", "员工编号: " + employee.getSn() + " 已被使用！");
		} catch (IdNumberIsExistException exception) {
			dataMap.put("result", "不能使用与其他人一样的证件号码！");
		} catch (Exception e) {
			dataMap.put("result", "修改失败！");
			e.printStackTrace();
		}
		return dataMap;
	}

	/**
	 * 调整某个员工的任职信息
	 * @param employeeId
	 * @param responsibleJobHoldings
	 * @return
	 */
	@ResponseBody
    @RequestMapping(value = "/transform-post", method = RequestMethod.POST, consumes = "application/json")
	public Map<String, Object> transformPost(Long employeeId, @RequestBody ResponsiblePostDTO[] responsibleJobHoldings) {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		try {
			Employee employee = getBaseApplication().getEntity(Employee.class, employeeId);
			employeeApplication.transformPost(employee, new HashSet<ResponsiblePostDTO>(Arrays.asList(responsibleJobHoldings)));
			dataMap.put("result", "success");
		} catch (HasPrincipalPostYetException exception) {
			dataMap.put("result", "该员工已经有主任职岗位！");
			exception.printStackTrace();
		} catch (EmployeeMustHaveAtLeastOnePostException exception) {
			dataMap.put("result", "必须保证每名员工至少在一个岗位上任职！");
			exception.printStackTrace();
		} catch (Exception e) {
			dataMap.put("result", "调整职务失败！");
			e.printStackTrace();
		}
		return dataMap;
	}

	/**
	 * 根据ID号获得员工
	 * @param id
	 * @return
	 */
    @ResponseBody
    @RequestMapping("/get/{id}")
	public Map<String,Object> get(@PathVariable("id") Long id) {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		try {
			dataMap.put("data", employeeApplication.getEmployeeById(id));
		} catch (Exception e) {
            dataMap.put("error", "查询指定职务失败！");
        	e.printStackTrace();
		}
		return dataMap;
	}

    /**
     * 获取性别列表
     * @return
     */
    @ResponseBody
    @RequestMapping("/genders")
	public Map<String,Object> getGendens() {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		Map<String, String> genders = new HashMap<String, String>();
		
		for (Gender gender : Gender.values()) {
			genders.put(gender.name(), gender.getLabel());
		}
		
		dataMap.put("data", genders);
		return dataMap;
	}

    /**
     * 获得某个员工的任职信息
     * @param employeeId
     * @return
     */
    @ResponseBody
    @RequestMapping("/get-posts-by-employee")
	public Map<String,Object> getPostsByEmployee(Long employeeId) {
		Employee employee = getBaseApplication().getEntity(Employee.class, employeeId);
		
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("data", employeeApplication.getPostsByEmployee(employee));
		return dataMap;
	}
    
    /**
     * 解雇某名员工
     * @param employeeDTO
     * @return
     */
	@ResponseBody
    @RequestMapping("/terminate")
	public Map<String, Object> terminateEmployee(EmployeeDTO employeeDTO) {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		getBaseApplication().terminateParty(employeeDTO.transFormToEmployee());
		dataMap.put("result", "success");
		return dataMap;
	}
	
	/**
	 * 同时解雇多名员工
	 * @param employeeDtos
	 * @return
	 */
	@ResponseBody
    @RequestMapping(value = "/terminate-employees", method = RequestMethod.POST, consumes = "application/json")
	public Map<String, Object> terminateEmployees(@RequestBody EmployeeDTO[] employeeDtos) {
		Set<Employee> employees = new HashSet<Employee>();
		for (EmployeeDTO employeeDTO : employeeDtos) {
			employees.add(employeeDTO.transFormToEmployee());
		}
		
		Map<String, Object> dataMap = new HashMap<String, Object>();
		getBaseApplication().terminateParties(employees);
		dataMap.put("result", "success");
		return dataMap;
	}

}
