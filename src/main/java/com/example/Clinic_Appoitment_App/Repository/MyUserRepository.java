package com.example.Clinic_Appoitment_App.Repository;

import com.example.Clinic_Appoitment_App.Model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MyUserRepository extends JpaRepository<MyUser,Long> {
    Optional<MyUser> findByUsername(String username);
}
