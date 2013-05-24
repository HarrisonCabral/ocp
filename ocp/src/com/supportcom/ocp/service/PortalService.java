package com.supportcom.ocp.service;

import java.util.List;

import com.supportcomm.ocp.entity.Portal;
import com.supportcomm.ocp.entity.PortalLoginVO;

public interface PortalService {
	
	public void save(Portal portal);
	public void delete(Portal portal);
	public Portal findById(Portal portal);
	public Portal findByPortalNumber(Portal portal);
	public void update(Portal portal);
	public void setSelectedAccessRights(List<Portal> selectedAccessRights);
	public List<PortalLoginVO> listAllByLogin(Long loginId);

}
