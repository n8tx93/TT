package com.hyc.T1.application.impl.domain;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import org.apache.commons.beanutils.BeanUtils;
import org.dayatang.domain.InstanceFactory;
import org.dayatang.querychannel.Page;
import org.dayatang.querychannel.QueryChannelService;
import org.openkoala.koala.auth.core.domain.User;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hyc.T1.application.domain.RuleApplication;
import com.hyc.T1.application.dto.RuleDTO;
import com.hyc.T1.application.dto.UserDTO;
import com.hyc.T1.core.domain.Rule;

@Named
@Transactional
public class RuleApplicationImpl implements RuleApplication
{

	private QueryChannelService queryChannel;

	private QueryChannelService getQueryChannelService()
	{
		if (queryChannel == null)
		{
			queryChannel = InstanceFactory.getInstance(QueryChannelService.class, "queryChannel");
		}
		return queryChannel;
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public RuleDTO getRule(Long id)
	{
		Rule rule = Rule.load(Rule.class, id);
		RuleDTO ruleDTO = new RuleDTO();
		// 将domain转成VO
		try
		{
			BeanUtils.copyProperties(ruleDTO, rule);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		ruleDTO.setId((java.lang.Long) rule.getId());
		return ruleDTO;
	}

	public RuleDTO saveRule(RuleDTO ruleDTO)
	{
		Rule rule = new Rule();
		try
		{
			BeanUtils.copyProperties(rule, ruleDTO);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		rule.save();
		ruleDTO.setId((java.lang.Long) rule.getId());
		return ruleDTO;
	}

	public void updateRule(RuleDTO ruleDTO)
	{
		Rule rule = Rule.get(Rule.class, ruleDTO.getId());
		// 设置要更新的值
		try
		{
			BeanUtils.copyProperties(rule, ruleDTO);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void removeRule(Long id)
	{
		this.removeRules(new Long[]
		{ id });
	}

	public void removeRules(Long[] ids)
	{
		for (int i = 0; i < ids.length; i++)
		{
			Rule rule = Rule.load(Rule.class, ids[i]);
			rule.remove();
		}
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<RuleDTO> findAllRule()
	{
		List<RuleDTO> list = new ArrayList<RuleDTO>();
		List<Rule> all = Rule.findAll(Rule.class);
		for (Rule rule : all)
		{
			RuleDTO ruleDTO = new RuleDTO();
			// 将domain转成VO
			try
			{
				BeanUtils.copyProperties(ruleDTO, rule);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			list.add(ruleDTO);
		}
		return list;
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Page<RuleDTO> pageQueryRule(RuleDTO queryVo, int currentPage, int pageSize)
	{
		List<RuleDTO> result = new ArrayList<RuleDTO>();
		List<Object> conditionVals = new ArrayList<Object>();
		StringBuilder jpql = new StringBuilder("select _rule from Rule _rule   left join _rule.creater  where 1=1 ");

		if (queryVo.getName() != null && !"".equals(queryVo.getName()))
		{
			jpql.append(" and _rule.name like ?");
			conditionVals.add(MessageFormat.format("%{0}%", queryVo.getName()));
		}

		if (queryVo.getDescription() != null && !"".equals(queryVo.getDescription()))
		{
			jpql.append(" and _rule.description like ?");
			conditionVals.add(MessageFormat.format("%{0}%", queryVo.getDescription()));
		}

		if (queryVo.getPassword() != null && !"".equals(queryVo.getPassword()))
		{
			jpql.append(" and _rule.password like ?");
			conditionVals.add(MessageFormat.format("%{0}%", queryVo.getPassword()));
		}

		if (queryVo.getQType() != null && !"".equals(queryVo.getQType()))
		{
			jpql.append(" and _rule.qType like ?");
			conditionVals.add(MessageFormat.format("%{0}%", queryVo.getQType()));
		}

		if (queryVo.getKType() != null && !"".equals(queryVo.getKType()))
		{
			jpql.append(" and _rule.kType like ?");
			conditionVals.add(MessageFormat.format("%{0}%", queryVo.getKType()));
		}

		if (queryVo.getReleaseRule() != null && !"".equals(queryVo.getReleaseRule()))
		{
			jpql.append(" and _rule.releaseRule like ?");
			conditionVals.add(MessageFormat.format("%{0}%", queryVo.getReleaseRule()));
		}
		if (queryVo.getAnswerCount() != null)
		{
			jpql.append(" and _rule.answerCount=?");
			conditionVals.add(queryVo.getAnswerCount());
		}

		Page<Rule> pages = getQueryChannelService().createJpqlQuery(jpql.toString()).setParameters(conditionVals).setPage(currentPage, pageSize)
				.pagedList();
		for (Rule rule : pages.getData())
		{
			RuleDTO ruleDTO = new RuleDTO();

			// 将domain转成VO
			try
			{
				BeanUtils.copyProperties(ruleDTO, rule);
			} catch (Exception e)
			{
				e.printStackTrace();
			}

			result.add(ruleDTO);
		}
		return new Page<RuleDTO>(pages.getPageIndex(), pages.getResultCount(), pageSize, result);
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public UserDTO findCreaterByRule(Long id)
	{
		String jpql = "select e from Rule o right join o.creater e where o.id=?";
		User result = (User) getQueryChannelService().createJpqlQuery(jpql).setParameters(new Object[]
		{ id }).singleResult();
		UserDTO dto = new UserDTO();
		if (result != null)
		{
			try
			{
				BeanUtils.copyProperties(dto, result);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return dto;
	}

}
