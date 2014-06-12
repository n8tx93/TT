
package com.hyc.T1.application.domain;

import java.util.List;
import org.dayatang.querychannel.Page;
import com.hyc.T1.application.dto.*;

public interface UserGroupApplication {

	public UserGroupDTO getUserGroup(Long id);
	
	public UserGroupDTO saveUserGroup(UserGroupDTO userGroup);
	
	public void updateUserGroup(UserGroupDTO userGroup);
	
	public void removeUserGroup(Long id);
	
	public void removeUserGroups(Long[] ids);
	
	public List<UserGroupDTO> findAllUserGroup();
	
	public Page<UserGroupDTO> pageQueryUserGroup(UserGroupDTO userGroup, int currentPage, int pageSize);
	
	public UserDTO findUserByUserGroup(Long id);


		public GroupDTO findGroupByUserGroup(Long id);


	
}

