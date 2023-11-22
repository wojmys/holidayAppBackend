package de.funkeengineering.urlaubsapp.domain.dto;

import lombok.*;

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
    @Builder.Default
    private List<Long> bookingIds = new ArrayList<>();
    @Builder.Default
    private List<Long> substitutionIds = new ArrayList<>();

}
