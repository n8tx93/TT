
package com.hyc.T1.application.impl.domain;

import java.util.List;
import java.util.ArrayList;
import java.text.MessageFormat;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import org.dayatang.domain.InstanceFactory;
import org.dayatang.querychannel.Page;
import org.dayatang.querychannel.QueryChannelService;
import com.hyc.T1.application.dto.*;
import com.hyc.T1.application.domain.RuleGroupApplication;
import com.hyc.T1.core.domain.*;

@Named
@Transactional
public class RuleGroupApplicationImpl implements RuleGroupApplication {


	private QueryChannelService queryChannel;

    private QueryChannelService getQueryChannelService(){
       if(queryChannel==null){
          queryChannel = InstanceFactory.getInstance(QueryChannelService.class,"queryChannel");
       }
     return queryChannel;
    }
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public RuleGroupDTO getRuleGroup(Long id) {
		RuleGroup ruleGroup = RuleGroup.load(RuleGroup.class, id);
		RuleGroupDTO ruleGroupDTO = new RuleGroupDTO();
		// 将domain转成VO
		try {
			BeanUtils.copyProperties(ruleGroupDTO, ruleGroup);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ruleGroupDTO.setId((java.lang.Long)ruleGroup.getId());
		return ruleGroupDTO;
	}
	
	public RuleGroupDTO saveRuleGroup(RuleGroupDTO ruleGroupDTO) {
		RuleGroup ruleGroup = new RuleGroup();
		try {
        	BeanUtils.copyProperties(ruleGroup, ruleGroupDTO);
        } catch (Exception e) {
        	e.printStackTrace();
        }
		ruleGroup.save();
		ruleGroupDTO.setId((java.lang.Long)ruleGroup.getId());
		return ruleGroupDTO;
	}
	
	public void updateRuleGroup(RuleGroupDTO ruleGroupDTO) {
		RuleGroup ruleGroup = RuleGroup.get(RuleGroup.class, ruleGroupDTO.getId());
		// 设置要更新的值
		try {
			BeanUtils.copyProperties(ruleGroup, ruleGroupDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void removeRuleGroup(Long id) {
		this.removeRuleGroups(new Long[] { id });
	}
	
	public void removeRuleGroups(Long[] ids) {
		for (int i = 0; i < ids.length; i++) {
			RuleGroup ruleGroup = RuleGroup.load(RuleGroup.class, ids[i]);
			ruleGroup.remove();
		}
	}
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<RuleGroupDTO> findAllRuleGroup() {
		List<RuleGroupDTO> list = new ArrayList<RuleGroupDTO>();
		List<RuleGroup> all = RuleGroup.findAll(RuleGroup.class);
		for (RuleGroup ruleGroup : all) {
			RuleGroupDTO ruleGroupDTO = new RuleGroupDTO();
			// 将domain转成VO
			try {
				BeanUtils.copyProperties(ruleGroupDTO, ruleGroup);
			} catch (Exception e) {
				e.printStackTrace();
			}
			list.add(ruleGroupDTO);
		}
		return list;
	}
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Page<RuleGroupDTO> pageQueryRuleGroup(RuleGroupDTO queryVo, int currentPage, int pageSize) {
		List<RuleGroupDTO> result = new ArrayList<RuleGroupDTO>();
		List<Object> conditionVals = new ArrayList<Object>();
	   	StringBuilder jpql = new StringBuilder("select _ruleGroup from RuleGroup _ruleGroup   left join _ruleGroup.group  left join _ruleGroup.rule  where 1=1 ");
	
	
	
        Page<RuleGroup> pages = getQueryChannelService().createJpqlQuery(jpql.toString()).setParameters(conditionVals).setPage(currentPage, pageSize).pagedList();
        for (RuleGroup ruleGroup : pages.getData()) {
            RuleGroupDTO ruleGroupDTO = new RuleGroupDTO();
            
             // 将domain转成VO
            try {
            	BeanUtils.copyProperties(ruleGroupDTO, ruleGroup);
            } catch (Exception e) {
            	e.printStackTrace();
            } 
            
                  result.add(ruleGroupDTO);
        }
        return new Page<RuleGroupDTO>(pages.getPageIndex(), pages.getResultCount(), pageSize, result);
	}
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public GroupDTO findGroupByRuleGroup(Long id) {
		String jpql = "select e from RuleGroup o right join o.group e where o.id=?";
		Group result = (Group) getQueryChannelService().createJpqlQuery(jpql).setParameters(new Object[] { id }).singleResult();
		GroupDTO  dto = new GroupDTO();
		if (result != null) {
			try {
				BeanUtils.copyProperties(dto, result);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return dto;
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public RuleDTO findRuleByRuleGroup(Long id) {
		String jpql = "select e from RuleGroup o right join o.rule e where o.id=?";
		Rule result = (Rule) getQueryChannelService().createJpqlQuery(jpql).setParameters(new Object[] { id }).singleResult();
		RuleDTO  dto = new RuleDTO();
		if (result != null) {
			try {
				BeanUtils.copyProperties(dto, result);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return dto;
	}

	
}
