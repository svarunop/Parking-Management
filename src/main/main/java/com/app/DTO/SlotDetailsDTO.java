package com.app.DTO;

public class SlotDetailsDTO {
	private Long slotId; 
	private String slotName; 
	private double slotPricePerHour; 
	private boolean isAvailable; 
	// Default constructor
	public SlotDetailsDTO() {
	}

	// Constructor with parameters
	public SlotDetailsDTO(Long slotId, String slotName, String location, String size, double slotPricePerHour,
			boolean isAvailable) {
		this.slotId = slotId;
		this.slotName = slotName;
		this.slotPricePerHour = slotPricePerHour;
		this.isAvailable = isAvailable;
	}

	// Getters and Setters
	public Long getSlotId() {
		return slotId;
	}

	public void setSlotId(Long slotId) {
		this.slotId = slotId;
	}

	public String getSlotName() {
		return slotName;
	}

	public void setSlotName(String slotName) {
		this.slotName = slotName;
	}

	public double getSlotPricePerHour() {
		return slotPricePerHour;
	}

	public void setSlotPricePerHour(double slotPricePerHour) {
		this.slotPricePerHour = slotPricePerHour;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean available) {
		isAvailable = available;
	}

	@Override
	public String toString() {
		return "SlotDetailsDTO [slotId=" + slotId + ", slotName=" + slotName + ", slotPricePerHour=" + slotPricePerHour
				+ ", isAvailable=" + isAvailable + "]";
	}

	
}
