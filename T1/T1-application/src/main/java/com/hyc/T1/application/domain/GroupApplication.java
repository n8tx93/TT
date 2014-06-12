
package com.hyc.T1.application.domain;

import java.util.List;
import org.dayatang.querychannel.Page;
import com.hyc.T1.application.dto.*;

public interface GroupApplication {

	public GroupDTO getGroup(Long id);
	
	public GroupDTO saveGroup(GroupDTO group);
	
	public void updateGroup(GroupDTO group);
	
	public void removeGroup(Long id);
	
	public void removeGroups(Long[] ids);
	
	public List<GroupDTO> findAllGroup();
	
	public Page<GroupDTO> pageQueryGroup(GroupDTO group, int currentPage, int pageSize);
	
	public UserDTO findCreaterByGroup(Long id);


	
}

