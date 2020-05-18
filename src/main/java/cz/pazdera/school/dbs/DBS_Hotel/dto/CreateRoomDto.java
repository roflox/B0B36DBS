package cz.pazdera.school.dbs.DBS_Hotel.dto;

import cz.pazdera.school.dbs.DBS_Hotel.model.Room;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Set;

public class CreateRoomDto {
    @NotNull
    public int number;
    @Min(1)
    @NotNull
    public int capacity;
    public Boolean television;
    public Boolean balcony;
    @Min(1)
    @NotNull
    public BigDecimal price;

    public Room getRoom(){
        var temp = new Room();
        temp.setCapacity(capacity);
        temp.setNumber(number);
        temp.setPrice(price);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<CreateRoomDto>> violations = validator.validate(this);
        System.err.println(violations);
        return temp;
    }
}
