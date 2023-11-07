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
            targetEntity = Booking.class,
            mappedBy = "employee",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private List<Booking> bookings=new ArrayList<>();
    @OneToMany(
            targetEntity = Booking.class,
            mappedBy = "substitution",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private List <Booking> substitutions=new ArrayList<>();
}
