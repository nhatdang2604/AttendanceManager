package com.nhatdang2604.dao;

import com.nhatdang2604.model.entity.Ministry;
import com.nhatdang2604.model.entity.User;

public interface IMinistryDAO {

	public Ministry getMinistryFromUser(User user);
}
