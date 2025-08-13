package com.app.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.DTO.BookingSlotDTO;
import com.app.DTO.CreateSlotDTO;
import com.app.DTO.SlotDetailsDTO;
import com.app.Entity.ParkingSlot; // Assuming you have a ParkingSlot entity
import com.app.Repository.ParkingSlotRepository; // Assuming you have a ParkingSlotRepository

@Service
public class ParkingSlotServiceImpl implements ParkingSlotService {

    @Autowired
    private ParkingSlotRepository parkingSlotRepository; // Injecting the ParkingSlotRepository

    @Override
    public SlotDetailsDTO createSlot(CreateSlotDTO createSlotDTO) {
        ParkingSlot parkingSlot = new ParkingSlot(); // Create a new ParkingSlot entity
        parkingSlot.setAvailable(createSlotDTO.isAvailable());
        parkingSlot.setSlotName(createSlotDTO.getSlotName());
        parkingSlot.setPricePerHour(createSlotDTO.getSlotPricePerHour());

        ParkingSlot savedSlot = parkingSlotRepository.save(parkingSlot); // Save the slot to the database
        return mapToDTO(savedSlot); // Return the saved slot as a DTO
    }

    @Override
    public void bookSlot(BookingSlotDTO bookSlotDTO) {
        // Implement booking logic here
        // For example, check if the slot is available and then mark it as booked
        Optional<ParkingSlot> optionalSlot = parkingSlotRepository.findById(bookSlotDTO.getSlotId());
        if (optionalSlot.isPresent()) {
            ParkingSlot slot = optionalSlot.get();
            if (slot.isAvailable()) {
                // Mark the slot as booked (you may want to add more logic here)
                slot.setAvailable(false); // Set to false to indicate it's booked
                parkingSlotRepository.save(slot); // Save the updated slot
            } else {
                throw new RuntimeException("Slot is not available for booking");
            }
        } else {
            throw new RuntimeException("Slot not found");
        }
    }

    @Override
    public SlotDetailsDTO getSlotDetails(Long id) {
        Optional<ParkingSlot> optionalSlot = parkingSlotRepository.findById(id);
        return optionalSlot.map(this::mapToDTO).orElse(null); // Map to DTO if found
    }

    @Override
    public SlotDetailsDTO updateSlot(Long id, CreateSlotDTO createSlotDTO) {
        Optional<ParkingSlot> optionalSlot = parkingSlotRepository.findById(id);
        if (optionalSlot.isPresent()) {
            ParkingSlot slot = optionalSlot.get();
            // Update fields from CreateSlotDTO to ParkingSlot entity
            slot.setAvailable(createSlotDTO.isAvailable());
            slot.setSlotName(createSlotDTO.getSlotName());
            slot.setPricePerHour(createSlotDTO.getSlotPricePerHour());

            ParkingSlot updatedSlot = parkingSlotRepository.save(slot); // Save the updated slot
            return mapToDTO(updatedSlot); // Return the updated slot as a DTO
        }
        return null; // Or throw a custom exception
    }
    @Override
    public List<SlotDetailsDTO> getAllSlots() {
        List<ParkingSlot> slots = parkingSlotRepository.findAll(); // Retrieve all slots
        return slots.stream()
                .map(this::mapToDTO) // Map each ParkingSlot entity to SlotDetailsDTO
                .collect(Collectors.toList());
    }

	@Override
	public SlotDetailsDTO getSlotById(Long slotId) {
		 Optional<ParkingSlot> optionalSlot = parkingSlotRepository.findById(slotId);
	        return optionalSlot.map(this::mapToDTO).orElse(null);
	}

	@Override
	public void deleteSlot(Long slotId) {
		parkingSlotRepository.deleteById(slotId); // Delete the slot by ID
		
	}
	private SlotDetailsDTO mapToDTO(ParkingSlot slot) {
	    SlotDetailsDTO slotDetailsDTO = new SlotDetailsDTO();
	    slotDetailsDTO.setSlotId(slot.getSlotId()); // Assuming getId() returns the slot's unique identifier
	    slotDetailsDTO.setSlotName(slot.getSlotName()); // Assuming getSlotName() returns the name of the slot
	    slotDetailsDTO.setSlotPricePerHour(slot.getPricePerHour()); // Assuming getSlotPricePerHour() returns the price per hour
	    slotDetailsDTO.setAvailable(slot.isAvailable()); // Assuming isAvailable() returns the availability status
	    return slotDetailsDTO; // Return the populated DTO
	}
}

	