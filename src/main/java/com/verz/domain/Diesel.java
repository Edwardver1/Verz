package com.verz.domain;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="diesel")
@PrimaryKeyJoinColumn(name="engine_id")
public class Diesel extends Engine {
	
	private String recommendDiesel;
	
	public Diesel(){
		
	}
	
	public Diesel ( String recDiesel){
		this.recommendDiesel = recDiesel;
	}

	public String getRecommendDiesel() {
		return recommendDiesel;
	}

	public void setRecommendDiesel(String recommendDiesel) {
		this.recommendDiesel = recommendDiesel;
	}
	
	
	
}
