package de.funkeengineering.urlaubsapp.service;

import de.funkeengineering.urlaubsapp.domain.Booking;
import de.funkeengineering.urlaubsapp.domain.Employee;
import de.funkeengineering.urlaubsapp.error.BookingNotFoundException;
import de.funkeengineering.urlaubsapp.error.EmployeeNotFoundException;
import de.funkeengineering.urlaubsapp.repository.BookingRepository;
import de.funkeengineering.urlaubsapp.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeDbService {
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
    public Employee saveEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    public Employee getEmployeeById(Long id){
        return employeeRepository.findById(id).orElseThrow(()->new EmployeeNotFoundException(id));
    }

    public Employee updateEmployee(Long employeeId, Employee updatedEmployee){
        Employee existingEmployee = getEmployeeById(employeeId);

        existingEmployee.setName(updatedEmployee.getName());
        existingEmployee.setBookings(updatedEmployee.getBookings());
        existingEmployee.setRemainingHolidays(updatedEmployee.getRemainingHolidays());
        existingEmployee.setTotalHolidays(updatedEmployee.getTotalHolidays());



        return saveEmployee(existingEmployee);
    }
}
