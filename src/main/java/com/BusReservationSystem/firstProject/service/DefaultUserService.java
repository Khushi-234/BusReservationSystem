package com.BusReservationSystem.firstProject.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.BusReservationSystem.firstProject.DTO.UserRegisteredDTO;
import com.BusReservationSystem.firstProject.model.User;
import com.BusReservationSystem.firstProject.DTO.BookingsDTO;
import com.BusReservationSystem.firstProject.model.Bookings;

public interface DefaultUserService  extends UserDetailsService{
    User save(UserRegisteredDTO userRegisteredDTO);

    Bookings updateBookings(BookingsDTO bookingDTO,UserDetails user);

    void sendEmail(BookingsDTO bookingDTO, User users, String nameGenrator);
}
