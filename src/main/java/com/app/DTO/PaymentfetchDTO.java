package com.app.DTO;

import java.time.LocalDate;

public class PaymentfetchDTO {

	private LocalDate startingDate;
	
	private LocalDate endDate;
	
	

	public PaymentfetchDTO(LocalDate startingDate, LocalDate endDate) {
		super();
		this.startingDate = startingDate;
		this.endDate = endDate;
	}

	public LocalDate getStartingDate() {
		return startingDate;
	}

	public void setStartingDate(LocalDate startingDate) {
		this.startingDate = startingDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	
	
}