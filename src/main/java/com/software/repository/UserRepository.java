package com.software.repository;

import javax.transaction.Transactional;

import com.software.model.User;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class UserRepository {
    @Autowired
    private SessionFactory factory;
    
    public User save(User user) {
        getSession().save(user);
        return user;
    }

    private Session getSession() {
		Session session = factory.getCurrentSession();
		if (session == null) {
			session = factory.openSession();
		}
		return session;
	}
}