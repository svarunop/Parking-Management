package com.app.Controller;

import com.app.DTO.BookingDTO;
import com.app.DTO.BookingResponseDTO;
import com.app.DTO.BookingSlotDTO;
import com.app.DTO.SlotDetailsDTO;
import com.app.Service.BookingService;
import com.app.Service.ParkingSlotService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking")
@CrossOrigin(origins = "http://localhost:3000")
public class BookingController {

    @Autowired
    private BookingService bookingService;
    
    @Autowired
    private ParkingSlotService parkingSlotService;

    @PostMapping("/user/book")
    public ResponseEntity<BookingDTO> bookSlot(@RequestBody BookingDTO bookingDTO) {
        BookingDTO createdBooking = bookingService.bookSlot(bookingDTO);
        return new ResponseEntity<>(createdBooking, HttpStatus.CREATED);
    }
    
    @GetMapping("/user/getAllSlots")
    public ResponseEntity<List<SlotDetailsDTO>>getAllSlot(){
    	try {
    		List<SlotDetailsDTO> allslot = parkingSlotService.getAllSlots();
	    	return new ResponseEntity<>(allslot,HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
		
    	
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BookingSlotDTO>> getBookingsByUserId(@PathVariable Long userId) {
        List<BookingSlotDTO> bookings = bookingService.getBookingsByUserId(userId);
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }
    
    @GetMapping("/getAllBookings")
    public ResponseEntity<List<BookingResponseDTO>> getAllBookings() {
        List<BookingResponseDTO> bookingResp = bookingService.getAllBookings();
        return new ResponseEntity<>(bookingResp, HttpStatus.OK);
    }

}