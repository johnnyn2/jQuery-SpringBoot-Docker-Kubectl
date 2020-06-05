package com.software.service;

import com.software.model.*;
import com.software.repository.SoftwareRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.*;
import java.util.List;

@Service
public class SoftwareImpl implements SoftwareService{
	@Autowired
	private SoftwareRepository repository;
	
	public SoftwareImpl(SoftwareRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public Software get(String name) {
		return repository.findById(name).orElse(null);
	}

	@Override
	public List<Software> get() {
		return repository.findAll();
	}

	@Override
	public Software save(Software software) {
		return repository.save(software);
	}
	
	@Override
	public List<Software> saveAll(List<Software> softwares) {
		return repository.saveAll(softwares);
	}

	@Override
	public String delete(String name) {
		repository.deleteById(name);
		return name + "software removed...";
	}

	@Override
	public Software update(Software software) {
		Software existingSoftware = repository.findById(software.getName()).orElse(null);
		existingSoftware.setName(software.getName());
		existingSoftware.setVersion(software.getVersion());
		existingSoftware.setDescription(software.getDescription());
		return repository.save(existingSoftware);
	}

	@Override
	public String test() {
		return "API is serving...";
	}
	
}
