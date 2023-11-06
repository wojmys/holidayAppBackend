package de.funkeengineering.urlaubsapp.mapper;

import de.funkeengineering.urlaubsapp.domain.Booking;
import de.funkeengineering.urlaubsapp.domain.dto.BookingDto;
import de.funkeengineering.urlaubsapp.service.EmployeeDbService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookingMapper {

    EmployeeDbService employeeDbService;

    public BookingDto mapBookingToBookingDto(Booking booking){
        return BookingDto.builder()
                .id(booking.getId())
                .startDate(booking.getStartDate())
                .endDate(booking.getEndDate())
                .quantityDays(booking.getQuantityDays())
                .status(booking.getStatus())
                .employee(booking.getEmployee())
                .employee(booking.getSubstitution())
                .build();
    }

    public Booking mapBookingDtoToBooking(BookingDto bookingDto){
        return Booking.builder()
                .id(bookingDto.getId())
                .startDate(bookingDto.getStartDate())
                .endDate(bookingDto.getEndDate())
                .quantityDays(bookingDto.getQuantityDays())
                .status(bookingDto.getStatus())
                .employee(bookingDto.getEmployee())
                .employee(bookingDto.getSubstitution())
                .build();
    }

    public List<BookingDto> mapToBookingDtoList(List<Booking>bookings){
        return bookings.stream()
                .map(this::mapBookingToBookingDto)
                .collect(Collectors.toList());
    }
}
