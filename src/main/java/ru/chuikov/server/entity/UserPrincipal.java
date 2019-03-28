package ru.chuikov.server.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class UserPrincipal {

    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private boolean enabled = true;
    private boolean accountNonLocked = true;
    private boolean accountNonExpired = true;
    private boolean credentialsNonExpired = true;

    public UserPrincipal() {

    }

    public String getFullInfo(){

        String out="id = "+id;
        if(firstName!=null)out+=" First Name = "+firstName;
        if(lastName!=null)out+=" Last Name = "+lastName;
        if(email!=null)out+=" Email = "+email;

        return out;
    }

    public UserPrincipal(Map<String, ?> map){
        if(map.containsKey("email")&&map.get("email")!=null)this.setEmail(map.get("email").toString());
        if(map.containsKey("id")&&map.get("id")!=null)this.setId(Long.valueOf(map.get("id").toString()));
        if(map.containsKey("firstName")&&map.get("firstName")!=null)this.setFirstName(map.get("firstName").toString());
        if(map.containsKey("lastName")&&map.get("lastName")!=null)this.setLastName(map.get("lastName").toString());
        if(map.containsKey("account_enabled")&&map.get("account_enabled")!=null)this.setEnabled((Boolean) map.get("account_enabled"));
        if(map.containsKey("account_locked")&&map.get("account_locked")!=null)this.setAccountNonLocked((Boolean) map.get("account_locked"));
        if(map.containsKey("account_expired")&&map.get("account_expired")!=null)this.setAccountNonExpired((Boolean) map.get("account_expired"));
        if(map.containsKey("credentials_expired")&&map.get("credentials_expired")!=null)this.setCredentialsNonExpired((Boolean) map.get("credentials_expired"));
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }


    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }
}
