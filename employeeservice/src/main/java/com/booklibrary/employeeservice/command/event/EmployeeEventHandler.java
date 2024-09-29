package com.booklibrary.employeeservice.command.event;

import com.booklibrary.employeeservice.command.data.Employee;
import com.booklibrary.employeeservice.command.data.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.DisallowReplay;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmployeeEventHandler {
    private final EmployeeRepository employeeRepository;

    @EventHandler
    public void on(EmployeeCreatedEvent event){
        Employee employee = new Employee();
        BeanUtils.copyProperties(event, employee);
        employeeRepository.save(employee);
    }

    @EventHandler
    public void on(EmployeeUpdatedEvent event) {
        Optional<Employee> oldEmployee = employeeRepository.findById(event.getId());
        Employee employee = oldEmployee.orElseThrow(() -> new RuntimeException("Employee not found"));
        employee.setFirstName(event.getFirstName());
        employee.setKin(event.getKin());
        employee.setLastName(event.getLastName());
        employee.setIsDisciplined(event.getIsDisciplined());
        employeeRepository.save(employee);
    }

    @EventHandler
    @DisallowReplay
    public void on(EmployeeDeletedEvent event) {
        try {
            employeeRepository.findById(event.getId()).orElseThrow(() -> new Exception("Employee not found"));
            employeeRepository.deleteById(event.getId());
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
    }
}
