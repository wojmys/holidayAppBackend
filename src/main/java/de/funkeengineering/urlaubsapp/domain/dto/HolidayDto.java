package de.funkeengineering.urlaubsapp.domain.dto;

import de.funkeengineering.urlaubsapp.domain.Booking;
import de.funkeengineering.urlaubsapp.domain.Status;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HolidayDto {

    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private Status status;
    private Long bookingId;
}
