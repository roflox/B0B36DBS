package cz.pazdera.school.dbs.DBS_Hotel.dto;

import cz.pazdera.school.dbs.DBS_Hotel.model.UserDetails;
import cz.pazdera.school.dbs.DBS_Hotel.model.UserRole;

import java.io.Serializable;

public class RegisterDto implements Serializable {
    public String username;
    public String password;
    public String zipCode;
    public UserRole role;

    public UserDetails getDetails(){
        var details = new UserDetails();
        details.setPassword(password);
        details.setUsername(username);
        details.setRole(role);
        return details;
    }
}
