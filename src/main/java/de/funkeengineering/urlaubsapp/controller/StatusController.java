package de.funkeengineering.urlaubsapp.controller;

import de.funkeengineering.urlaubsapp.domain.Status;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@CrossOrigin("*")
@RequestMapping("api/status")
public class StatusController {

    @GetMapping
    public ResponseEntity<List<String>> fetchAllStatuses() {
        List<String> listOfStatuses = enumToList(Status.class);
       // log.info(listOfStatuses.toString());
        return ResponseEntity.ok(listOfStatuses);
    }
    private static <T extends Enum<T>> List<String> enumToList(Class<T> enumClass) {
        List<String> enumStringList = new ArrayList<>();
        T[] enumConstants = enumClass.getEnumConstants();

        for (T enumValue : enumConstants) {
            enumStringList.add(enumValue.name());
        }

        return enumStringList;
    }

}
