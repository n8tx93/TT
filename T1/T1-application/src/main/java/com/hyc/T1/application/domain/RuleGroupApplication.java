
package com.hyc.T1.application.domain;

import java.util.List;
import org.dayatang.querychannel.Page;
import com.hyc.T1.application.dto.*;

public interface RuleGroupApplication {

	public RuleGroupDTO getRuleGroup(Long id);
	
	public RuleGroupDTO saveRuleGroup(RuleGroupDTO ruleGroup);
	
	public void updateRuleGroup(RuleGroupDTO ruleGroup);
	
	public void removeRuleGroup(Long id);
	
	public void removeRuleGroups(Long[] ids);
	
	public List<RuleGroupDTO> findAllRuleGroup();
	
	public Page<RuleGroupDTO> pageQueryRuleGroup(RuleGroupDTO ruleGroup, int currentPage, int pageSize);
	
	public GroupDTO findGroupByRuleGroup(Long id);


		public RuleDTO findRuleByRuleGroup(Long id);


	
}

