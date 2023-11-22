package de.funkeengineering.urlaubsapp.config;

import de.funkeengineering.urlaubsapp.domain.Booking;
import de.funkeengineering.urlaubsapp.domain.Employee;
import de.funkeengineering.urlaubsapp.domain.Status;
import de.funkeengineering.urlaubsapp.domain.dto.BookingDto;
import de.funkeengineering.urlaubsapp.mapper.BookingMapper;
import de.funkeengineering.urlaubsapp.repository.BookingRepository;
import de.funkeengineering.urlaubsapp.repository.EmployeeRepository;
import de.funkeengineering.urlaubsapp.service.BookingDbService;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.CompositeTypeRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;

@Component
@Slf4j
public class StartupScript {

    private BookingRepository bookingRepository;

    private EmployeeRepository employeeRepository;

    @Autowired
    public StartupScript(BookingRepository bookingRepository,
                         EmployeeRepository employeeRepository) {
        this.bookingRepository = bookingRepository;
        this.employeeRepository = employeeRepository;

        log.info("Break");
        execute();
    }

    public void execute() {


        createEmployees();
        createBooking();

    }

    @Transactional
    public void createEmployees() {
        Employee employee1 = Employee.builder()
                .name("Wojciech")
                .totalHolidays(30)
                .bookings(new ArrayList<>())
                .substitutions(new ArrayList<>())
                .build();

        Employee employee2 = Employee.builder()
                .name("Andreas")
                .totalHolidays(30)
                .bookings(new ArrayList<>())
                .substitutions(new ArrayList<>())
                .build();


        log.info("Saving employee 1");
        employeeRepository.save(employee1);
        log.info("Saving employee 2");
        employeeRepository.save(employee2);


    }

    @Transactional
    public void createBooking() {
        var employee1 = employeeRepository.findById(1L).get();
        var employee2 = employeeRepository.findById(2L).get();


        Booking booking = Booking.builder()
                .employee(employee1)
                .substitution(employee2)
                .status(Status.IN_PROGRESS)
                .startDate(LocalDate.of(2023, 1, 1))
                .endDate(LocalDate.of(2023, 1, 2))
                .quantityDays(2.0)
                .build();


        log.info("Saving booking");
        bookingRepository.save(booking);

    }


}
