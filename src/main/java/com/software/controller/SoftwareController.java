package com.software.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.software.model.Software;
import com.software.repository.SoftwareRepository;

@RestController
public class SoftwareController {
	@Autowired
	private SoftwareRepository softwareRepository;

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

	@GetMapping("/getSoftware")
	public Software getSoftware(@RequestParam(name = "name") String name) {
		return softwareRepository.get(name);
	}

	@GetMapping("/getAll")
	public List<Software> getAll() {
		return softwareRepository.get();
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
	public Software deleteSoftware(@RequestParam(name = "name") String name) {
		return softwareRepository.delete(name);
	}

	@GetMapping(value = "/")
	public String isServerOK() {
		return softwareRepository.test();
	}
}
