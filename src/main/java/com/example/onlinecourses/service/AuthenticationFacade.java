package com.example.onlinecourses.service;

import com.example.onlinecourses.models.entities.User;



public interface AuthenticationFacade {
    User getCurrentPrincipal();
}
