package de.funkeengineering.urlaubsapp.service;

import de.funkeengineering.urlaubsapp.domain.Employee;
import de.funkeengineering.urlaubsapp.error.EmployeeNotFoundException;
import de.funkeengineering.urlaubsapp.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeDbService {
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    public void deleteEmployeeById(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new EmployeeNotFoundException(id);
        }
        employeeRepository.deleteById(id);
    }

    public Employee updateEmployee(Long employeeId, Employee updatedEmployee) {
        Employee existingEmployee = getEmployeeById(employeeId);

        existingEmployee.setName(updatedEmployee.getName());
        existingEmployee.setBookings(updatedEmployee.getBookings());
        existingEmployee.setRemainingHolidays(updatedEmployee.getRemainingHolidays());
        existingEmployee.setTotalHolidays(updatedEmployee.getTotalHolidays());

        return saveEmployee(existingEmployee);
    }
}
