package ru.chuikov.server.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.chuikov.server.entity.User;
import ru.chuikov.server.repository.UserRepository;
import ru.chuikov.server.service.AccountDetailsService;

import java.util.List;
import java.util.Optional;

@Service(value = "userService")
public class AccountDetailsServiceImpl implements AccountDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> user=userRepository.findDetailsByEmail(s);
        if(!user.isPresent()) throw new BadCredentialsException("Bad Credentials");

        //AcPrincipal principal=new AcPrincipal(user.get());
        new AccountStatusUserDetailsChecker().check(user.get());

        return user.get();
    }
    /*
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> userDetails=userRepository.findDetailsByEmail(s);
        if(!userDetails.isPresent()) throw new BadCredentialsException("Bad Credentials");

        new AccountStatusUserDetailsChecker().check(userDetails.get());

        return userDetails.get();
    }
     */

    @Override
    public User add(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User update(User user) {
        return user;
    }

    @Override
    public Optional<User> getById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void signUp(String email, String password) {
        userRepository.saveAndFlush(new User(email,password));
    }

    @Override
    public boolean freeEmail(String email) {
        return !userRepository.findByEmail(email).isPresent();
    }



}
