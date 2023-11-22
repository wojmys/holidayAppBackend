package de.funkeengineering.urlaubsapp.mapper;

import de.funkeengineering.urlaubsapp.domain.Booking;
import de.funkeengineering.urlaubsapp.domain.Employee;
import de.funkeengineering.urlaubsapp.domain.Status;
import de.funkeengineering.urlaubsapp.domain.dto.BookingDto;
import de.funkeengineering.urlaubsapp.service.BookingDbService;
import de.funkeengineering.urlaubsapp.service.EmployeeDbService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookingMapperTestSuite {

    @Autowired
    BookingMapper bookingMapper;
    @Autowired
    BookingDbService bookingDbService;
    @Autowired
    EmployeeDbService employeeDbService;

    @Test
    void mapToBookingTest() {
        //given
        Employee employee = Employee.builder()
//                .id(1L)
                .totalHolidays(10)
                .remainingHolidays(10)
                .bookings(new ArrayList<>())
                .substitutions(new ArrayList<>())
                .name("Joe Doe")
                .build();

        Employee substitute = Employee.builder()
//                .id(2L)
                .totalHolidays(10)
                .remainingHolidays(10)
                .bookings(new ArrayList<>())
                .substitutions(new ArrayList<>())
                .name("Charlie Sheen")
                .build();
        employee = employeeDbService.saveEmployee(employee);
        substitute = employeeDbService.saveEmployee(substitute);

        BookingDto bookingDto = BookingDto.builder()
                .id(10L)
                .startDate(LocalDate.of(2023, 11, 13))
                .endDate(LocalDate.of(2023, 11, 16))
                .quantityDays(4)
                .status(Status.APPROVED)
                .employeeId(employee.getId())
                .substitutionId(substitute.getId())
                .build();
        //when
        Booking booking = bookingMapper.mapBookingDtoToBooking(bookingDto);
        //then
        assertNotNull(booking);
        assertEquals(10L, booking.getId());
        assertEquals(LocalDate.of(2023, 11, 13), booking.getStartDate());
        assertEquals(LocalDate.of(2023, 11, 16), booking.getEndDate());
        assertEquals(4, booking.getQuantityDays());
        assertEquals(Status.APPROVED, booking.getStatus());
        assertEquals(employee.getId(), booking.getEmployee().getId());
        assertEquals(substitute.getId(), booking.getSubstitution().getId());
    }

    @Test
    void mapToBookingDtoTest() {

        //given
        Employee employee = Employee.builder()
                .id(1L)
                .totalHolidays(10)
                .remainingHolidays(10)
                .bookings(new ArrayList<>())
                .substitutions(new ArrayList<>())
                .name("Joe Doe")
                .build();

        Employee substitute = Employee.builder()
                .id(2L)
                .totalHolidays(10)
                .remainingHolidays(10)
                .bookings(new ArrayList<>())
                .substitutions(new ArrayList<>())
                .name("Charlie Sheen")
                .build();
        employeeDbService.saveEmployee(employee);
        employeeDbService.saveEmployee(substitute);

        Booking booking = Booking.builder()
                .id(99L)
                .employee(employee)
                .startDate(LocalDate.of(2024, 01, 02))
                .endDate(LocalDate.of(2024, 01, 05))
                .substitution(substitute)
                .quantityDays(4)
                .status(Status.IN_PROGRESS)
                .build();

        // when
        BookingDto bookingDto = bookingMapper.mapBookingToBookingDto(booking);

        //then
        assertNotNull(bookingDto);
        assertEquals(99L, bookingDto.getId());
        assertEquals("Joe Doe", employeeDbService.getEmployeeById(bookingDto.getEmployeeId()).getName());
        assertEquals("Charlie Sheen", employeeDbService.getEmployeeById(bookingDto.getSubstitutionId()).getName());
    }

    @Test
    void mapToBookingDtoList() {
        //given
        Employee employee = Employee.builder()
                .id(1L)
                .totalHolidays(10)
                .remainingHolidays(10)
                .bookings(new ArrayList<>())
                .substitutions(new ArrayList<>())
                .name("Joe Doe")
                .build();

        Employee substitute = Employee.builder()
                .id(2L)
                .totalHolidays(10)
                .remainingHolidays(10)
                .bookings(new ArrayList<>())
                .substitutions(new ArrayList<>())
                .name("Charlie Sheen")
                .build();
        employeeDbService.saveEmployee(employee);
        employeeDbService.saveEmployee(substitute);

        Booking booking = Booking.builder()
                .id(99L)
                .employee(employee)
                .startDate(LocalDate.of(2024, 01, 02))
                .endDate(LocalDate.of(2024, 01, 05))
                .substitution(substitute)
                .quantityDays(4)
                .status(Status.IN_PROGRESS)
                .build();

        Booking secondBooking = Booking.builder()
                .id(98L)
                .employee(substitute)
                .startDate(LocalDate.of(2024, 01, 9))
                .endDate(LocalDate.of(2024, 01, 12))
                .substitution(employee)
                .quantityDays(4)
                .status(Status.IN_PROGRESS)
                .build();

        List<Booking> bookingList = new ArrayList<>();
        bookingList.add(booking);
        bookingList.add(secondBooking);

        //when
        List<BookingDto> bookingDtos = bookingMapper.mapToBookingDtoList(bookingList);

        //then
        assertNotNull(bookingDtos);
        assertEquals(2,bookingDtos.size());
        assertEquals(99,bookingDtos.get(0).getId());
        assertEquals(98,bookingDtos.get(1).getId());
    }

}
