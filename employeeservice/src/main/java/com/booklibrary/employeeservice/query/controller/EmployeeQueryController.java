package com.booklibrary.employeeservice.query.controller;

import com.booklibrary.employeeservice.query.model.EmployeeResponseModel;
import com.booklibrary.employeeservice.query.queries.GetAllEmployeeQuery;
import com.booklibrary.employeeservice.query.queries.GetDetailEmployeeQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/employees")
@RequiredArgsConstructor
@Slf4j
public class EmployeeQueryController {
    private final QueryGateway queryGateway;

    @GetMapping
    public List<EmployeeResponseModel> getAllEmployee(@RequestParam(required = false, defaultValue = "false") Boolean isDisciplined){
        log.info("Calling to getAllEmployee");
        return queryGateway
                .query(new GetAllEmployeeQuery(isDisciplined), ResponseTypes.multipleInstancesOf(EmployeeResponseModel.class)).join();
    }

    @GetMapping("/{employeeId}")
    public EmployeeResponseModel getDetailEmployee(@PathVariable String employeeId){
        return queryGateway.query(new GetDetailEmployeeQuery(employeeId),ResponseTypes.instanceOf(EmployeeResponseModel.class)).join();
    }
}
