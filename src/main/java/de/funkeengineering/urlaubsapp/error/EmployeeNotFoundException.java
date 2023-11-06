package de.funkeengineering.urlaubsapp.error;

import lombok.Getter;

@Getter
public class EmployeeNotFoundException extends RuntimeException {

    private final Long id;
    public EmployeeNotFoundException(Long id) {
        super("EMPLOYEE_ID not found");
        this.id = id;
    }
}
