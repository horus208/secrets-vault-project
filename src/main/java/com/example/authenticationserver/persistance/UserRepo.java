package com.example.authenticationserver.persistance;

import com.example.authenticationserver.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<AppUser,Integer>
{
    public Optional<AppUser> findByEmail(String email);
    public void deleteByEmail(String email);
    public boolean existsByEmail(String email);

}
