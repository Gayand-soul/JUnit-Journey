package com.example;

import java.util.Optional;

public class UserRepository {

    public Optional<String> findById(int id){
        throw new UnsupportedOperationException("DB not available in tests");
    }
    public boolean existsByEmail(String email){
        throw new UnsupportedOperationException("DB not available in tests");
    }
    public void save(String username){
        throw new UnsupportedOperationException("DB not available in tests");
    }
}
