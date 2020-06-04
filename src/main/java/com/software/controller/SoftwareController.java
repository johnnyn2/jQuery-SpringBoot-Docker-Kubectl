package com.software.controller;

import com.software.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;
import com.software.model.*;
import java.util.List;

@RestController
public class SoftwareController {
	@Autowired
	SoftwareService softwareService;
	
	@GetMapping("/getSoftware")
	public Software getSoftware(@RequestParam(name = "name") String name) {
		return softwareService.get(name);
	}
	
	@GetMapping("/getAll")
	public List<Software> getAll() {
		return softwareService.get();
	}
	
	@PostMapping("/addSoftware")
	public Software addSoftware(@RequestBody Software software) {
		return softwareService.save(software);
	}
	
	@PostMapping("/addSoftwares")
	public List<Software> addSoftwares(@RequestBody List<Software>softwares) {
		return softwareService.saveAll(softwares);
	}
	
	@PutMapping("/update")
	public Software updateSoftware(@RequestBody Software software) {
		return softwareService.update(software);
	}
	
	@DeleteMapping("/delete")
	public String deleteProduct(@RequestParam(name = "name") String name) {
		return softwareService.delete(name);
	}
	
	
	@GetMapping(value = "/")
	public String isServerOK() {
		return softwareService.test();
	}
}
