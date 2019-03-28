package ru.chuikov.server.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@Table(name="account")
public class User implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Size(min = 8, message = "Minimum password length: 8 characters")
    @Column(name = "password",nullable = false)
    private String password;

    @Size(min=6,max=255)
    @Column(unique = true,name = "email",nullable = false)
    private String email;

    //@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "account_role",
            joinColumns
                    = @JoinColumn(name = "account_id"),
            inverseJoinColumns
                    = @JoinColumn(name = "role_id"))
    private List<Role> roles;

    @Column(name = "account_enabled")
    private boolean enabled = true;

    @Column(name = "account_locked")
    private boolean accountNonLocked = true;

    @Column(name = "account_expired")
    private boolean accountNonExpired = true;

    @Column(name = "credentials_expired")
    private boolean credentialsNonExpired = true;


    public static Collection<? extends GrantedAuthority> translate(List<Role> roles) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        for (Role role : roles) {
            String name = role.getName().toLowerCase();
            if (!name.startsWith("role_")) {
                name = "role_" + name;
            }
            authorities.add(new SimpleGrantedAuthority(name));
        }
        return authorities;
    }


    public String getFullName()
    {
        return  "ID = "+String.valueOf(this.id)+
                " Fitst Name : "+this.firstName+
                " Last Name : "+this.lastName+
                " Email: "+this.email;
    }

    
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        roles.forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));

        });
        return authorities;
    }

    
    public String getPassword() {
        return password;
    }

    
    public String getUsername() {
        return email;
    }


    
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    
    public boolean isEnabled() {
        return enabled;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public User() {
    }

    public User(@Size(min = 6, max = 255) String email,@Size(min = 8, message = "Minimum password length: 8 characters") String password) {
        this.password = password;
        this.email = email;
    }

}
