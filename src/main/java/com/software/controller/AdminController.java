package com.software.controller;

import java.util.Map;

import com.software.model.User;
import com.software.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/api/admin/hello")
	public Map<String, String> adminSayHello() {
		Map<String, String> result = Map.of("message", "admin say hello");
		return result;
	}

	@GetMapping("/api/user/hello")
	public Map<String, String> userSayHello() {
		Map<String, String> result = Map.of("message", "user say hello");
		return result;
	}

    @PostMapping("api/admin/add")
    public User addUserByAdmin(@RequestBody User user) {
        String pwd = user.getPassword();
        String encryptedPwd = passwordEncoder.encode(pwd);
        user.setPassword(encryptedPwd);
        userRepository.save(user);
        return user;
    }

    @GetMapping("addPeople")
    public ModelAndView addPeople() {
        return new ModelAndView("addPeople");
    }
    
}