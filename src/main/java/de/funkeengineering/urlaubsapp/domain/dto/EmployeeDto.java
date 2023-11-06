package de.funkeengineering.urlaubsapp.domain.dto;

import de.funkeengineering.urlaubsapp.domain.Booking;
import de.funkeengineering.urlaubsapp.domain.Employee;
import de.funkeengineering.urlaubsapp.domain.Holiday;
import jakarta.persistence.*;
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
    private String substitute;
    private int totalHolidays;
    private int remainingHolidays;

    private List<Long> bookingsIds;

}
