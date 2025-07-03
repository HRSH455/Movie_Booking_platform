package com.examly.springapp.entity;

import com.fasterxml.jackson.annotation.ObjectIdGenerators.StringIdGenerator;

import jakarta.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;
    private String email;
    private String password;
    private String username;
    private String mobileNumber;
    private String userRole; 
}
