package cz.pazdera.school.dbs.DBS_Hotel.dto.promo;

import cz.pazdera.school.dbs.DBS_Hotel.model.PromoCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class CreatePromoDto {

    @NotNull
    @Length(min = 3)
    public String name;
    @NotNull
    public Boolean active;
    @NotNull
    @Min(1)
    public Integer discount;
    @NotNull
    @Length(min = 3)
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
