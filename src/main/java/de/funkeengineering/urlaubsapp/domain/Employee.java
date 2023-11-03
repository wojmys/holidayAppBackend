package de.funkeengineering.urlaubsapp.domain;

import jakarta.persistence.*;
import lombok.*;


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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "BOOKING_ID")
    private Booking booking;

}
