package com.app.DTO;

import java.time.Duration;
import java.time.LocalTime;

public class BookingSlotDTO {
    private Long BookingId;
    private Long userId;          // ID of the user making the booking
    private Long slotId;          // ID of the slot being booked
    private LocalTime startTime;  // Start time of the booking
    private LocalTime endTime;    // End time of the booking
   // private double pricePerHour;  // Price per hour for the slot
    private double totalPrice;     // Total price for the booking

    // Getters and Setters
    public Long getSlotId() {
        return slotId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setSlotId(Long slotId) {
        this.slotId = slotId;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime localTime) {
        this.endTime = localTime;
    }

//    public double getPricePerHour() {
//        return pricePerHour;
//    }
//
//    public void setPricePerHour(double pricePerHour) {
//        this.pricePerHour = pricePerHour;
//    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

	public Long getBookingId() {
		return BookingId;
	}

//	public void setBookingId(Long bookingId) {
//		BookingId = bookingId;
//	}

	public void setBookingId(Long bookingId2) {
		// TODO Auto-generated method stub
		this.BookingId = bookingId2;
		
	}

    // Method to calculate total price
//    public double calculateTotalPrice() {
//        if (startTime != null && endTime != null && endTime.isAfter(startTime)) {
//            long hours = Duration.between(startTime, endTime).toHours();
//            return hours * pricePerHour;
//        }
//        return 0.0; // Return 0 if the times are invalid
//    }
}