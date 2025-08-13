package com.app.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.DTO.CreateSlotDTO;
import com.app.DTO.SlotDetailsDTO;
import com.app.DTO.UserDTO;
import com.app.Service.ParkingSlotService;
import com.app.Service.UserService;

	@RestController
	@RequestMapping("/admin")
	@CrossOrigin("http://localhost:3000")
	public class AdminController {

	    @Autowired
	    private ParkingSlotService parkingSlotService;
	    
	    @Autowired
	    private UserService userService;
	    
	    
		@Autowired
		private PasswordEncoder passwordEncode;

	    // Create a new parking slot
	    @PostMapping("/addslot")
	    public ResponseEntity<SlotDetailsDTO> createSlot(@RequestBody CreateSlotDTO createSlotDTO) {
	        try {
	            SlotDetailsDTO createdSlot = parkingSlotService.createSlot(createSlotDTO);
	            return new ResponseEntity<>(createdSlot, HttpStatus.CREATED);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

//	    // Book a parking slot
//	    @PostMapping("/bookSlot")
//	    public ResponseEntity<String> bookSlot(@RequestBody BookingSlotDTO bookSlotDTO) {
//	        try {
//	            parkingSlotService.bookSlot(bookSlotDTO);
//	            return new ResponseEntity<>("Slot booked successfully", HttpStatus.CREATED);
//	        } catch (Exception e) {
//	            e.printStackTrace();
//	            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//	        }
//	    }

	    // Get details of a specific parking slot
	    @GetMapping("/getSlotDetails/{id}")
	    public ResponseEntity<SlotDetailsDTO> getSlotDetails(@PathVariable Long id) {
	        try {
	            SlotDetailsDTO slotDetails = parkingSlotService.getSlotDetails(id);
	            if (slotDetails != null) {
	                return new ResponseEntity<>(slotDetails, HttpStatus.OK);
	            } else {
	                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	    @GetMapping("/getAllSlots")
	    public ResponseEntity<List<SlotDetailsDTO>>getAllSlot(){
	    	try {
	    		List<SlotDetailsDTO> allslot = parkingSlotService.getAllSlots();
		    	return new ResponseEntity<>(allslot,HttpStatus.OK);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	        }
			
	    	
	    }

	    // Update a parking slot
	    @PutMapping("/updateSlot/{id}")
	    public ResponseEntity<SlotDetailsDTO> updateSlot(@PathVariable Long id, @RequestBody CreateSlotDTO createSlotDTO) {
	        try {
	            SlotDetailsDTO updatedSlot = parkingSlotService.updateSlot(id, createSlotDTO);
	            return new ResponseEntity<>(updatedSlot, HttpStatus.OK);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

	    // Delete a parking slot
	    @DeleteMapping("/deleteSlot/{id}")
	    public ResponseEntity<Void> deleteSlot(@PathVariable Long id) {
	        try {
	            parkingSlotService.deleteSlot(id);
	            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	    

	    // Add a new customer
	    @PostMapping("/addCustomer")
	    public ResponseEntity<UserDTO> addCustomer(@RequestBody UserDTO userDTO) {
	        try {
	        	userDTO.setPassword(passwordEncode.encode(userDTO.getPassword()));
	            UserDTO createdUser  = userService.addCustomer(userDTO);
	            return new ResponseEntity<>(createdUser , HttpStatus.CREATED);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

	    // Edit an existing customer
	    @PutMapping("/editCustomer/{id}")
	    public ResponseEntity<UserDTO> editCustomer(@PathVariable Long id, @RequestBody UserDTO userDTO) {
	        try {
	        	userDTO.setPassword(passwordEncode.encode(userDTO.getPassword()));
	            UserDTO updatedUser  = userService.editCustomer(id, userDTO);
	            return new ResponseEntity<>(updatedUser , HttpStatus.OK);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

	    // Delete a customer
	    @DeleteMapping("/deleteCustomer/{id}")
	    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
	        try {
	            userService.deleteCustomer(id);
	            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

	    // View all customers
	    @GetMapping("/viewAllCustomers")
	    public ResponseEntity<List<UserDTO>> viewAllCustomers() {
	        try {
	            List<UserDTO> users = userService.getAllCustomers();
	            return new ResponseEntity<>(users, HttpStatus.OK);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

	    // View customer by ID
	    @GetMapping("/viewCustomer/{id}")
	    public ResponseEntity<UserDTO> viewCustomerById(@PathVariable Long id) {
	        try {
	            UserDTO user = userService.getCustomerById(id);
	            if (user != null) {
	                return new ResponseEntity<>(user, HttpStatus.OK);
	            } else {
	                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	}
