package com.booklibrary.employeeservice.query.projection;

import com.booklibrary.employeeservice.command.data.Employee;
import com.booklibrary.employeeservice.command.data.EmployeeRepository;
import com.booklibrary.employeeservice.query.model.EmployeeResponseModel;
import com.booklibrary.employeeservice.query.queries.GetAllEmployeeQuery;
import com.booklibrary.employeeservice.query.queries.GetDetailEmployeeQuery;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EmployeeProjection {
    private final EmployeeRepository employeeRepository;

    @QueryHandler
    public List<EmployeeResponseModel> handle(GetAllEmployeeQuery query){
        List<Employee> listEmployee = employeeRepository.findAllByIsDisciplined(query.getIsDisciplined());
        return listEmployee.stream().map(employee -> {
            EmployeeResponseModel model = new EmployeeResponseModel();
            BeanUtils.copyProperties(employee,model);
            return model;
        }).toList();
    }

    @QueryHandler
    public EmployeeResponseModel handle(GetDetailEmployeeQuery query){
        Employee employee = employeeRepository.findById(query.getId()).orElseThrow(() -> new RuntimeException("Employee not found"));
        EmployeeResponseModel model = new EmployeeResponseModel();
        BeanUtils.copyProperties(employee,model);
        return model;
    }
}
