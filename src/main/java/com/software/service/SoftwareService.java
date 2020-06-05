package com.software.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.software.model.Software;

@Service
public interface SoftwareService {
	public Software get(String name);

	public List<Software> get();

	public Software save(Software software);

	public List<Software> saveAll(List<Software> softwares);

	public Software delete(String name);

	public Software update(Software software);

	public String test();
}
