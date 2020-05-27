package cz.pazdera.school.dbs.DBS_Hotel.dto.reservation;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class CreateFeedbackDto {
    @NotBlank
    @Length(max = 1023)
    public String feedback;
}
