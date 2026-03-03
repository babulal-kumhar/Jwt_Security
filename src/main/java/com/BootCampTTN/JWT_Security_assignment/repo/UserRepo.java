package com.BootCampTTN.JWT_Security_assignment.repo;


import com.BootCampTTN.JWT_Security_assignment.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
