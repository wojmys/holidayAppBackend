package de.funkeengineering.urlaubsapp.error;

import de.funkeengineering.urlaubsapp.domain.Holiday;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalHttpErrorHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(HolidayNotFoundException.class)
    public ResponseEntity<Object> handleHolidayNotFoundException(HolidayNotFoundException holidayNotFoundException) {
        return new ResponseEntity<>("Holiday with given id doesn't exist", HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<Object> handleEmployeeNotFoundException(EmployeeNotFoundException employeeNotFoundException) {
        return new ResponseEntity<>("Employee with given id doesn't exist", HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(BookingNotFoundException.class)
    public ResponseEntity<Object> handleBookingNotFoundException(BookingNotFoundException bookingNotFoundException) {
        return new ResponseEntity<>("Booking with given id doesn't exist", HttpStatus.BAD_REQUEST);
    }
}
