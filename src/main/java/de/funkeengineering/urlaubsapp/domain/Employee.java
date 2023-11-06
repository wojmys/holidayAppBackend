package de.funkeengineering.urlaubsapp.domain;

import jakarta.persistence.*;
import lombok.*;

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
    private String substitute;
    private int totalHolidays;
    private int remainingHolidays;

    @OneToMany(
            targetEntity = Booking.class,
            mappedBy = "employee",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private List<Booking> bookings;

}
