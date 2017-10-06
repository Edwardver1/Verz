package com.verz.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/*** Composition class  ***/

@Embeddable
public class Feature{
	
	@Column(nullable = false)
	String name;
	
	public Feature(){
		
	}
	
	public Feature(String name){
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name == null ){
			throw new IllegalArgumentException("Feature name can't be null");
		}else{
			this.name = name;
		}
		
	}

	@Override
	public String toString() {
		return  name ;
	}
	
	
	
}
