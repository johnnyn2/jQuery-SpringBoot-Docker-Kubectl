package com.software.repository;

import com.software.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SoftwareRepository extends JpaRepository<Software, String> {

}
