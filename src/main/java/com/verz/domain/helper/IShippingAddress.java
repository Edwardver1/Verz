package com.verz.domain.helper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public interface IShippingAddress {
	
	public void setDeliverDate(Date date);
	public Date getDeliverDate();
	public void setNote(String note);
	public String getNote();

}
