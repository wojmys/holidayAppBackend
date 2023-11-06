package de.funkeengineering.urlaubsapp.error;

import lombok.Getter;

@Getter
public class HolidayNotFoundException extends RuntimeException{
    private final Long id;

    public HolidayNotFoundException(Long id) {
        super("Holiday not found");
        this.id = id;
    }
}
