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
    private String substitute;
    private int totalHolidays;
    private int remainingHolidays;

    @OneToMany(
            targetEntity = Booking.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            mappedBy = "employee")
    private List<Booking> employeeBookings = new ArrayList<>();

}
