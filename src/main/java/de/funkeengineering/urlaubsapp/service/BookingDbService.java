package de.funkeengineering.urlaubsapp.service;

import de.funkeengineering.urlaubsapp.domain.Booking;
import de.funkeengineering.urlaubsapp.error.BookingNotFoundException;
import de.funkeengineering.urlaubsapp.repository.BookingRepository;
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

    public Booking saveBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id).orElseThrow(() -> new BookingNotFoundException(id));
    }

    public void deleteBookingById(Long id) {
        if (!bookingRepository.existsById(id)) {
            throw new BookingNotFoundException(id);
        }
            bookingRepository.deleteById(id);
    }

    public Booking updateBooking(Long bookingId, Booking updatedBooking) {
        Booking existingBooking = getBookingById(bookingId);

        existingBooking.setStartDate(updatedBooking.getStartDate());
        existingBooking.setEndDate(updatedBooking.getEndDate());
        existingBooking.setQuantityDays(updatedBooking.getQuantityDays());
        existingBooking.setStatus(updatedBooking.getStatus());
        existingBooking.setEmployee(updatedBooking.getEmployee());
        existingBooking.setSubstitution(updatedBooking.getSubstitution());

        return saveBooking(existingBooking);
    }

    public List<Booking> getBookingByEmployeeId(Long employeeId) {
        return bookingRepository.findAllByEmployeeId(employeeId);
    }

    public List<Booking> getBookingBySubstitutionId(Long employeeId) {
        return bookingRepository.findAllBySubstitutionId(employeeId);
    }
}
