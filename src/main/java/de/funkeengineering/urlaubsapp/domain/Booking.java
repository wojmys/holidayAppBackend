package de.funkeengineering.urlaubsapp.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dateNow;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "HOLIDAY_ID")
    private Holiday holiday;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "EMPLOYEE_ID")
    private Employee employee;

    public Booking(LocalDate dateNow, Holiday holiday, Employee employee) {
        this.dateNow = LocalDate.now();
        this.holiday = holiday;
        this.employee = employee;
    }
}
