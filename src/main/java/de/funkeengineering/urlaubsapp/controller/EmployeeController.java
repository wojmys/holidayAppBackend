package de.funkeengineering.urlaubsapp.controller;

import de.funkeengineering.urlaubsapp.domain.Booking;
import de.funkeengineering.urlaubsapp.domain.Employee;
import de.funkeengineering.urlaubsapp.domain.dto.BookingDto;
import de.funkeengineering.urlaubsapp.domain.dto.EmployeeDto;
import de.funkeengineering.urlaubsapp.mapper.BookingMapper;
import de.funkeengineering.urlaubsapp.mapper.EmployeeMapper;
import de.funkeengineering.urlaubsapp.service.BookingDbService;
import de.funkeengineering.urlaubsapp.service.EmployeeDbService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("api/employee")
public class EmployeeController {

    private EmployeeMapper employeeMapper;
    private EmployeeDbService employeeDbService;

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> fetchAllEmployees() {
        List<Employee> employees = employeeDbService.getAllEmployees();
        return ResponseEntity.ok(employeeMapper.mapToEmployeeDtosList(employees));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> fetchEmployeeById(@PathVariable Long id) {
        return ResponseEntity.ok(employeeMapper.mapEmployeeToEmployeeDto(employeeDbService.getEmployeeById(id)));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createEmployee(@RequestBody EmployeeDto employeeDto) {
        Employee employee = employeeMapper.mapEmployeeDtoToEmployee(employeeDto);
        employeeDbService.saveEmployee(employee);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeDbService.deleteEmployeeById(id);
        return ResponseEntity.ok().build();
    }

}
