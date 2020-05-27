package cz.pazdera.school.dbs.DBS_Hotel.dto.reservation;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.time.LocalDate;

public class CreateReservationDto {

    @NotNull
    @Min(1)
    public Integer roomNumber;
    public String promoCode;
    @Min(1)
    @NotNull
    public Integer duration;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull
    @FutureOrPresent
    public LocalDate startDate;

    @Min(1)
    @NotNull
    public Integer numberOfPersons;

    @Override
    public String toString() {
        return "CreateReservationDto{" +
                "roomNumber=" + roomNumber +
                ", promoCode=" + promoCode +
                ", duration=" + duration +
                ", startDate=" + startDate +
                ", numberOfPersons=" + numberOfPersons +
                '}';
    }
}
