package de.funkeengineering.urlaubsapp.mapper;

import de.funkeengineering.urlaubsapp.domain.Booking;
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
                .bookingsId(mapToBookingIdList(employee.getBookings()))
                .substitutionsId(mapToSubstitutionIdList(employee.getSubstitutions()))
                .build();
    }

    public Employee mapEmployeeDtoToEmployee(EmployeeDto employeeDto) {
        return Employee.builder()
                .id(employeeDto.getId())
                .name(employeeDto.getName())
                .totalHolidays(employeeDto.getTotalHolidays())
                .remainingHolidays(employeeDto.getRemainingHolidays())
                .bookings(employeeDto.getBookingsId().stream()
                        .map(bookingId -> {
                            Booking booking = new Booking();
                            booking.setId(bookingId);
                            return booking;
                        })
                        .collect(Collectors.toList()))
                .substitutions(employeeDto.getSubstitutionsId().stream()
                        .map(substitutionId -> {
                            Booking booking = new Booking();
                            booking.setId(substitutionId);
                            return booking;
                        })
                        .collect(Collectors.toList()))
                .build();
    }

    public List<EmployeeDto> mapToEmployeeDtosList(List<Employee> employees) {
        return employees.stream()
                .map(this::mapEmployeeToEmployeeDto)
                .collect(Collectors.toList());
    }

    public List<Long> mapToBookingIdList(final List<Booking> bookingList) {
        return bookingList.stream()
                .map(Booking::getId)
                .collect(Collectors.toList());
    }

    public List<Long> mapToSubstitutionIdList(final List<Booking> substitutionList) {
        return substitutionList.stream()
                .map(Booking::getId)
                .collect(Collectors.toList());
    }
}
