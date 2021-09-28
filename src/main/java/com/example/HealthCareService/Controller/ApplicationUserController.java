package com.example.HealthCareService.Controller;

import com.example.HealthCareService.Model.ApplicationUser;
import com.example.HealthCareService.Security.JwtUtil;
import com.example.HealthCareService.Service.ApplicationUserService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
public class ApplicationUserController {

    @Autowired
    private ApplicationUserService applicationUserService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<JSONObject> registerApplicationUser(@RequestBody ApplicationUser applicationUser){
        ApplicationUser registeredApplicationUser=applicationUserService.registerApplicationUserService(applicationUser);
        if(registeredApplicationUser==null){
            JSONObject responseJson = new JSONObject();
            responseJson.put("message","Password or Username policy failed");
            return new ResponseEntity<JSONObject >(responseJson, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        JSONObject responseJson = new JSONObject();
        responseJson.put("message","Registration Successful");
        return new ResponseEntity<JSONObject >(responseJson, HttpStatus.OK);
    }

    @PostMapping("/signin")
    public ResponseEntity<JSONObject>userSignin(@RequestBody ApplicationUser applicationUser){
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(applicationUser.getUser_name(),applicationUser.getPassword())
            );
            String token=jwtUtil.generateToken(applicationUser.getUser_name());
            JSONObject responseJson = new JSONObject();
            responseJson.put("token",token);
            responseJson.put("id",applicationUser.getUser_name());
            return new ResponseEntity<JSONObject>(responseJson,HttpStatus.OK);
        }catch (Exception exception){
            JSONObject responseJson = new JSONObject();
            responseJson.put("message","Username or Password is incorrect");
            return new ResponseEntity<JSONObject>(responseJson,HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/viewprofile/{id}")
    public ResponseEntity<ApplicationUser>viewProfile(@PathVariable String appUserId){

        ApplicationUser applicationUser = applicationUserService.getApplicationUserService(appUserId);
        if(applicationUser==null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(applicationUser,HttpStatus.OK);
    }

    @PutMapping("/editprofile")
    public ResponseEntity<ApplicationUser>editProfile(@RequestBody ApplicationUser applicationUser){

        ApplicationUser updatedApplicationUser = applicationUserService.updateUserProfileService(applicationUser);
        if(updatedApplicationUser==null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(updatedApplicationUser,HttpStatus.OK);
    }

}
