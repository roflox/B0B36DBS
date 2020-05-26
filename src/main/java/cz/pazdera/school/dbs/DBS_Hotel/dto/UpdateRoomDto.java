package cz.pazdera.school.dbs.DBS_Hotel.dto;

import cz.pazdera.school.dbs.DBS_Hotel.model.Room;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.util.Set;

public class UpdateRoomDto {


    @Min(1)
    public Integer capacity;
    public Boolean television;
    public Boolean balcony;
    @Min(1)
    public BigDecimal price;

    public Room getRoom() {
        var temp = new Room();
        temp.setCapacity(capacity);
        temp.setPrice(price);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<UpdateRoomDto>> violations = validator.validate(this);
        System.err.println(violations);
        return temp;
    }


}
