package com.software.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.software.model.Software;
import com.software.service.SoftwareService;

@Repository
@Transactional
public class SoftwareRepository implements SoftwareService {
	@Autowired
	private SessionFactory factory;

	@Override
	public Software get(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Software> get() {
		// TODO Auto-generated method stub
		return getSession().createCriteria(Software.class).list();
	}

	@Override
	public void save(Software software) {
		// TODO Auto-generated method stub
		getSession().save(software);
	}

	@Override
	public void saveAll(List<Software> softwares) {
		// TODO Auto-generated method stub

	}

	@Override
	public String delete(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Software update(Software software) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String test() {
		// TODO Auto-generated method stub
		return "API is serving...";
	}

	private Session getSession() {
		Session session = factory.getCurrentSession();
		if (session == null) {
			session = factory.openSession();
		}
		return session;
	}
}
