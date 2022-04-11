package com.nhatdang2604.main;

import com.nhatdang2604.utility.HibernateUtil;

public class Main {

	public static void main(String[] args) {
		HibernateUtil.INSTANCE.getSessionFactory();
	}

}
