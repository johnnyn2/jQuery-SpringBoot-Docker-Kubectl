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
		return getSession().get(Software.class, name);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Software> get() {
		return getSession().createCriteria(Software.class).list();
	}

	@Override
	public Software save(Software software) {
        getSession().save(software);
        return software;
	}

	@Override
	public List<Software> saveAll(List<Software> softwares) {
        for(Software s : softwares) {
            getSession().save(s);
        }
        return softwares;
	}

	@Override
	public Software delete(String name) {
        Software s = getSession().get(Software.class, name);
        getSession().delete(s);
		return s;
	}

	@Override
	public Software update(Software software) {
        Software s = getSession().get(Software.class, software.getName());
        s.setName(software.getName());
        s.setVersion(software.getVersion());
        s.setDescription(software.getDescription());
        getSession().update(s);
		return s;
	}

	@Override
	public String test() {
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
