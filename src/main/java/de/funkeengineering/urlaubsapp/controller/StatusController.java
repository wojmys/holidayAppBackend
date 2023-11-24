package de.funkeengineering.urlaubsapp.controller;

import de.funkeengineering.urlaubsapp.domain.Employee;
import de.funkeengineering.urlaubsapp.domain.Status;
import de.funkeengineering.urlaubsapp.domain.dto.EmployeeDto;
import de.funkeengineering.urlaubsapp.service.StatusDbService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@CrossOrigin("*")
@RequestMapping("api/status")
public class StatusController {

    private StatusDbService statusDbService;

    @GetMapping
    public ResponseEntity <List<Status>> fetchAllStatuses() {
        List<Status> statuses = statusDbService.getAllStatuses();
        return ResponseEntity.ok(statuses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Status> fetchStatusById(@PathVariable Long id) {
        log.info("Get Status by Id {}", id);
        return ResponseEntity.ok(statusDbService.getStatusById(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Status> createStatus(@RequestBody Status status) {
        log.info("Status created");
        return ResponseEntity.ok(statusDbService.saveStatus(status));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStatus(@PathVariable Long id) {
        statusDbService.deleteStatusById(id);
        log.info("Status with id=" + id + " deleted");
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Status> updateStatus (@PathVariable Long id, @RequestBody Status status){
        Status updatedStatus = status;
        Status savedStatus = statusDbService.updateStatus(id, updatedStatus);
        return ResponseEntity.ok(savedStatus);
    }

//    @GetMapping
//    public ResponseEntity<List<Status>> fetchAllStatuses() {
//        List<String> listOfStatuses = enumToList(Status.class);
//       // log.info(listOfStatuses.toString());
//        return ResponseEntity.ok(listOfStatuses);
//    }
//    private static <T extends Enum<T>> List<String> enumToList(Class<T> enumClass) {
//        List<String> enumStringList = new ArrayList<>();
//        T[] enumConstants = enumClass.getEnumConstants();
//
//        for (T enumValue : enumConstants) {
//            enumStringList.add(enumValue.name());
//        }
//
//        return enumStringList;
//    }

}
