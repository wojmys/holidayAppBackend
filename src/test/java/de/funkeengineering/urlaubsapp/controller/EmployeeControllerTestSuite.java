package de.funkeengineering.urlaubsapp.controller;

import com.google.gson.Gson;
import de.funkeengineering.urlaubsapp.domain.Employee;
import de.funkeengineering.urlaubsapp.domain.dto.EmployeeDto;
import de.funkeengineering.urlaubsapp.mapper.EmployeeMapper;
import de.funkeengineering.urlaubsapp.service.EmployeeDbService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitWebConfig
@WebMvcTest(EmployeeController.class)
class EmployeeControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private EmployeeDbService employeeDbService;
    @MockBean
    private EmployeeMapper employeeMapper;

    @Test
    void createEmployeeTest() throws Exception {

        //given
        Employee employee = Employee.builder()
                .id(1L)
                .totalHolidays(10)
                .remainingHolidays(10)
                .bookings(new ArrayList<>())
                .substitutions(new ArrayList<>())
                .name("Joe Doe")
                .build();
        EmployeeDto employeeDto = EmployeeDto.builder()
                .id(1L)
                .totalHolidays(10)
                .remainingHolidays(10)
                .bookingsId(new ArrayList<>())
                .substitutionsId(new ArrayList<>())
                .name("Joe Doe")
                .build();
        //when & then
        when(employeeMapper.mapEmployeeDtoToEmployee(employeeDto)).thenReturn(employee);
        when(employeeDbService.saveEmployee(employee)).thenReturn(employee);
        String jsonContent = new Gson().toJson(employeeDto);
        mockMvc.perform(post("/api/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    void fetchAnEmptyListTest() throws Exception {
        //when&then
        when(employeeDbService.getAllEmployees()).thenReturn(List.of());
        mockMvc.perform(get("/api/employee")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }

    @Test
    void fetchAllEmployeesTest() throws Exception {
        //given
        List<Employee> employeeList = List.of(
                Employee.builder()
                        .id(22L)
                        .totalHolidays(10)
                        .remainingHolidays(10)
                        .bookings(new ArrayList<>())
                        .substitutions(new ArrayList<>())
                        .name("Joe Doe")
                        .build(),

                Employee.builder()
                        .id(33L)
                        .totalHolidays(10)
                        .remainingHolidays(10)
                        .bookings(new ArrayList<>())
                        .substitutions(new ArrayList<>())
                        .name("Sam Smith")
                        .build()
        );
        List<EmployeeDto> employeeDtoList = List.of(
                EmployeeDto.builder()
                        .id(22L)
                        .totalHolidays(10)
                        .remainingHolidays(10)
                        .bookingsId(new ArrayList<>())
                        .substitutionsId(new ArrayList<>())
                        .name("Joe Doe")
                        .build(),

                EmployeeDto.builder()
                        .id(33L)
                        .totalHolidays(10)
                        .remainingHolidays(10)
                        .bookingsId(new ArrayList<>())
                        .substitutionsId(new ArrayList<>())
                        .name("Sam Smith")
                        .build()
        );
        //when & then
        when(employeeDbService.getAllEmployees()).thenReturn(employeeList);
        when(employeeMapper.mapToEmployeeDtosList(employeeList)).thenReturn(employeeDtoList);

        mockMvc.perform(get("/api/employee")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(22)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].totalHolidays", Matchers.is(10)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id", Matchers.is(33)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].totalHolidays", Matchers.is(10)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].remainingHolidays", Matchers.is(10)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].remainingHolidays", Matchers.is(10)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name", Matchers.is("Sam Smith")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("Joe Doe")));
    }

    @Test
    void fetchEmployeeByIdTest() throws Exception {
        //given
        Employee employee = Employee.builder()
                .id(66L)
                .totalHolidays(15)
                .remainingHolidays(10)
                .bookings(new ArrayList<>())
                .substitutions(new ArrayList<>())
                .name("Joe Doe")
                .build();
        EmployeeDto employeeDto = EmployeeDto.builder()
                .id(66L)
                .totalHolidays(15)
                .remainingHolidays(10)
                .bookingsId(new ArrayList<>())
                .substitutionsId(new ArrayList<>())
                .name("Joe Doe")
                .build();
        when(employeeDbService.getEmployeeById(66L)).thenReturn(employee);
        when(employeeMapper.mapEmployeeToEmployeeDto(employee)).thenReturn(employeeDto);
        //when & then
        mockMvc.perform(get("/api/employee/{id}", employeeDto.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                //    .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(66)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("Joe Doe")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalHolidays", Matchers.is(15)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.remainingHolidays", Matchers.is(10)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.bookingsId.size()", Matchers.is(0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.substitutionsId.size()", Matchers.is(0)));
    }

    @Test
    void updateEmployeeTest() throws Exception{
        //given
        Employee employee = Employee.builder()
                .id(77L)
                .totalHolidays(5)
                .remainingHolidays(1)
                .bookings(new ArrayList<>())
                .substitutions(new ArrayList<>())
                .name("Liam Stevens")
                .build();
        EmployeeDto requestedEmployee = EmployeeDto.builder()
                .id(77L)
                .totalHolidays(55)
                .remainingHolidays(10)
                .bookingsId(new ArrayList<>())
                .substitutionsId(new ArrayList<>())
                .name("Sara May")
                .build();
        when(employeeMapper.mapEmployeeDtoToEmployee(any())).thenReturn(employee);
        when(employeeDbService.saveEmployee(any())).thenReturn(employee);
        when(employeeMapper.mapEmployeeToEmployeeDto(any())).thenReturn(requestedEmployee);
        String jsonContent = new Gson().toJson(requestedEmployee);
        //when & then
        mockMvc.perform(put("/api/employee/{id}", requestedEmployee.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("Sara May")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.remainingHolidays",Matchers.is(10)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalHolidays",Matchers.is(55)));
    }
}
