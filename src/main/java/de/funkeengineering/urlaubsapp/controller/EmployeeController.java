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
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {
        Employee employee = employeeMapper.mapEmployeeDtoToEmployee(employeeDto);
        employee = employeeDbService.saveEmployee(employee);
        log.info("Employee created");
        return ResponseEntity.ok(employeeMapper.mapEmployeeToEmployeeDto(employee));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeDbService.deleteEmployeeById(id);
        log.info("Employee with id="+ id+" deleted");
        return ResponseEntity.ok().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto>updateEmployee(@PathVariable Long id, @RequestBody EmployeeDto employeeDto){
        Employee updatedEmployee = employeeMapper.mapEmployeeDtoToEmployee(employeeDto);
        Employee savedEmployee = employeeDbService.updateEmployee(id,updatedEmployee);
        return ResponseEntity.ok(employeeMapper.mapEmployeeToEmployeeDto(savedEmployee));
    }

}
