package com.app.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.DTO.UserDTO;
import com.app.Service.UserService;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {

    @Autowired
    private UserService userService;
    
	@Autowired
	private PasswordEncoder passwordEncode;

    @PostMapping("/signup")
    public ResponseEntity<UserDTO> registerCustomer(@RequestBody UserDTO userDTO) {
    	userDTO.setPassword(passwordEncode.encode(userDTO.getPassword()));
    	System.out.println("Received user data: " + userDTO);
        UserDTO createdUser = userService.registerCustomer(userDTO);
        System.out.println("Created user: " + createdUser);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
    
    // Edit an existing customer
    @PutMapping("customer/editCustomer/{id}")
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
    
    @GetMapping("customer/viewCustomer/{id}")
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
