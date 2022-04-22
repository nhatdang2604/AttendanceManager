package com.nhatdang2604.service;

import com.nhatdang2604.dao.MinistryDAO;
import com.nhatdang2604.dao.i.IMinistryDAO;
import com.nhatdang2604.model.entity.Ministry;
import com.nhatdang2604.model.entity.User;
import com.nhatdang2604.service.i.IMinistryService;

public enum MinistryService implements IMinistryService {

	INSTANCE;
	
	private IMinistryDAO ministryDAO;
	
	private MinistryService() {
		ministryDAO = MinistryDAO.INSTANCE;
	}

	public Ministry loginAsMinistry(User user) {
		return ministryDAO.getMinistryFromUser(user);
	}
	
}
