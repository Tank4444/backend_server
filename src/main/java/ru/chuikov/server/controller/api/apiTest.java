package ru.chuikov.server.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.chuikov.server.entity.User;
import ru.chuikov.server.entity.UserPrincipal;
import ru.chuikov.server.service.AccountDetailsService;

import java.security.Principal;

@Controller
@RequestMapping("/api/test")
public class apiTest {

    @GetMapping
    public String testGet()
    {
        return "test";
    }

    @PostMapping
    @ResponseBody
    public String testPost() { return "Test OK";}


    @ResponseBody
    @RequestMapping("/admin")
    @PreAuthorize("hasAuthority('role_admin')")
    public String testAdmin(){
        return "OK admin";
    }
    @ResponseBody
    @RequestMapping("/user")
    @PreAuthorize("hasAuthority('role_user')")
    public String testUser(){
        return "OK user";
    }

    @ResponseBody
    @RequestMapping("/all")
    @PreAuthorize("hasAnyAuthority('role_user','role_admin')")
    public String testAll(){
        return "OK user";
    }

}
