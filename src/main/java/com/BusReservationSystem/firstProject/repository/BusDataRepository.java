package com.BusReservationSystem.firstProject.repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.BusReservationSystem.firstProject.DTO.BookingsDTO;
import com.BusReservationSystem.firstProject.model.BusData;

@Repository
public interface BusDataRepository extends JpaRepository<BusData, Integer>{
    @Query(value = "select * from Reservation  where reservation.to_destination =:to and reservation.from_destination =:from and reservation.filter_date =:date  order By reservation.filter_date desc " , nativeQuery = true)
    List<BusData> findByToFromAndDate(String to , String from, Date date);



}