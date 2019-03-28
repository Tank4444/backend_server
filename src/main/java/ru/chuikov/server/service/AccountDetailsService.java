package ru.chuikov.server.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.chuikov.server.entity.User;

import java.util.List;
import java.util.Optional;


public interface AccountDetailsService extends UserDetailsService {
    User add(User user);
    List<User> getAll();
    User update(User user);

    void signUp(String email, String password);

    boolean freeEmail(String email);
    Optional<User> getById(Long id);

}
