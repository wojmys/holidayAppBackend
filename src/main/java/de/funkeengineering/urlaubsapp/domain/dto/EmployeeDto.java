package de.funkeengineering.urlaubsapp.domain.dto;

import de.funkeengineering.urlaubsapp.domain.Booking;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
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
    private List<Long> bookingsId = new ArrayList<>();
    private List<Long> substitutionsId = new ArrayList<>();

}
