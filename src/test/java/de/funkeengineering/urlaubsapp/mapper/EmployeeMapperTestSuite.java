package de.funkeengineering.urlaubsapp.mapper;

import de.funkeengineering.urlaubsapp.domain.Employee;
import de.funkeengineering.urlaubsapp.domain.dto.EmployeeDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class EmployeeMapperTestSuite {

    @Autowired
    EmployeeMapper employeeMapper;

    @Test
    void mapToEmployeeDtoTest() {
        //given
        Employee employee = Employee.builder()
                .id(1L)
                .totalHolidays(10)
                .remainingHolidays(10)
//                .bookings(new ArrayList<>())
//                .substitutions(new ArrayList<>())
                .name("Joe Doe")
                .build();
        //when
        EmployeeDto employeeDto = employeeMapper.mapEmployeeToEmployeeDto(employee);
        //then
        assertNotNull(employeeDto);
        assertEquals(1L, employeeDto.getId());
        assertEquals(10, employeeDto.getTotalHolidays());
        assertEquals(10, employeeDto.getRemainingHolidays());
        assertEquals(0, employeeDto.getBookingIds().size());
        assertEquals(0, employeeDto.getSubstitutionIds().size());
        assertEquals("Joe Doe", employeeDto.getName());
    }

    @Test
    void mapToEmployeeTest() {
        //given
        EmployeeDto employeeDto = EmployeeDto.builder()
                .id(1L)
                .name("John Smith")
                .totalHolidays(5)
                .bookingIds(new ArrayList<>())
                .substitutionIds(new ArrayList<>())
                .remainingHolidays(5)
                .build();
        employeeDto.getBookingIds().add(1L);
        //when
        Employee employee = employeeMapper.mapEmployeeDtoToEmployee(employeeDto);
        //then
        assertNotNull(employee);
        assertEquals(1L, employee.getId());
        assertEquals("John Smith", employee.getName());
        assertEquals(5, employee.getTotalHolidays());
        assertEquals(5, employee.getRemainingHolidays());
//        assertEquals(1, employee.getBookings().size());
    }


    @Test
    void mapToEmployeeDtoListTest() {
        //given
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(Employee.builder()
                .id(1L)
                .totalHolidays(10)
                .remainingHolidays(10)
//                .bookings(new ArrayList<>())
//                .substitutions(new ArrayList<>())
                .name("Joe Doe")
                .build()
        );
        employeeList.add(Employee.builder()
                .id(2L)
                .totalHolidays(11)
                .remainingHolidays(5)
//                .bookings(new ArrayList<>())
//                .substitutions(new ArrayList<>())
                .name("Sandra Lee")
                .build());
        //when
        List<EmployeeDto>employeeDtos = employeeMapper.mapToEmployeeDtosList(employeeList);
        //then
        assertNotNull(employeeDtos);
        assertEquals(2,employeeDtos.size());
        assertEquals("Sandra Lee", employeeDtos.get(1).getName());
        assertEquals(10, employeeDtos.get(0).getRemainingHolidays());
        assertEquals(2L,employeeDtos.get(1).getId());
    }


}
