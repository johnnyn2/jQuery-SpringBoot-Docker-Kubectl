package com.software.controller;

import java.util.List;

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

	@GetMapping("/getSoftware")
	public Software getSoftware(@RequestParam(name = "name") String name) {
		return softwareRepository.get(name);
	}

	@GetMapping("/getAll")
	public List<Software> getAll() {
		return softwareRepository.get();
	}

	@PostMapping("/addSoftware")
	public String addSoftware() {
		Software s = new Software();
		s.setName("sdsd");
		s.setVersion(1.3);
		s.setDescription("sddsad");
		softwareRepository.save(s);
		return "success";
	}

	@PostMapping("/addSoftwares")
	public void addSoftwares(@RequestBody List<Software> softwares) {

	}

	@PutMapping("/update")
	public Software updateSoftware(@RequestBody Software software) {
		return softwareRepository.update(software);
	}

	@DeleteMapping("/delete")
	public String deleteProduct(@RequestParam(name = "name") String name) {
		return softwareRepository.delete(name);
	}

	@GetMapping(value = "/")
	public String isServerOK() {
		return softwareRepository.test();
	}
}
