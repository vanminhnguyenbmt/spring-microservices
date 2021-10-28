package com.nguyenvm.auth.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

// A (temporary) class represent the user saved in the database.
@Getter
@Setter
@AllArgsConstructor
public class AppUser {
    private Integer id;
    private String username, password;
    private String role;
}
