package de.funkeengineering.urlaubsapp.mapper;

import de.funkeengineering.urlaubsapp.domain.Employee;
import de.funkeengineering.urlaubsapp.domain.dto.EmployeeDto;
import de.funkeengineering.urlaubsapp.service.EmployeeDbService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class EmployeeMapper {

    private BookingMapper bookingMapper;

    public EmployeeDto mapEmployeeToEmployeeDto(Employee employee) {
        return EmployeeDto.builder()
                .id(employee.getId())
                .name(employee.getName())
                .totalHolidays(employee.getTotalHolidays())
                .remainingHolidays(employee.getRemainingHolidays())
                .bookings(employee.getBookings().stream()
                        .map(bookingMapper::mapBookingToBookingDto)
                        .collect(Collectors.toList())
                )
                .build();
    }

    public Employee mapEmployeeDtoToEmployee(EmployeeDto employeeDto) {
        return Employee.builder()
                .id(employeeDto.getId())
                .name(employeeDto.getName())
                .totalHolidays(employeeDto.getTotalHolidays())
                .remainingHolidays(employeeDto.getRemainingHolidays())
                .bookings(employeeDto.getBookings().stream()
                        .map(bookingMapper::mapBookingDtoToBooking)
                        .collect(Collectors.toList())
                )
                .build();
    }

    public List<EmployeeDto> mapToEmployeeDtosList(List<Employee>employees){
        return employees.stream()
                .map(this::mapEmployeeToEmployeeDto)
                .collect(Collectors.toList());
    }
}
