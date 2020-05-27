package cz.pazdera.school.dbs.DBS_Hotel.dto.promo;

import cz.pazdera.school.dbs.DBS_Hotel.model.PromoCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CreatePromoDto {

    @NotNull(message = "name may not be null")
    @NotBlank
    @Length(min = 3)
    public String name;
    @NotNull(message = "active may not be null")
    public Boolean active;
    @NotNull(message = "discount may not be null")
    @Min(1)
    public Integer discount;
    @NotNull(message = "code may not be null")
    @Length(min = 3)
    @NotBlank
    public String code;

    public PromoCode getPromo(){
        var tmp = new PromoCode();
        tmp.setActive(active);
        tmp.setDiscount(discount);
        tmp.setName(name);
        tmp.setCode(code);
        return tmp;
    }
}
