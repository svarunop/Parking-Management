package com.app.Controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.DTO.PaymentDTO;
import com.app.DTO.PaymentfetchDTO;
import com.app.Entity.Payment;
import com.app.Service.PaymentService;

@RestController
@RequestMapping("/payments")
@CrossOrigin("http://localhost:3000")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	// Method to make a payment
	@PostMapping("/user/makepayment/{userId}")
	public ResponseEntity<PaymentDTO> makePayment(@PathVariable Long userId,@RequestBody PaymentDTO paymentDTO) {

		// Validate the payment details (optional)
		if (paymentDTO.getAmount() <= 0) {
			return ResponseEntity.badRequest().body(null); // or throw an exception
		}

		// Call the service to handle the payment logic
		PaymentDTO result = paymentService.makePayment(userId, paymentDTO);

		// Return the result
		return ResponseEntity.ok(result);
	}

	// Method to get all payments by date
	  @PostMapping("/fetch")
	    public ResponseEntity<List<PaymentDTO>> getPaymentsByDate(@RequestBody PaymentfetchDTO paymentfetchDTO) {
	        // Call the service to retrieve payments by date range
	        List<PaymentDTO> payments = paymentService.getPaymentsByDate(paymentfetchDTO);
	        
	        // Return the list of payments
	        return ResponseEntity.ok(payments);
	    }
	  
//	  @GetMapping("/admin/date/{startDate}/{endDate}")
//	    public ResponseEntity<List<PaymentDTO>> getPaymentsByDate(
//	            @PathVariable String startDate,
//	            @PathVariable String endDate) {
//	        LocalDate startingDate = LocalDate.parse(startDate); // Parse the start date
//	        LocalDate endingDate = LocalDate.parse(endDate); // Parse the end date
//
//	        // Call the service to retrieve payments by date range
//	        List<PaymentDTO> payments = paymentService.getPaymentsByDate(new PaymentfetchDTO(startingDate, endingDate));
//	        
//	        // Return the list of payments
//	        return ResponseEntity.ok(payments);
//	    }
	  @PostMapping("/admin/date/{startDate}/{endDate}")
	    public ResponseEntity<List<PaymentDTO>> getPaymentsByDate(
	            @PathVariable String startDate,
	            @PathVariable String endDate) {
	        try {
	            // Parse the dates
	            LocalDate startingDate = LocalDate.parse(startDate);
	            LocalDate endingDate = LocalDate.parse(endDate);

	            // Call the service to retrieve payments
	            List<PaymentDTO> payments = paymentService.getPaymentsByDate(new PaymentfetchDTO(startingDate, endingDate));

	            return ResponseEntity.ok(payments);
	        } catch (DateTimeParseException e) {
	            throw new IllegalArgumentException("Invalid date format. Please use 'yyyy-MM-dd'.");
	        }
	    }




//	private Date parseDate(String dateString) {
//	    // Define the date format you expect
//	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//
//	    try {
//	        // Parse the string to LocalDate
//	        LocalDate localDate = LocalDate.parse(dateString, formatter);
//	        
//	        // Convert LocalDate to java.util.Date
//	        return java.sql.Date.valueOf(localDate);
//	    } catch (DateTimeParseException e) {
//	        throw new IllegalArgumentException("Invalid date format. Please use yyyy-MM-dd.");
//	    }
//	}

	
}