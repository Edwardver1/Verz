package com.verz.domain;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="gas")
@PrimaryKeyJoinColumn(name="engine_id")
public class Gas extends Engine {

	private String ignitionSystem ; 
	private String recommendGas;
	
	public Gas(){
		
	}
	
	
	public Gas(String ignitionSystem , String recGas){
		this.ignitionSystem = ignitionSystem;
		this.recommendGas = recGas;
		
	}


	public String getIgnitionSystem() {
		return ignitionSystem;
	}


	public void setIgnitionSystem(String ignitionSystem) {
		this.ignitionSystem = ignitionSystem;
	}


	public String getRecommendGas() {
		return recommendGas;
	}


	public void setRecommendGas(String recommendGas) {
		this.recommendGas = recommendGas;
	}
	
	
	
	
}
