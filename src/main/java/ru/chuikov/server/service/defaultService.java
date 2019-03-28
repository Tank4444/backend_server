package ru.chuikov.server.service;

import com.mongodb.client.gridfs.GridFSBucket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.chuikov.server.entity.OauthClientDetails;
import ru.chuikov.server.entity.Role;
import ru.chuikov.server.entity.User;
import ru.chuikov.server.repository.OauthClientRepository;
import ru.chuikov.server.repository.RoleRepository;

import javax.annotation.PostConstruct;
import java.util.Collections;

@Service
public class defaultService {

    @Autowired
    private AccountDetailsService userService;

    @Autowired
    private RoleRepository roleService;

    @Autowired
    private OauthClientRepository oauthClientRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //"{bcrypt}"
    @PostConstruct
    private void setupDefaultUser() {
        //Set def Roles
        //1-user
        //2-admin
        roleService.saveAndFlush(new Role("role_user"));
        roleService.saveAndFlush(new Role("role_admin"));

        //Set admin
        //login - admin
        //password - admin
        User user=new User("admin@mail.ru",
                passwordEncoder.encode("admin")

        );
        user.setRoles(roleService.findAll());

        userService.add(user);

        user=new User("user@user.ru",
                passwordEncoder.encode("user"));
        user.setRoles(Collections.singletonList(roleService.findRoleByName("role_user")));

        userService.add(user);

        OauthClientDetails oauthClientDetails=new OauthClientDetails(
                "client",
                passwordEncoder.encode("password"),
                "USER_CLIENT_RESOURCE",
                "read,write",
                "password,refresh_token",
                null,
                "role_user,role_admin",
                900,3600,
                "{}",null);
        oauthClientRepository.saveAndFlush(oauthClientDetails);

    }

}
