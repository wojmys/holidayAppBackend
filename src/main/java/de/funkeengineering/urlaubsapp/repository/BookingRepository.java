package de.funkeengineering.urlaubsapp.repository;

import de.funkeengineering.urlaubsapp.domain.Booking;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface BookingRepository extends CrudRepository <Booking, Long> {
    List<Booking>findAll();
}
