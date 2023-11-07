package de.funkeengineering.urlaubsapp.domain.dto;

import de.funkeengineering.urlaubsapp.domain.Employee;
import de.funkeengineering.urlaubsapp.domain.Status;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    private LocalDate startDate;
    private LocalDate endDate;
    private double quantityDays;
    private Status status;
//    private Employee employee;
    private Long employeeId;
//    private Employee substitution;
    private Long substitutionId;

}
