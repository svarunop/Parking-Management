package com.app.Service;

import java.util.Date;
import java.util.List;

import com.app.DTO.PaymentDTO;
import com.app.DTO.PaymentfetchDTO;

public interface PaymentService {

	PaymentDTO makePayment(Long userId, PaymentDTO paymentDTO);

	List<PaymentDTO> getPaymentsByDate(PaymentfetchDTO paymentfetchDTO);

}
