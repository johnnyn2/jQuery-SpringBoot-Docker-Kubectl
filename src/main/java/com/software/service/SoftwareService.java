package com.software.service;

import com.software.model.*;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.*;
import java.util.List;
import com.software.repository.*;

@Service
public interface SoftwareService {
	public Software get(String name);
	public List<Software> get();
	public Software save(Software software);
	public List<Software> saveAll(List<Software> softwares);
	public String delete(String name);
	public Software update(Software software);
	public String test();
}
