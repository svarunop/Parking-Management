package com.app.DTO;

public class CreateSlotDTO {
	private Long slotId;
    private String slotName;        // Name of the parking slot
    private double slotPricePerHour; // Price per hour for the parking slot
    private boolean isAvailable;     // Availability status of the parking slot

    // Default constructor
    public CreateSlotDTO() {
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
}