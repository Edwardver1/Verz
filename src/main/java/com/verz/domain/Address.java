package com.verz.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.EnumSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.verz.domain.helper.AddressType;
import com.verz.domain.helper.IBillingAddress;
import com.verz.domain.helper.IShippingAddress;



@Entity
public class Address implements IBillingAddress,IShippingAddress {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(nullable=false,updatable = false)
	private Long id;
	
	private EnumSet<AddressType> addressTypes;
	
	@Column(nullable=false)
	private String recipientName;
	@Column(nullable=false)
	private String AddressStreet;
	@Column(nullable=false)
	private String AddressCity;
	@Column(nullable=false)
	private String AddressState;
	@Column(nullable=false)
	private String AddressZipcode;
	
	@DateTimeFormat(pattern = "yyyy MMMM dd - hh:mm")
	private Date deliverDate;
	
	private String note;
	private String email;
	
	@OneToOne(cascade=CascadeType.ALL)
	private Order order;
	
	public Address(){
		
	}
	
	public Address(EnumSet<AddressType> addressType){
		
		setAddressTypes(addressType);
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Order getOrder() {
		return order;
	}


	public void setOrder(Order order) {
		this.order = order;
	}


	public EnumSet<AddressType> getAddressTypes() {
		return addressTypes;
	}

	public void setAddressTypes(EnumSet<AddressType> addressTypes) {
		if(addressTypes != null){
			if(!addressTypes.isEmpty()){
				this.addressTypes = addressTypes;
			}else {
				throw new IllegalArgumentException("Address type list is empty");
			}
		} else {
			throw new IllegalArgumentException("Address type list is null");
		}
	}

	public String getRecipientName() {
		return recipientName;
	}

	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}

	public String getAddressStreet() {
		return AddressStreet;
	}

	public void setAddressStreet(String addressStreet) {
		AddressStreet = addressStreet;
	}

	public String getAddressCity() {
		return AddressCity;
	}

	public void setAddressCity(String addressCity) {
		AddressCity = addressCity;
	}

	public String getAddressState() {
		return AddressState;
	}

	public void setAddressState(String addressState) {
		AddressState = addressState;
	}

	public String getAddressZipcode() {
		return AddressZipcode;
	}

	public void setAddressZipcode(String addressZipcode) {
		AddressZipcode = addressZipcode;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDeliverDate() {
		return deliverDate;
	}

	public void setDeliverDate(Date deliverDate) {
		this.deliverDate = deliverDate;
	}

	




	
	
	

}
