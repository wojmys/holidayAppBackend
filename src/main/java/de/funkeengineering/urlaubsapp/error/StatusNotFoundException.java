package de.funkeengineering.urlaubsapp.error;

import lombok.Getter;

@Getter
public class StatusNotFoundException extends RuntimeException{
    private final Long id;
    public StatusNotFoundException(Long id) {
        super("Status_ID not found");
        this.id = id;
    }
}
