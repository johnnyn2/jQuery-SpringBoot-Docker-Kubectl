package com.software.repository;

import javax.transaction.Transactional;

import com.software.model.Role;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class RoleRepository {
    @Autowired
    private SessionFactory factory;
    
    public Role save(Role role) {
        getSession().save(role);
        return role;
    }

    private Session getSession() {
		Session session = factory.getCurrentSession();
		if (session == null) {
			session = factory.openSession();
		}
		return session;
	}
}