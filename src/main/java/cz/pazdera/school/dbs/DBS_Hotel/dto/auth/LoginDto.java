package cz.pazdera.school.dbs.DBS_Hotel.dto.auth;

import cz.pazdera.school.dbs.DBS_Hotel.model.UserDetails;
import cz.pazdera.school.dbs.DBS_Hotel.model.UserRole;

import java.io.Serializable;

public class LoginDto implements Serializable {

    public String username;
    public String password;
    public UserRole role;

}
