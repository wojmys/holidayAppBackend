package de.funkeengineering.urlaubsapp.service;

import de.funkeengineering.urlaubsapp.domain.Holiday;
import de.funkeengineering.urlaubsapp.error.HolidayNotFoundException;
import de.funkeengineering.urlaubsapp.repository.HolidayRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class HolidayDbService {

    private HolidayRepository holidayRepository;

    public List<Holiday> getAllHolidays(){
        return holidayRepository.findAll();
    }
    public Holiday saveHoliday(Holiday holiday) {
        return holidayRepository.save(holiday);
    }
    public Holiday getHolidayById(Long id){
        return holidayRepository.findById(id).orElseThrow(()-> new HolidayNotFoundException(id));
    }
    public Holiday updateHoliday(Long holidayId, Holiday updatedHoliday){
        Holiday existingHoliday = getHolidayById(holidayId);

        existingHoliday.setStartDate(updatedHoliday.getStartDate());
        existingHoliday.setEndDate(updatedHoliday.getEndDate());
        existingHoliday.setStatus(updatedHoliday.getStatus());

        return saveHoliday(existingHoliday);
    }
}
