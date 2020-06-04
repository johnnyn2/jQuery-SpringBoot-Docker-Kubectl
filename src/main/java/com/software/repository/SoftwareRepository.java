package com.software.repository;

import com.software.model.*;
import org.springframework.stereotype.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Component;

public interface SoftwareRepository extends JpaRepository<Software, String> {

}
