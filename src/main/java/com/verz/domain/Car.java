package com.verz.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table(name="car")
public class Car  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (nullable = false, updatable = false)
	private Long id;
	
	@Column (nullable = false)
	private String make;
	@Column (nullable = false)
	private String model; 
	@Column (nullable = false)
	private LocalDate year; 
	private String color;
	@Column (nullable = false)
	private double price;
	private boolean active=true;

	@Transient
	private MultipartFile carImage;
	
	@OneToMany(mappedBy = "car")
	@JsonIgnore
	private List<CarToCartItem> carToCartItemList;
	
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;
	
	@ElementCollection
	@CollectionTable(name="Car_Features",joinColumns=@JoinColumn(name="car_id"))
	@OrderColumn(name="feature_id")
    private List<Feature> features = new ArrayList<>() ;
	
	@OneToOne(mappedBy = "car",cascade=CascadeType.ALL)
	private Engine engine;
	
	public Car (){
		
	}

	public Long getId() {
		return id;
	}


	public void setId(Long carId) {
		this.id = carId;
	}


	public String getMake() {
		return make;
	}
    
	public void setMake(String make) {
		if (make == null ){ // cause required attribute
			throw new IllegalArgumentException("Producer can't be null");
		}
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		if (model == null ){
			throw new IllegalArgumentException("Model can't be null");
		}
		this.model = model;
	}

	public LocalDate getYear() {
		return year;
	}

	public void setYear(LocalDate constractionYear) {
		if (constractionYear == null ){
			throw new IllegalArgumentException("Year can't be null");
		}
		this.year = constractionYear;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		if (color == null ){
			throw new IllegalArgumentException("Color can't be null");
		}
		this.color = color;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public MultipartFile getCarImage() {
		return carImage;
	}


	public void setCarImage(MultipartFile carImage) {
		this.carImage = carImage;
	}


	public List<CarToCartItem> getCarToCartItemList() {
		return carToCartItemList;
	}


	public void setCarToCartItemList(List<CarToCartItem> carToCartItemList) {
		this.carToCartItemList = carToCartItemList;
	}
	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	

	public Category getCategory() {
		return category;
	}


	public void setCategory(Category category) {
		this.category = category;
	}
	
	


	public Engine getEngine() {
		return engine;
	}

	public void setEngine(Engine engine) {
		this.engine = engine;
	}

	public List<Feature> getFeatureList() {
		return features;
	}

	public String getFeatures() {
		
		List<Feature> list = getFeatureList();
		
		String features = Arrays.toString(list.toArray()).replace("[", "").replace("]", "");
		
		return features;
	}
	
	

}
