package cz.pazdera.school.dbs.DBS_Hotel.dto.user;

import cz.pazdera.school.dbs.DBS_Hotel.model.UserDetails;
import cz.pazdera.school.dbs.DBS_Hotel.model.UserRole;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CreateUserDto {
    @NotNull
    @Email(message = "must be valid email address")
    public String username;
    @NotNull
    @Length(min = 8)
    @NotBlank
    public String password;
    @NotNull
    public UserRole role;

    public UserDetails getDetails(){
        var details = new UserDetails();
        details.setPassword(password);
        details.setUsername(username);
        if(role==null){
            details.setRole(UserRole.USER);
        }else {
            details.setRole(role);
        }
        return details;
    }
}
