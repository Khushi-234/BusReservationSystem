package com.BusReservationSystem.firstProject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BusReservationSystem.firstProject.model.Bookings;




public interface BookingsRepository extends JpaRepository<Bookings, Integer> {

    List<Bookings> findByUserId(int userId);

}