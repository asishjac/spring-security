package com.example.HealthCareService.Service;

import com.example.HealthCareService.Model.ApplicationUser;
import com.example.HealthCareService.Repository.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserAuthService implements UserDetailsService {

    @Autowired
    private ApplicationUserRepository applicationUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        ApplicationUser applicationUser=applicationUserRepository.findById(username).get();
        return new User(applicationUser.getUser_name(),applicationUser.getPassword(),new ArrayList<>());
    }
}
