package com.app.DTO;

import java.time.LocalDate;
import java.util.Date;

public class PaymentDTO {
    private Long id;
    private Long userId;
    private Double amount;
    private LocalDate paymentDate;

//    public PaymentDTO(Long id2, double amount2, LocalDate paymentDate2, String userName) {
//		// TODO Auto-generated constructor stub
//	}
    
    

	// Getters and Setters
    public Long getId() {
        return id;
    }

    public PaymentDTO() {
	super();
	this.id = id;
	this.userId = userId;
	this.amount = amount;
	this.paymentDate = paymentDate;
}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public LocalDate getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}

	public void setId(Long id) {
		this.id = id;
	}


}