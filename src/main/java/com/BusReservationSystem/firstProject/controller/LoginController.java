package com.BusReservationSystem.firstProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.BusReservationSystem.firstProject.DTO.UserLoginDTO;

import com.BusReservationSystem.firstProject.service.DefaultUserService;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private DefaultUserService userService;

    @ModelAttribute("user")
    public UserLoginDTO userLoginDTO() {
        return new UserLoginDTO();
    }

    @GetMapping
    public String login() {
        return "login";
    }

    @PostMapping
    public void  loginUser(@ModelAttribute("user")
                           UserLoginDTO userLoginDTO) {
        userService.loadUserByUsername(userLoginDTO.getUsername());
    }

}
