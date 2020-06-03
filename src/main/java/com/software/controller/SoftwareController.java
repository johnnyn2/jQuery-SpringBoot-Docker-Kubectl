package com.software.controller;

import com.software.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;
import com.software.model.*;
import java.util.ArrayList;

@RestController
public class SoftwareController {
	@Autowired
	SoftwareService softwareService;
	
	@GetMapping(value = "/getSoftware")
	public Software getSoftware(@RequestParam(name = "name") String name) {
		return softwareService.getSoftware(name);
	}
	
	@GetMapping(value = "/getAll")
	public ArrayList<Software> getAll() {
		return softwareService.getAll();
	}
	
	@GetMapping(value = "/")
	public String isServerOK() {
		return softwareService.test();
	}
}
