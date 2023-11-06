package de.funkeengineering.urlaubsapp.service;

import de.funkeengineering.urlaubsapp.domain.Booking;
import de.funkeengineering.urlaubsapp.domain.Holiday;
import de.funkeengineering.urlaubsapp.error.BookingNotFoundException;
import de.funkeengineering.urlaubsapp.error.HolidayNotFoundException;
import de.funkeengineering.urlaubsapp.repository.BookingRepository;
import de.funkeengineering.urlaubsapp.repository.HolidayRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookingDbService {

    private BookingRepository bookingRepository;

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
    public Booking saveBooking(Booking booking){
        return bookingRepository.save(booking);
    }

    public Booking getBookingById(Long id){
        return bookingRepository.findById(id).orElseThrow(()->new BookingNotFoundException(id));
    }

    public Booking updateBooking(Long bookingId, Booking updatedBooking){
        Booking existingBooking = getBookingById(bookingId);

        existingBooking.setEmployee(updatedBooking.getEmployee());
        existingBooking.setHoliday(updatedBooking.getHoliday());

        return saveBooking(existingBooking);
    }

}
