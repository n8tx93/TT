
package com.hyc.T1.application.domain;

import java.util.List;
import org.dayatang.querychannel.Page;
import com.hyc.T1.application.dto.*;

public interface RuleApplication {

	public RuleDTO getRule(Long id);
	
	public RuleDTO saveRule(RuleDTO rule);
	
	public void updateRule(RuleDTO rule);
	
	public void removeRule(Long id);
	
	public void removeRules(Long[] ids);
	
	public List<RuleDTO> findAllRule();
	
	public Page<RuleDTO> pageQueryRule(RuleDTO rule, int currentPage, int pageSize);
	
	public UserDTO findCreaterByRule(Long id);


	
}

