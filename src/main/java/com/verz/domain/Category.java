package com.verz.domain;

import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.Table;





@Entity
@Table(name = "category")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false , updatable = false)
	private long id;
	
	@Column(nullable = false)
	private String name;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="category_id")
	@MapKey(name="id")
	private Map<Long,Car> cars;
	
	public Category(){
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<Long, Car> getCars() {
		return cars;
	}

	public void setCars(Map<Long, Car> cars) {
		this.cars = cars;
	}

	
	
	
	
	
	
}
