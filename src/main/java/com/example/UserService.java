package com.example;

import java.util.Optional;

public class UserService {

    private final UserRepository repo;

    public UserService (UserRepository repo){
        this.repo = repo;
    }

    /* Returns username, or "Guest" if not found.*/
    public String getUserName(int id){
        return repo.findById(id).orElse("Guest");
    }

    /*
     * Registers a new user.
     * Throws IllegalArgumentException if username is null, blank or < 3 chars.
     * Throws IllegalArgumentException if the email is already taken.
     */
    public void register(String username, String email){
        if(username == null || username.isBlank() || username.length() < 3){
            throw new IllegalArgumentException("Invalid username: " + username);
        }
        if(repo.existsByEmail(email)){
            throw new IllegalStateException("Email already registered: "+ email);
        }
        repo.save(username);
    }
}
