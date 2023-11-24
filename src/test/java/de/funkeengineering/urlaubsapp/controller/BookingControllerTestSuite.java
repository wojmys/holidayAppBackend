//package de.funkeengineering.urlaubsapp.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
//import de.funkeengineering.urlaubsapp.domain.Booking;
//import de.funkeengineering.urlaubsapp.domain.Employee;
//import de.funkeengineering.urlaubsapp.domain.Status;
//import de.funkeengineering.urlaubsapp.domain.dto.BookingDto;
//import de.funkeengineering.urlaubsapp.domain.dto.EmployeeDto;
//import de.funkeengineering.urlaubsapp.mapper.BookingMapper;
//import de.funkeengineering.urlaubsapp.mapper.EmployeeMapper;
//import de.funkeengineering.urlaubsapp.service.BookingDbService;
//import de.funkeengineering.urlaubsapp.service.EmployeeDbService;
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringJUnitWebConfig
//@WebMvcTest(BookingController.class)
//class BookingControllerTestSuite {
//
//    @Autowired
//    private MockMvc mockMvc;
//    @MockBean
//    private BookingMapper bookingMapper;
//    @MockBean
//    private BookingDbService bookingDbService;
//    @MockBean
//    private EmployeeMapper employeeMapper;
//    @MockBean
//    private EmployeeDbService employeeDbService;
//
//
//
//    @Disabled
//    @Test
//    void createBookingTest() throws Exception {
//
//        //given
//        Employee employee = createNewEmployee();
//        Employee substitution = createNewSubstitution();
//
//        EmployeeDto employeeDto = createNewEmployeeDto();
//        EmployeeDto substitutionDto = createNewSubstitutionDto();
//
//        Booking booking = Booking.builder()
//                .id(22L)
//                .employee(employee)
//                .substitution(substitution)
//                .startDate(LocalDate.of(2023,01,02))
//                .endDate(LocalDate.of(2023,01,05))
//                .status(Status.builder().name("In progress").build())
//                //.status(Status.IN_PROGRESS)
//                .quantityDays(4)
//                .build();
//        BookingDto bookingDto = BookingDto.builder()
//                .id(22L)
//                .employeeId(1L)
//                .substitutionId(2L)
//                .startDate(LocalDate.of(2023,01,02))
//                .endDate(LocalDate.of(2023,01,05))
//                .status(Status.builder().name("In progress").build())
//                //.status(Status.IN_PROGRESS)
//                .quantityDays(4)
//                .build();
//        when(bookingMapper.mapBookingDtoToBooking(bookingDto)).thenReturn(booking);
//        when(bookingDbService.saveBooking(booking)).thenReturn(booking);
//        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
//        String jsonContent = mapper.writeValueAsString(bookingDto);
//
//        //when & then
//        mockMvc.perform(post("/api/booking")
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .characterEncoding("UTF-8")
//                .content(jsonContent))
//                .andExpect(status().isOk());
//
//
//
//
//    }
//
//    private Employee createNewEmployee(){
//        return Employee.builder()
//                .id(1L)
//                .totalHolidays(10)
//                .remainingHolidays(10)
//                .bookings(new ArrayList<>())
//                .substitutions(new ArrayList<>())
//                .name("Joe Doe")
//                .build();
//    }
//    private EmployeeDto createNewEmployeeDto(){
//        return EmployeeDto.builder()
//                .id(1L)
//                .totalHolidays(10)
//                .remainingHolidays(10)
//                .bookingIds(new ArrayList<>())
//                .substitutionIds(new ArrayList<>())
//                .name("Joe Doe")
//                .build();
//    }
//
//    private EmployeeDto createNewSubstitutionDto(){
//        return EmployeeDto.builder()
//                .id(2L)
//                .totalHolidays(10)
//                .remainingHolidays(10)
//                .bookingIds(new ArrayList<>())
//                .substitutionIds(new ArrayList<>())
//                .name("John Smith")
//                .build();
//    }
//    private Employee createNewSubstitution(){
//        return Employee.builder()
//                .id(2L)
//                .totalHolidays(10)
//                .remainingHolidays(10)
//                .bookings(new ArrayList<>())
//                .substitutions(new ArrayList<>())
//                .name("John Smith")
//                .build();
//    }
//
//}
