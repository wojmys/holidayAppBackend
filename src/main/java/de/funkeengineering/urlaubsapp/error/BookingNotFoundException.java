package de.funkeengineering.urlaubsapp.error;

import lombok.Getter;

@Getter
public class BookingNotFoundException extends RuntimeException{
    private final Long id;

    public BookingNotFoundException(Long id) {
        super("BOOKING_ID not found");
        this.id = id;
    }

}
