package com.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.Entity.ParkingSlot;

public interface ParkingSlotRepository extends JpaRepository<ParkingSlot, Long> {
}
