package ru.chuikov.server.controller.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;
import ru.chuikov.server.entity.Role;
import ru.chuikov.server.entity.User;
import ru.chuikov.server.entity.UserPrincipal;
import ru.chuikov.server.service.AccountDetailsService;

import java.security.Principal;
import java.util.Collections;

@RestController
@RequestMapping("/api/user")
public class userController {
    @Autowired
    private AccountDetailsService userService;

    //edit
    @PostMapping
    public ResponseEntity me(UserPrincipal userPrincipal){
        return new ResponseEntity(userPrincipal.getFullInfo(), HttpStatus.OK);
    }

    @RequestMapping(path = "/add",method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody User user)
    {
        user.setRoles(Collections.singletonList(new Role("user")));
        user = userService.add(user);
        return new ResponseEntity(user, HttpStatus.CREATED);
    }

    @RequestMapping(path = "/update",method = RequestMethod.POST)
    public ResponseEntity update(@RequestBody User user)
    {
        user=userService.update(user);
        return new ResponseEntity(user, HttpStatus.OK);
    }

    @RequestMapping(path = "/get",method = RequestMethod.POST)
    public ResponseEntity get(@RequestParam("id") User user)
    {
        //rewrite
        return new ResponseEntity(user, HttpStatus.OK);
    }

    @RequestMapping(path = "/getList",method = RequestMethod.POST)
    public ResponseEntity get()
    {
        return new ResponseEntity(userService.getAll(), HttpStatus.OK);
    }


}
