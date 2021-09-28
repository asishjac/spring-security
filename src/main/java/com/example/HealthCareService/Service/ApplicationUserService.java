package com.example.HealthCareService.Service;

import com.example.HealthCareService.Model.ApplicationUser;
import com.example.HealthCareService.Repository.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserService {

    @Autowired
    private ApplicationUserRepository applicationUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ApplicationUser registerApplicationUserService(ApplicationUser applicationUser){
        ApplicationUser applicationUserEncoded=new ApplicationUser();
        applicationUserEncoded.setUser_name(applicationUser.getUser_name());
        applicationUserEncoded.setUser_email(applicationUser.getUser_email());
        applicationUserEncoded.setUser_mobile(applicationUser.getUser_mobile());
        applicationUserEncoded.setLocation(applicationUser.getLocation());
        applicationUserEncoded.setPassword(passwordEncoder.encode(applicationUser.getPassword()));
        return applicationUserRepository.save(applicationUserEncoded);
    }

    public ApplicationUser getApplicationUserService(String appUserId){

        return applicationUserRepository.findById(appUserId).get();
    }

    public ApplicationUser updateUserProfileService(ApplicationUser applicationUser){

        return applicationUserRepository.save(applicationUser);
    }
}
