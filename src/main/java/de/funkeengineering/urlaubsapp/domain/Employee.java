package de.funkeengineering.urlaubsapp.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int totalHolidays;
    private int remainingHolidays;

    @OneToMany(
            mappedBy = "employee"
    )
    private List<Booking> bookings;

    @OneToMany(
            mappedBy = "substitution"
    )
    private List <Booking> substitutions;
}
