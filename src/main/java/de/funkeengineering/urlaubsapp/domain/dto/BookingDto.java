package de.funkeengineering.urlaubsapp.domain.dto;

import de.funkeengineering.urlaubsapp.domain.Employee;
import de.funkeengineering.urlaubsapp.domain.Holiday;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class BookingDto {

    private Long id;
    private LocalDate dateNow;
    private Holiday holiday;
    private Long employeeId;

    public BookingDto(Long id, Holiday holiday, Long employeeId) {
        this.id = id;
        this.dateNow = LocalDate.now();
        this.holiday = holiday;
        this.employeeId = employeeId;
    }
}
