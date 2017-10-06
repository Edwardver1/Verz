package com.verz.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;





@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@Table(name="engine")
public class Engine {
	
	
	@Id
	@GeneratedValue(generator="myGenerator")  
    @GenericGenerator(name="myGenerator", strategy="foreign", parameters=@Parameter(value="car", name = "property"))
	private Long id;
	@Column(nullable = false)
	private double power;
	@Column( nullable = false)
	private double displacement;

	private String fuel;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="car_id")
	private Car car;
	
	public Engine(){
		
	}
	
	public Engine (double power, double engineDisplacement , String typeFuel){
		this.power = power;
		this.displacement = engineDisplacement;
		this.fuel = typeFuel;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public double getPower() {
		return power;
	}


	public void setPower(double power) {
		this.power = power;
	}


	

	public double getDisplacement() {
		return displacement;
	}

	public void setDisplacement(double displacement) {
		this.displacement = displacement;
	}

	public String getFuel() {
		return fuel;
	}

	public void setFuel(String fuel) {
		this.fuel = fuel;
	}

	public Car getCar() {
		return car;
	}


	public void setCar(Car car) {
		this.car = car;
	}
	
	

}
