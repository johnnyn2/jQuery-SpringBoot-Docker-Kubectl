package com.software.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "software")
public class Software {
	@Id
	@Column
	private String name;
	@Column
	private double version;
	@Column
	private String description;
}
