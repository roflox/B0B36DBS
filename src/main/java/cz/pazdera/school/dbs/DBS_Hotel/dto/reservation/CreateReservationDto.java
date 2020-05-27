package cz.pazdera.school.dbs.DBS_Hotel.dto.reservation;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class CreateReservationDto {

    @NotNull
    @Min(1)
    public Integer roomNumber;
    @Min(1)
    @NotNull
    public String promoCode;
    @Min(1)
    @NotNull
    public Integer duration;

    @JsonFormat(pattern = "yyyy-MM-dd")
    public LocalDate startDate;

    @Min(1)
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
