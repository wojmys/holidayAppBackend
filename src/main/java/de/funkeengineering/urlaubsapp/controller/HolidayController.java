package de.funkeengineering.urlaubsapp.controller;

import de.funkeengineering.urlaubsapp.domain.Holiday;
import de.funkeengineering.urlaubsapp.domain.dto.HolidayDto;
import de.funkeengineering.urlaubsapp.mapper.HolidayMapper;
import de.funkeengineering.urlaubsapp.service.HolidayDbService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("api/holiday")
public class HolidayController {

    private HolidayMapper holidayMapper;
    private HolidayDbService holidayDbService;
    @GetMapping
    public ResponseEntity<List<HolidayDto>> fetchAllHolidays() {
        log.info("Fetching all holidays");
        List<Holiday>holidays = holidayDbService.getAllHolidays();
        return ResponseEntity.ok(holidayMapper.mapToHolidayDtoList(holidays));
    }
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void>createHoliday(@RequestBody HolidayDto holidayDto){
        log.info("Creating holiday..");
        holidayDbService.saveHoliday(holidayMapper.mapHolidayDtoToHoliday(holidayDto));
        log.info("Successfully created!");
        return ResponseEntity.ok().build();
    }
}
