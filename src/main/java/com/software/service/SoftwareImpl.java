package com.software.service;

import com.software.model.*;

import java.util.Arrays;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class SoftwareImpl implements SoftwareService{
	private ArrayList<Software> softwares;
	public SoftwareImpl() {
		Software eclipse = new Software("Eclipse", 5.0, "This is not a very good coding tool..");
		Software vscode = new Software("Visual Studio Code", 10.3, "This is the best code editor I have ever used. It supports great plugins for multiple languages, syntax highlight and fast. I used it to code React and Angular for a year. It's awesome...");
		Software netbeans = new Software("NetBeans", 7.4, "It is also a good tool for coding Java. But I didn't touch it for a 3 to 4 years so I really don't know how to use its current version.");
		Software nodePadPlusPlus = new Software("NotePad++", 1.3, "This is the first editor I used for coding. Although it sucks, it is full of memory and it is always the fastest editor.");
		Software postman = new Software("Postman", 3.5, "This is a good tool for testing API. I used it when I was working in App Tech. I have not touched it for 1 year because I focused on frontend app production in the past year.");
		Software xampp = new Software("XAMPP", 1.2, "This is the first server I used for development. It provides a lot of built-in utilities, such as phpmyadmin. It is a good option for those who don't want to configure the server.");
		softwares = new ArrayList<Software>(Arrays.asList(eclipse, vscode, netbeans, nodePadPlusPlus, postman, xampp));
	}
	@Override
	public Software getSoftware(String name) {
		Software temp = new Software("NULL", 0.0, "NULL");
		for(Software s:softwares) {
			if (s.getName().equalsIgnoreCase(name)) {
				temp = new Software(s.getName(), s.getVersion(), s.getDescription());
				System.out.printf("Software name: %s ; name: %s", s.getName(), name);
			}
		}
		return temp;
	}
	
	@Override
	public ArrayList<Software> getAll() {
		return softwares;
	}
	
	@Override
	public String test() {
		return "API is serving...";
	}
	
}
