package de.funkeengineering.urlaubsapp.mapper;

import de.funkeengineering.urlaubsapp.domain.Booking;
import de.funkeengineering.urlaubsapp.domain.dto.BookingDto;
import de.funkeengineering.urlaubsapp.service.EmployeeDbService;
import de.funkeengineering.urlaubsapp.service.StatusDbService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookingMapper {

    EmployeeDbService employeeDbService;
    StatusDbService statusDbService;


    public BookingDto mapBookingToBookingDto(Booking booking){
        return BookingDto.builder()
                .id(booking.getId())
                .startDate(booking.getStartDate())
                .endDate(booking.getEndDate())
                .quantityDays(booking.getQuantityDays())
                .statusId(booking.getStatus().getId())
                .employeeId(booking.getEmployee().getId())
                .substitutionId(booking.getSubstitution().getId())
                .build();
    }

    public Booking mapBookingDtoToBooking(BookingDto bookingDto){
        return Booking.builder()
                .id(bookingDto.getId())
                .startDate(bookingDto.getStartDate())
                .endDate(bookingDto.getEndDate())
                .quantityDays(bookingDto.getQuantityDays())
                .status(statusDbService.getStatusById(bookingDto.getStatusId()))
                .employee(employeeDbService.getEmployeeById(bookingDto.getEmployeeId()))
                .substitution(employeeDbService.getEmployeeById(bookingDto.getSubstitutionId()))
                .build();
    }

    public List<BookingDto> mapToBookingDtoList(List<Booking>bookings){
        return bookings.stream()
                .map(this::mapBookingToBookingDto)
                .collect(Collectors.toList());
    }
}
