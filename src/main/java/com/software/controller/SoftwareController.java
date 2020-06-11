package com.software.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.software.model.Software;
import com.software.repository.SoftwareRepository;

@RestController
public class SoftwareController {
	@Autowired
	private SoftwareRepository softwareRepository;

	@GetMapping("/getSoftware")
	public ModelAndView getSoftware(@RequestParam(name = "id") int id) {
		// return softwareRepository.get(name);
		Software software = softwareRepository.get(id);
		Map<String, Object> params = new HashMap<>();
		params.put("software", software);
		return new ModelAndView("getSoftware", params);
	}

	@GetMapping("/getAll")
	public ModelAndView getAll() {
		List<Software> softwares = softwareRepository.get();
		Map<String, Object> params = new HashMap<>();
		params.put("softwares", softwares);
		return new ModelAndView("getAll", params);
	}

	@PostMapping("/addSoftware")
	public Software addSoftware(@RequestBody Software software) {
		return softwareRepository.save(software);
	}

	@PostMapping("/addSoftwares")
	public List<Software> addSoftwares(@RequestBody List<Software> softwares) {
		return softwareRepository.saveAll(softwares);
	}

	@PutMapping("/update")
	public Software updateSoftware(@RequestBody Software software) {
		return softwareRepository.update(software);
	}

	@DeleteMapping("/delete")
	public Software deleteSoftware(@RequestParam(name = "id") int id) {
		return softwareRepository.delete(id);
	}

	@GetMapping("/")
	public ModelAndView index(Software software) {
		String role = hasRole("ROLE_ADMIN") ? "ADMIN" : "USER";
		Map<String, String> params = new HashMap<>();
		params.put("role", role);
		Map<String, Object> out = new HashMap<>();
		out.put("role", params);
		return new ModelAndView("index", out);
	}

	protected boolean hasRole(String role) {
        // get security context from thread local
        SecurityContext context = SecurityContextHolder.getContext();
        if (context == null)
            return false;

        Authentication authentication = context.getAuthentication();
        if (authentication == null)
            return false;

        for (GrantedAuthority auth : authentication.getAuthorities()) {
            if (role.equals(auth.getAuthority()))
                return true;
        }

        return false;
    }
}
