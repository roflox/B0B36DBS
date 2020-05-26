package cz.pazdera.school.dbs.DBS_Hotel.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "user_details")
public class UserDetails extends AbstractModel {

    @Basic(optional = false)
    @Column(nullable = false,unique = true)
    private String username;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonIgnore
    private AppUser appUser;

    @Basic(optional = false)
    @JsonIgnore
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String email) {
        this.username = email;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }


    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }


    public ArrayList<SimpleGrantedAuthority> getAuthorities(){
        var temp = new ArrayList<SimpleGrantedAuthority>();
        temp.add(new SimpleGrantedAuthority(this.role.toString()));
        return temp;
    }
}
