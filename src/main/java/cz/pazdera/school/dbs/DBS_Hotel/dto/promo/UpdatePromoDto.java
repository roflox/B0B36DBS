package cz.pazdera.school.dbs.DBS_Hotel.dto.promo;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.Min;

public class UpdatePromoDto {

    @Length(min = 3)
    public String name;
    public Boolean active;
    @Min(1)
    public Integer discount;
    @Length(min = 3)
    public String code;
}
