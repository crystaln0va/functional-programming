package com.RateMyProfessor.BackEndFunctionalProgramming.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUser {
    
    private Long id;
    private String name;
    private String username;
    private String password;
    private String email;
    private String university;
    private Long universityID;
}
