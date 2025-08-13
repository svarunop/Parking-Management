package com.app.Service;

import java.util.List;

import com.app.DTO.BookingSlotDTO;
import com.app.DTO.CreateSlotDTO;
import com.app.DTO.SlotDetailsDTO;

public interface ParkingSlotService {

	

	SlotDetailsDTO createSlot(CreateSlotDTO createSlotDTO);

	List<SlotDetailsDTO> getAllSlots();

	SlotDetailsDTO getSlotById(Long slotId);

	SlotDetailsDTO updateSlot(Long slotId, CreateSlotDTO createSlotDTO);

	void deleteSlot(Long slotId);

	SlotDetailsDTO getSlotDetails(Long id);

	void bookSlot(BookingSlotDTO bookSlotDTO);

}
