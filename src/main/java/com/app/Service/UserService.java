package com.app.Service;

import java.util.List;

import com.app.DTO.UserDTO;

public interface UserService {

	UserDTO addCustomer(UserDTO userDTO);

	UserDTO editCustomer(Long id, UserDTO userDTO);

	void deleteCustomer(Long id);

	List<UserDTO> getAllCustomers();

	UserDTO getCustomerById(Long id);

	UserDTO registerCustomer(UserDTO userDTO);

}
