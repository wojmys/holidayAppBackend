package de.funkeengineering.urlaubsapp.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeDto {

    private Long id;
    private String name;
    private int totalHolidays;
    private int remainingHolidays;
    private List<BookingDto> bookings;

}
