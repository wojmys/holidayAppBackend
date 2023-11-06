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
public class Holiday {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;

    @Enumerated
    private Status status;

    @OneToOne(mappedBy = "holiday")
    private Booking booking;

    public Holiday(Long id, LocalDate startDate, LocalDate endDate, Booking booking) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = Status.IN_PROGRESS;
        this.booking = booking;
    }
}
