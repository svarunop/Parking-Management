package com.app.Service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.DTO.UserDTO;
import com.app.Entity.Role;
import com.app.Entity.User;
import com.app.Repository.UserRepository;
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    private final ModelMapper modelMapper;

    public UserServiceImpl() {
        this.modelMapper = new ModelMapper();

        // Custom converter to exclude the password field
        Converter<User, UserDTO> userToUserDTOConverter = new Converter<User, UserDTO>() {
            @Override
            public UserDTO convert(MappingContext<User, UserDTO> context) {
                User source = context.getSource();
                UserDTO destination = new UserDTO();
                destination.setId(source.getId());
                destination.setName(source.getName());
                destination.setEmail(source.getEmail());
                destination.setPhoneNumber(source.getPhoneNumber());
                destination.setVehicleNumber(source.getVehicleNumber());
                destination.setPassword(source.getPassword());
                return destination;
            }
        };

        // Add the custom converter
        modelMapper.addConverter(userToUserDTOConverter);
    }

    // Add a new customer
    public UserDTO addCustomer(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        user.setRole(Role.ROLE_USER);
        user = userRepository.save(user);
        return modelMapper.map(user, UserDTO.class);
    }

    // Edit an existing customer
    public UserDTO editCustomer(Long id, UserDTO userDTO) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        existingUser.setName(userDTO.getName());
        existingUser.setEmail(userDTO.getEmail());
        existingUser.setPhoneNumber(userDTO.getPhoneNumber());
        existingUser.setVehicleNumber(userDTO.getVehicleNumber());
        existingUser.setPassword(userDTO.getPassword());
        userRepository.save(existingUser);
        return modelMapper.map(existingUser, UserDTO.class);
    }

    // Delete a customer
    public void deleteCustomer(Long id) {
        userRepository.deleteById(id);
    }

    // Get all customers
    public List<UserDTO> getAllCustomers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    // Get customer by ID
    public UserDTO getCustomerById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return modelMapper.map(user, UserDTO.class);
    }

	@Override
	public UserDTO registerCustomer(UserDTO userDTO) {
		   User user = modelMapper.map(userDTO, User.class);
            user.setRole(Role.ROLE_USER);
	        // Save the user without hashing the password
	        user = userRepository.save(user);

	        // Map the entity back to a DTO and return
	        return modelMapper.map(user, UserDTO.class);
	}
}
