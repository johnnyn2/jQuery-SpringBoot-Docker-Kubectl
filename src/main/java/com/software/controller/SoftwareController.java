package com.software.controller;

import com.software.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;
import com.software.model.*;
import java.util.ArrayList;

@RestController
@RequestMapping("/software")
public class SoftwareController {
	@Autowired
	SoftwareService softwareService;
	
	@GetMapping(value = "/{name}")
	public Software getSoftware(@PathVariable String name) {
		return softwareService.getSoftware(name);
	}
	
	@GetMapping(value = "/")
	public ArrayList<Software> getAll() {
		return softwareService.getAll();
	}
	
	@GetMapping(value = "/test")
	public String isServerOK() {
		return softwareService.test();
	}
}
