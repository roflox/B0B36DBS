package cz.pazdera.school.dbs.DBS_Hotel.dto.reservation;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class UpdateReservationDto {

    @Min(1)
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
                ", duration=" + duration +
                ", startDate=" + startDate +
                ", numberOfPersons=" + numberOfPersons +
                '}';
    }
}
