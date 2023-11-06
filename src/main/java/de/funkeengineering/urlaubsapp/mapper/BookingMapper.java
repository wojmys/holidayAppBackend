package de.funkeengineering.urlaubsapp.mapper;

import de.funkeengineering.urlaubsapp.domain.Booking;
import de.funkeengineering.urlaubsapp.domain.Holiday;
import de.funkeengineering.urlaubsapp.domain.dto.BookingDto;
import de.funkeengineering.urlaubsapp.domain.dto.HolidayDto;
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
                .employeeId(booking.getEmployee().getId())
                .holiday(booking.getHoliday())
                .build();
    }

    public Booking mapBookingDtoToBooking(BookingDto bookingDto){
        return Booking.builder()
                .id(bookingDto.getId())
                .employee(employeeDbService.getEmployeeById(bookingDto.getEmployeeId()))
                .holiday(bookingDto.getHoliday())
                .build();
    }

    public List<BookingDto> mapToBookingDtoList(List<Booking>bookings){
        return bookings.stream()
                .map(this::mapBookingToBookingDto)
                .collect(Collectors.toList());
    }
}
