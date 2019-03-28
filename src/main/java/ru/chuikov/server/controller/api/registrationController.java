package ru.chuikov.server.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.chuikov.server.service.AccountDetailsService;

@RestController
@RequestMapping("/signin")
public class registrationController {
    @Autowired
    private AccountDetailsService userService;

    @PostMapping
    public ResponseEntity<String> signup(
            @RequestParam("email") String email,
            @RequestParam("password") String password) {
        if(!userService.freeEmail(email))
            return new ResponseEntity<>("email is used", HttpStatus.BAD_REQUEST);
        else{
            userService.signUp(email,password);
            return new ResponseEntity<>("Sign up", HttpStatus.OK);
        }
    }
}
