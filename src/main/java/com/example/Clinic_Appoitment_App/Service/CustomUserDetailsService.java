package com.example.Clinic_Appoitment_App.Service;

import com.example.Clinic_Appoitment_App.Model.MyUser;
import com.example.Clinic_Appoitment_App.Repository.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private MyUserRepository myUserRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser myUser = myUserRepo.findByUsername(username)
                .orElseThrow(()->new UsernameNotFoundException("User not found"));

        return new User(
                myUser.getUsername(),
                myUser.getPassword(),
                true, true,true, true,
                myUser.getRoles()
                        .stream()
                        .map(role->new SimpleGrantedAuthority("ROLE_"+role.getName()))
                        .collect(Collectors.toSet())
        );
    }
}
