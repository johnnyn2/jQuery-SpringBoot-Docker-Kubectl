package com.software.repository;

import java.util.List;

import javax.transaction.Transactional;

import com.software.model.User;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
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

    @SuppressWarnings("unchecked")
    public User findUserbyUsername(String username) {
        Criteria cr = getSession().createCriteria(User.class);
        cr.add(Restrictions.eq("username", username));
        List<User> results = cr.list();
        if (results.size() > 0) {
            return results.get(0);
        }
        return null;
    }

    private Session getSession() {
		Session session = factory.getCurrentSession();
		if (session == null) {
			session = factory.openSession();
		}
		return session;
	}
}