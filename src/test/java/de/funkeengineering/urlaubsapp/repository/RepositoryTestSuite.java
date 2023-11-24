package de.funkeengineering.urlaubsapp.repository;

import de.funkeengineering.urlaubsapp.domain.Booking;
import de.funkeengineering.urlaubsapp.domain.Employee;
import de.funkeengineering.urlaubsapp.domain.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RepositoryTestSuite {

    @Autowired
    private StatusRepository statusRepository;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private EmployeeRepository employeeRepository;


    private Booking booking;
    private Employee employee;
    private Employee substitution;
    private Status status;

    @BeforeEach
    void setUp() {

        status = Status.builder()
                .id(1L)
                .name("In progress")
                .build();

        employee = Employee.builder()
                .id(1L)
                .name("John Smith")
                .totalHolidays(30)
                .remainingHolidays(25)
//                .bookings(new ArrayList<>())
//                .substitutions(new ArrayList<>())
                .build();

        substitution = Employee.builder()
                .id(2L)
                .name("Jack Mason")
                .totalHolidays(20)
                .remainingHolidays(5)
//                .bookings(new ArrayList<>())
//                .substitutions(new ArrayList<>())
                .build();

        statusRepository.save(status);
        employeeRepository.save(employee);
        employeeRepository.save(substitution);

        booking = Booking.builder()
                .id(1L)
                .startDate(LocalDate.of(2023, 11, 06))
                .endDate(LocalDate.of(2023, 11, 07))
                .quantityDays(1)
                .status(status)
                .employee(employee)
                .substitution(substitution)
                .build();

        bookingRepository.save(booking);
    }
    @Test
    void shouldCreateEmployees (){
        //then
        assertNotNull(employee);
        assertNotNull(substitution);
    }

    @Test
    void shouldCreateBooking() {
        //then
        assertNotNull(booking);
    }

    @Test
    void shouldCreateStatus() {
        //then
        assertNotNull(status);
    }

    @Test
    void shouldFetchBookingById() {
        //when
        Optional<Booking> bookingById = bookingRepository.findById(booking.getId());
        Optional<Employee> employeeById = employeeRepository.findById(employee.getId());
        Optional<Employee> substistuitionById = employeeRepository.findById(substitution.getId());
        //then
        assertEquals(1, bookingById.get().getQuantityDays());
        assertEquals("John Smith", bookingById.get().getEmployee().getName());
        assertEquals("Jack Mason", bookingById.get().getSubstitution().getName());
        //assertEquals(1, bookingById.get().getEmployee().getBookings().size());
//        assertEquals(0, employeeById.get().getSubstitutions().size());
//        assertEquals(1, substistuitionById.get().getSubstitutions().size());
    }

    @Test
    void shouldFetchAllBookings() {
        //given
        Employee secondEmployee = Employee.builder()
                .id(5L)
                .name("Alan Anderson")
//                .substitutions(new ArrayList<>())
//                .bookings(new ArrayList<>())
                .totalHolidays(30)
                .remainingHolidays(29)
                .build();

        Employee secondSubstitution = Employee.builder()
                .id(6L)
                .name("Martin Schmitt")
//                .substitutions(new ArrayList<>())
//                .bookings(new ArrayList<>())
                .totalHolidays(30)
                .remainingHolidays(29)
                .build();
        employeeRepository.save(secondEmployee);
        employeeRepository.save(secondSubstitution);

        Booking secondBooking = Booking.builder()
                .id(3L)
                .startDate(LocalDate.of(2023, 11, 13))
                .endDate(LocalDate.of(2023, 11, 15))
                .status(status)
              //  .status(Status.IN_PROGRESS)
                .quantityDays(2)
//                .employee(secondEmployee)
//                .substitution(secondSubstitution)
                .build();
        var savedBooking = bookingRepository.save(secondBooking);
        assertNotEquals(3L, savedBooking);

        //when
        List<Booking> bookingList = bookingRepository.findAll();
        //then
        assertEquals(2, bookingList.size());

        assertEquals(1, bookingList.stream().filter(x -> x.getId().equals(2L)).collect(Collectors.toList()).size());

        bookingRepository.deleteById(2L);
        bookingList = bookingRepository.findAll();
        assertEquals(1, bookingList.size());

    }

    @Test
    void shouldFetchAllEmployees() {
        //given
        Employee secondEmployee = Employee.builder()
                .id(7L)
                .name("Alan Anderson")
//                .substitutions(new ArrayList<>())
//                .bookings(new ArrayList<>())
                .totalHolidays(30)
                .remainingHolidays(29)
                .build();
        //when
        employeeRepository.save(secondEmployee);
        List<Employee>employeeList = employeeRepository.findAll();
        //then
        assertEquals(3,employeeList.size());   //employee+substitution+secondEmployee
    }

}
