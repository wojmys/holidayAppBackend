package de.funkeengineering.urlaubsapp.controller;

import de.funkeengineering.urlaubsapp.domain.Booking;
import de.funkeengineering.urlaubsapp.domain.dto.BookingDto;
import de.funkeengineering.urlaubsapp.mapper.BookingMapper;
import de.funkeengineering.urlaubsapp.service.BookingDbService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@CrossOrigin("*")
@RequestMapping("api/booking")
public class BookingController {

    private BookingMapper bookingMapper;
    private BookingDbService bookingDbService;

    @Operation(summary = "Fetch all existing bookings",
            description = "Hier kommt die lange genaue Beschreibung rein")
    @GetMapping
    public ResponseEntity<List<BookingDto>> fetchAllBookings() {
        List<Booking> bookings = bookingDbService.getAllBookings();
        return ResponseEntity.ok(bookingMapper.mapToBookingDtoList(bookings));
    }

    @Operation(summary = "Fetch booking by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the booking", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = BookingDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Booking not found", content = @Content)})
    @GetMapping(value = "/{id}")
    public ResponseEntity<BookingDto> fetchBookingById(@Parameter(description = "id of booking to be searched")
                                                       @PathVariable Long id) {
        Booking booking = bookingDbService.getBookingById(id);
        return ResponseEntity.ok(bookingMapper.mapBookingToBookingDto(booking));
    }

    @Operation(summary = "Create new booking")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createBooking(@RequestBody BookingDto bookingDto) {
        Booking booking = bookingMapper.mapBookingDtoToBooking(bookingDto);
        bookingDbService.saveBooking(booking);
        log.info("Booking created");
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Delete booking by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Booking successfully deleted", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = BookingDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Booking not found", content = @Content)})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(
                                              @PathVariable Long id) {
        bookingDbService.deleteBookingById(id);
        log.info("Booking with id=" + id + " deleted");
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Update booking by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Booking successfully updated", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = BookingDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Booking not found", content = @Content)})
    @PutMapping("/{id}")
    public ResponseEntity<BookingDto> updateBooking(@Parameter(description = "id of booking to be updated")
                                                    @PathVariable Long id, @RequestBody BookingDto bookingDto) {
        Booking updatedBooking = bookingMapper.mapBookingDtoToBooking(bookingDto);
        Booking savedBooking = bookingDbService.updateBooking(id, updatedBooking);
        return ResponseEntity.ok(bookingMapper.mapBookingToBookingDto(savedBooking));
    }
}
