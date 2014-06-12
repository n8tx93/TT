package com.hyc.T1.application.impl.domain;

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

import com.hyc.T1.application.domain.UserGroupApplication;
import com.hyc.T1.application.dto.GroupDTO;
import com.hyc.T1.application.dto.UserDTO;
import com.hyc.T1.application.dto.UserGroupDTO;
import com.hyc.T1.core.domain.Group;
import com.hyc.T1.core.domain.UserGroup;

@Named
@Transactional
public class UserGroupApplicationImpl implements UserGroupApplication
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
	public UserGroupDTO getUserGroup(Long id)
	{
		UserGroup userGroup = UserGroup.load(UserGroup.class, id);
		UserGroupDTO userGroupDTO = new UserGroupDTO();
		// 将domain转成VO
		try
		{
			BeanUtils.copyProperties(userGroupDTO, userGroup);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		userGroupDTO.setId((java.lang.Long) userGroup.getId());
		return userGroupDTO;
	}

	public UserGroupDTO saveUserGroup(UserGroupDTO userGroupDTO)
	{
		UserGroup userGroup = new UserGroup();
		try
		{
			BeanUtils.copyProperties(userGroup, userGroupDTO);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		userGroup.save();
		userGroupDTO.setId((java.lang.Long) userGroup.getId());
		return userGroupDTO;
	}

	public void updateUserGroup(UserGroupDTO userGroupDTO)
	{
		UserGroup userGroup = UserGroup.get(UserGroup.class, userGroupDTO.getId());
		// 设置要更新的值
		try
		{
			BeanUtils.copyProperties(userGroup, userGroupDTO);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void removeUserGroup(Long id)
	{
		this.removeUserGroups(new Long[]
		{ id });
	}

	public void removeUserGroups(Long[] ids)
	{
		for (int i = 0; i < ids.length; i++)
		{
			UserGroup userGroup = UserGroup.load(UserGroup.class, ids[i]);
			userGroup.remove();
		}
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<UserGroupDTO> findAllUserGroup()
	{
		List<UserGroupDTO> list = new ArrayList<UserGroupDTO>();
		List<UserGroup> all = UserGroup.findAll(UserGroup.class);
		for (UserGroup userGroup : all)
		{
			UserGroupDTO userGroupDTO = new UserGroupDTO();
			// 将domain转成VO
			try
			{
				BeanUtils.copyProperties(userGroupDTO, userGroup);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			list.add(userGroupDTO);
		}
		return list;
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Page<UserGroupDTO> pageQueryUserGroup(UserGroupDTO queryVo, int currentPage, int pageSize)
	{
		List<UserGroupDTO> result = new ArrayList<UserGroupDTO>();
		List<Object> conditionVals = new ArrayList<Object>();
		StringBuilder jpql = new StringBuilder(
				"select _userGroup from UserGroup _userGroup   left join _userGroup.user  left join _userGroup.group  where 1=1 ");

		Page<UserGroup> pages = getQueryChannelService().createJpqlQuery(jpql.toString()).setParameters(conditionVals).setPage(currentPage, pageSize)
				.pagedList();
		for (UserGroup userGroup : pages.getData())
		{
			UserGroupDTO userGroupDTO = new UserGroupDTO();

			// 将domain转成VO
			try
			{
				BeanUtils.copyProperties(userGroupDTO, userGroup);
			} catch (Exception e)
			{
				e.printStackTrace();
			}

			result.add(userGroupDTO);
		}
		return new Page<UserGroupDTO>(pages.getPageIndex(), pages.getResultCount(), pageSize, result);
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public UserDTO findUserByUserGroup(Long id)
	{
		String jpql = "select e from UserGroup o right join o.user e where o.id=?";
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

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public GroupDTO findGroupByUserGroup(Long id)
	{
		String jpql = "select e from UserGroup o right join o.group e where o.id=?";
		Group result = (Group) getQueryChannelService().createJpqlQuery(jpql).setParameters(new Object[]
		{ id }).singleResult();
		GroupDTO dto = new GroupDTO();
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
