package com.app.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.DTO.PaymentDTO;
import com.app.DTO.PaymentfetchDTO;
import com.app.Entity.Payment;
import com.app.Entity.User;
import com.app.Repository.PaymentRepository;
import com.app.Repository.UserRepository;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private UserRepository userRepository; // Repository to fetch User

    @Override
    public PaymentDTO makePayment(Long userId, PaymentDTO paymentDTO) {
        // Validate the payment details
        if (paymentDTO.getAmount() <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }

        // Fetch the User entity using the userId
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Create a new Payment entity
        Payment payment = new Payment();
        payment.setUser(user); // Set the User object
        payment.setAmount(paymentDTO.getAmount());
        payment.setPaymentDate(LocalDate.now()); // Set the current date/time

        // Save the payment record to the database
        Payment savedPayment = paymentRepository.save(payment);

        // Convert the saved Payment entity to PaymentDTO
        PaymentDTO result = new PaymentDTO();
        result.setId(savedPayment.getId());
        result.setUserId(savedPayment.getUser().getId()); // Get the user ID from the User object
        result.setAmount(savedPayment.getAmount());
        result.setPaymentDate(savedPayment.getPaymentDate());

        return result; // Return the created payment record
    }

    @Override
    public List<PaymentDTO> getPaymentsByDate(PaymentfetchDTO paymentfetchDTO) {
        LocalDate startingDate = paymentfetchDTO.getStartingDate();
        LocalDate endDate = paymentfetchDTO.getEndDate();

        // Fetch payments from the repository between the two dates
        List<Payment> payments = paymentRepository.findByPaymentDateBetween(startingDate, endDate);

        // Map to DTO using the helper method
        return payments.stream()
                       .map(this::convertToDTO) // Use the helper method to convert each payment
                       .collect(Collectors.toList());
    }

    // Helper method to convert Payment entity to PaymentDTO
    private PaymentDTO convertToDTO(Payment payment) {
        PaymentDTO dto = new PaymentDTO();
        dto.setId(payment.getId());
        dto.setUserId(payment.getUser().getId()); // Get the user ID from the User object
        dto.setAmount(payment.getAmount());
        dto.setPaymentDate(payment.getPaymentDate());
        return dto; // Corrected syntax
    }

}
