package com.app.Service;

import java.util.List;

import com.app.DTO.BookingDTO;
import com.app.DTO.BookingResponseDTO;
import com.app.DTO.BookingSlotDTO;

public interface BookingService {

	//BookingSlotDTO bookSlot(BookingSlotDTO bookingSlotDTO);

	List<BookingSlotDTO> getBookingsByUserId(Long userId);

	BookingDTO bookSlot(BookingDTO bookingDTO);

	List<BookingResponseDTO> getAllBookings();
}