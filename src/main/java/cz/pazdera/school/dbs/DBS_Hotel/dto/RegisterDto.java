package cz.pazdera.school.dbs.DBS_Hotel.dto;

import cz.pazdera.school.dbs.DBS_Hotel.model.CustomerDetails;

import java.io.Serializable;

public class RegisterDto implements Serializable {
    public String username;
    public String password;
    public String zipCode;

    public CustomerDetails getDetails(){
        var details = new CustomerDetails();
        details.setPassword(password);
        details.setUsername(username);
        return details;
    }
}
