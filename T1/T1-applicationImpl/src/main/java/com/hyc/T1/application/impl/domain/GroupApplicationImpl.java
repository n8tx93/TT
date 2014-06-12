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

import com.hyc.T1.application.domain.GroupApplication;
import com.hyc.T1.application.dto.GroupDTO;
import com.hyc.T1.application.dto.UserDTO;
import com.hyc.T1.core.domain.Group;

@Named
@Transactional
public class GroupApplicationImpl implements GroupApplication
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
	public GroupDTO getGroup(Long id)
	{
		Group group = Group.load(Group.class, id);
		GroupDTO groupDTO = new GroupDTO();
		// 将domain转成VO
		try
		{
			BeanUtils.copyProperties(groupDTO, group);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		groupDTO.setId((java.lang.Long) group.getId());
		return groupDTO;
	}

	public GroupDTO saveGroup(GroupDTO groupDTO)
	{
		Group group = new Group();
		try
		{
			BeanUtils.copyProperties(group, groupDTO);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		group.save();
		groupDTO.setId((java.lang.Long) group.getId());
		return groupDTO;
	}

	public void updateGroup(GroupDTO groupDTO)
	{
		Group group = Group.get(Group.class, groupDTO.getId());
		// 设置要更新的值
		try
		{
			BeanUtils.copyProperties(group, groupDTO);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void removeGroup(Long id)
	{
		this.removeGroups(new Long[]
		{ id });
	}

	public void removeGroups(Long[] ids)
	{
		for (int i = 0; i < ids.length; i++)
		{
			Group group = Group.load(Group.class, ids[i]);
			group.remove();
		}
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<GroupDTO> findAllGroup()
	{
		List<GroupDTO> list = new ArrayList<GroupDTO>();
		List<Group> all = Group.findAll(Group.class);
		for (Group group : all)
		{
			GroupDTO groupDTO = new GroupDTO();
			// 将domain转成VO
			try
			{
				BeanUtils.copyProperties(groupDTO, group);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			list.add(groupDTO);
		}
		return list;
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Page<GroupDTO> pageQueryGroup(GroupDTO queryVo, int currentPage, int pageSize)
	{
		List<GroupDTO> result = new ArrayList<GroupDTO>();
		List<Object> conditionVals = new ArrayList<Object>();
		StringBuilder jpql = new StringBuilder("select _group from Group _group   left join _group.creater  where 1=1 ");

		if (queryVo.getName() != null && !"".equals(queryVo.getName()))
		{
			jpql.append(" and _group.name like ?");
			conditionVals.add(MessageFormat.format("%{0}%", queryVo.getName()));
		}

		if (queryVo.getDescription() != null && !"".equals(queryVo.getDescription()))
		{
			jpql.append(" and _group.description like ?");
			conditionVals.add(MessageFormat.format("%{0}%", queryVo.getDescription()));
		}

		if (queryVo.getType() != null && !"".equals(queryVo.getType()))
		{
			jpql.append(" and _group.type like ?");
			conditionVals.add(MessageFormat.format("%{0}%", queryVo.getType()));
		}
		Page<Group> pages = getQueryChannelService().createJpqlQuery(jpql.toString()).setParameters(conditionVals).setPage(currentPage, pageSize)
				.pagedList();
		for (Group group : pages.getData())
		{
			GroupDTO groupDTO = new GroupDTO();

			// 将domain转成VO
			try
			{
				BeanUtils.copyProperties(groupDTO, group);
			} catch (Exception e)
			{
				e.printStackTrace();
			}

			result.add(groupDTO);
		}
		return new Page<GroupDTO>(pages.getPageIndex(), pages.getResultCount(), pageSize, result);
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public UserDTO findCreaterByGroup(Long id)
	{
		String jpql = "select e from Group o right join o.creater e where o.id=?";
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
