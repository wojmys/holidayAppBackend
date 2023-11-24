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
    private LocalDate startDate;
    private LocalDate endDate;
    private double quantityDays;


    @ManyToOne
    @JoinColumn(name = "STATUS")
    private Status status;

    @ManyToOne()
    @JoinColumn(name = "EMPLOYEE")
    private Employee employee;

    @ManyToOne()
    @JoinColumn(name = "SUBSTITUTION")
    private Employee substitution;

}
