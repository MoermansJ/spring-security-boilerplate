package com.example.demo.controller;

import com.example.demo.model.dto.AuthenticationRequest;
import com.example.demo.model.exception.AuthenticationException;
import com.example.demo.service.UserDeleteService;
import com.example.demo.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authenticate")
public class AuthenticationController {
    private AuthenticationService authenticationService;
    private UserDeleteService userDeleteService;

    public AuthenticationController(AuthenticationService authenticationService,
                                    UserDeleteService userDeleteService) {
        this.authenticationService = authenticationService;
        this.userDeleteService = userDeleteService;
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody AuthenticationRequest authenticationRequest) {
        try {
            return ResponseEntity.ok(authenticationService.register(authenticationRequest));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unable to process requests at this time.");
        }
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationRequest authenticationRequest) {
        try {
            return ResponseEntity.ok(authenticationService.login(authenticationRequest));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unable to process requests at this time.");
        }
    }

//    @DeleteMapping("/delete")
//    public ResponseEntity delete(@RequestParam long id) {
//        try {
//            return ResponseEntity.ok(userDeleteService.delete(id));
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
//        }
//    }
}
