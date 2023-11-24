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
public class Status {
    //    APPROVED,
//    REJECTED,
//    IN_PROGRESS

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

//    @OneToMany(
//            mappedBy = "status"
//    )
//    private List<Booking> bookings;

}
