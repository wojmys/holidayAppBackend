package de.funkeengineering.urlaubsapp.mapper;

import de.funkeengineering.urlaubsapp.domain.Booking;
import de.funkeengineering.urlaubsapp.domain.Holiday;
import de.funkeengineering.urlaubsapp.domain.dto.HolidayDto;
import de.funkeengineering.urlaubsapp.service.BookingDbService;
import de.funkeengineering.urlaubsapp.service.HolidayDbService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HolidayMapper {

    private BookingDbService bookingDbService;

    //map HolidayToHolidayDto
    public HolidayDto mapHolidayToHolidayDto(Holiday holiday){
        return HolidayDto.builder()
                .id(holiday.getId())
                .startDate(holiday.getStartDate())
                .endDate(holiday.getEndDate())
                .bookingId(holiday.getBooking().getId())
                .status(holiday.getStatus())
                .build();
    }

    //map HolidayDtoToHoliday
    public Holiday mapHolidayDtoToHoliday(HolidayDto holidayDto){
        return Holiday.builder()
                .id(holidayDto.getId())
                .startDate(holidayDto.getStartDate())
                .endDate(holidayDto.getEndDate())
                .booking(bookingDbService.getBookingById(holidayDto.getBookingId()))
                .status(holidayDto.getStatus())
                .build();
    }
    //map toHolidayDtoList

    public List<HolidayDto>mapToHolidayDtoList(List<Holiday>holidays){
        return holidays.stream()
                .map(this::mapHolidayToHolidayDto)
                .collect(Collectors.toList());
    }



}
