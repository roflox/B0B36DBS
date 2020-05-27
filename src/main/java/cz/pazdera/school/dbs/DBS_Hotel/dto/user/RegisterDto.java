package cz.pazdera.school.dbs.DBS_Hotel.dto.user;

import cz.pazdera.school.dbs.DBS_Hotel.model.UserDetails;
import cz.pazdera.school.dbs.DBS_Hotel.model.UserRole;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class RegisterDto implements Serializable {
    @NotNull
    @Email(message = "must be valid email address")
    public String username;
    @NotNull
    @Length(min = 8)
    @NotBlank
    public String password;

    public UserDetails getDetails(){
        var details = new UserDetails();
        details.setPassword(password);
        details.setUsername(username);
        details.setRole(UserRole.USER);
        return details;
    }
}
