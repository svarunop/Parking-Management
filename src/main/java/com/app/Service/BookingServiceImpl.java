package com.app.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.DTO.BookingDTO;
import com.app.DTO.BookingResponseDTO;
import com.app.DTO.BookingSlotDTO;
import com.app.Entity.Booking;
import com.app.Entity.ParkingSlot;
import com.app.Entity.User; // Assuming you have a User entity
import com.app.Repository.BookingRepository;
import com.app.Repository.ParkingSlotRepository;
import com.app.Repository.UserRepository; // Assuming you have a UserRepository

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository; // Repository for Booking
    @Autowired
    private ParkingSlotRepository parkingSlotRepository; // Repository for ParkingSlot
    @Autowired
    private UserRepository userRepository; // Repository for User

    @Override
    public BookingDTO bookSlot(BookingDTO bookingDTO) {
        // Fetch the parking slot by ID
        Optional<ParkingSlot> optionalSlot = parkingSlotRepository.findById(bookingDTO.getSlotId());
        // Fetch the user by ID
        Optional<User> optionalUser  = userRepository.findById(bookingDTO.getUserId());

        // Check if the slot and user exist
        if (optionalSlot.isPresent() && optionalUser.isPresent()) {
            ParkingSlot slot = optionalSlot.get();
            User user = optionalUser.get();

            // Check if the slot is available
            if (slot.isAvailable()) {
                slot.setAvailable(false); // Mark the slot as booked
                parkingSlotRepository.save(slot); // Save the updated slot

                // Create a new Booking entity
                Booking booking = new Booking();
                booking.setSlotDetails(slot); // Set the ParkingSlot object
                booking.setUser(user); // Set the User object
                booking.setBookingId(bookingDTO.getId());
                booking.setStartTime(bookingDTO.getStartTime());
                booking.setEndTime(bookingDTO.getEndTime());
                booking.setTotalPrice(bookingDTO.getTotalPrice()); // Set the current time as booking time
                booking.setBookingdate(LocalDate.now());

                // Save the booking
                bookingRepository.save(booking);

                return bookingDTO; // Return the booking details
            } else {
                throw new RuntimeException("Slot is not available for booking");
            }
        } else {
            throw new RuntimeException("Slot or User not found");
        }
    }

    @Override
    public List<BookingSlotDTO> getBookingsByUserId(Long userId) {
        List<Booking> bookings = bookingRepository.findByUserId(userId); // Fetch bookings by user ID
        return bookings.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    // Helper method to map Booking entity to BookingSlotDTO
    private BookingSlotDTO mapToDTO(Booking booking) {
        BookingSlotDTO bookingSlotDTO = new BookingSlotDTO();
        bookingSlotDTO.setBookingId(booking.getBookingId());
        bookingSlotDTO.setSlotId(booking.getSlotDetails().getSlotId()); // Assuming you have a getId() method in ParkingSlot
        bookingSlotDTO.setUserId(booking.getUser().getId()); // Assuming you have a getId() method in User
        bookingSlotDTO.setStartTime(booking.getStartTime());
        bookingSlotDTO.setEndTime(booking.getEndTime());
        bookingSlotDTO.setTotalPrice(booking.getTotalPrice());
        return bookingSlotDTO;
    }

    @Override
    public List<BookingResponseDTO> getAllBookings() {
        return bookingRepository.findAll().stream()
                .map(this::convertToResponseDTO) // Convert Booking to BookingResponseDTO
                .collect(Collectors.toList());
    }

    private BookingResponseDTO convertToResponseDTO(Booking booking) {
        BookingResponseDTO dto = new BookingResponseDTO();
        dto.setId(booking.getBookingId()); // Use getBookingId() to get the booking ID
        dto.setSlotId(booking.getSlotDetails().getSlotId()); // Access the slot ID through slotDetails
        dto.setStartTime(booking.getStartTime());
        dto.setEndTime(booking.getEndTime());
        dto.setTotalPrice(booking.getTotalPrice());

        // Fetch the user's name based on the user object
        User user = booking.getUser(); // Get the user object from the booking
        if (user != null) {
            dto.setUserName(user.getName()); // Set the user's name in the DTO
        } else {
            dto.setUserName("Unknown User"); // Fallback if user is not found
        }

        return dto;
    }
}
