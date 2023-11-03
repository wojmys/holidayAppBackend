package de.funkeengineering.urlaubsapp.controller;

import de.funkeengineering.urlaubsapp.domain.Holiday;
import de.funkeengineering.urlaubsapp.domain.dto.HolidayDto;
import de.funkeengineering.urlaubsapp.mapper.HolidayMapper;
import de.funkeengineering.urlaubsapp.service.HolidayDbService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("api/holiday")
public class HolidayController {

    //create mapper
    private HolidayMapper holidayMapper;
    private HolidayDbService holidayDbService;
    @GetMapping
    public ResponseEntity<List<HolidayDto>> fetchAllHolidays() {
        List<Holiday>holidays = holidayDbService.getAllHolidays();
        return ResponseEntity.ok(holidayMapper.mapToHolidayDtoList(holidays));
    }

}
