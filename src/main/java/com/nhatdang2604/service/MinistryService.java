package com.nhatdang2604.service;

import com.nhatdang2604.dao.IMinistryDAO;
import com.nhatdang2604.dao.MinistryDAO;
import com.nhatdang2604.model.entity.Ministry;
import com.nhatdang2604.model.entity.User;

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
