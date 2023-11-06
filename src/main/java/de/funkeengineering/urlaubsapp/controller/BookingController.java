package de.funkeengineering.urlaubsapp.controller;

import de.funkeengineering.urlaubsapp.domain.Booking;
import de.funkeengineering.urlaubsapp.domain.dto.BookingDto;
import de.funkeengineering.urlaubsapp.mapper.BookingMapper;
import de.funkeengineering.urlaubsapp.service.BookingDbService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("api/booking")
public class BookingController {

    private BookingMapper bookingMapper;
    private BookingDbService bookingDbService;

    @GetMapping
    public ResponseEntity<List<BookingDto>> fetchAllBookings() {
        List<Booking> bookings = bookingDbService.getAllBookings();
        return ResponseEntity.ok(bookingMapper.mapToBookingDtoList(bookings));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingDto> fetchBookingById(@PathVariable Long id) {
        Booking booking = bookingDbService.getBookingById(id);
        return ResponseEntity.ok(bookingMapper.mapBookingToBookingDto(booking));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createBooking(@RequestBody BookingDto bookingDto) {
        Booking booking = bookingMapper.mapBookingDtoToBooking(bookingDto);
        bookingDbService.saveBooking(booking);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        bookingDbService.deleteBookingById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookingDto> updateBooking(@PathVariable Long id, @RequestBody BookingDto bookingDto){
        Booking updatedBooking = bookingMapper.mapBookingDtoToBooking(bookingDto);
        Booking savedBooking = bookingDbService.updateBooking(id,updatedBooking);
        return ResponseEntity.ok(bookingMapper.mapBookingToBookingDto(savedBooking));
    }

}
