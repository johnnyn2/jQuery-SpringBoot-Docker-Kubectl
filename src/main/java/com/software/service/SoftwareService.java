package com.software.service;

import com.software.model.*;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public interface SoftwareService {
	public Software getSoftware(String name);
	public ArrayList<Software> getAll();
	public String test();
}
