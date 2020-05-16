package cz.pazdera.school.dbs.DBS_Hotel.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.Min;
import java.time.LocalDate;

public class CreateReservationDto {

    @Min(1)
    public Integer roomNumber;
    @Min(1)
    public Integer promoId;
    @Min(1)
    public Integer duration;

    @JsonFormat(pattern = "yyyy-MM-dd")
    public LocalDate startDate;

    @Min(1)
    public Integer numberOfPersons;

    @Override
    public String toString() {
        return "CreateReservationDto{" +
                "roomNumber=" + roomNumber +
                ", promoId=" + promoId +
                ", duration=" + duration +
                ", startDate=" + startDate +
                ", numberOfPersons=" + numberOfPersons +
                '}';
    }
}
