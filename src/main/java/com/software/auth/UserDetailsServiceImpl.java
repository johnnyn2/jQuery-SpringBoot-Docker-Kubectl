package com.software.auth;

import java.util.Set;

import com.software.model.Role;
import com.software.model.User;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private SessionFactory factory;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findUserbyUsername(username);

        UserBuilder builder = null;
        if (user != null) {
            builder = org.springframework.security.core.userdetails.User.withUsername(username);
            builder.password(new BCryptPasswordEncoder().encode(user.getPassword()));
            String[] roles = new String[user.getRoles().size()];
            int i = 0;
            for(Role r:user.getRoles()) {
                roles[i] = r.getName();
                i++;
            }
            builder.roles(roles);
        } else {
            throw new UsernameNotFoundException("User not found");
        }
        return builder.build();
    }

    @Transactional
    private User findUserbyUsername(String username) {
        return getSession().get(User.class, username);
    }

    private Session getSession() {
		Session session = factory.getCurrentSession();
		if (session == null) {
			session = factory.openSession();
		}
		return session;
	}
    
}