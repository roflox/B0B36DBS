package cz.pazdera.school.dbs.DBS_Hotel.dto;

import cz.pazdera.school.dbs.DBS_Hotel.model.CustomerDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;

public class LoginDto implements UserDetails, Serializable {

    private String username;
    private String password;

    public LoginDto(){};

    public LoginDto(CustomerDetails customerDetails){
        this.password=customerDetails.getPassword();
        this.username=customerDetails.getUsername();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
