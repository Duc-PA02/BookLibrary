package com.booklibrary.employeeservice.command.controller;

import com.booklibrary.employeeservice.command.command.CreateEmployeeCommand;
import com.booklibrary.employeeservice.command.command.DeleteEmployeeCommand;
import com.booklibrary.employeeservice.command.command.UpdateEmployeeCommand;
import com.booklibrary.employeeservice.command.model.CreateEmployeeModel;
import com.booklibrary.employeeservice.command.model.UpdateEmployeeModel;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/employees")
@RequiredArgsConstructor
public class EmployeeCommandController {
    private final CommandGateway commandGateway;

    @PostMapping
    public String createEmployee(@Valid @RequestBody CreateEmployeeModel model){
        CreateEmployeeCommand command = new CreateEmployeeCommand(UUID.randomUUID().toString(), model.getFirstName(), model.getLastName(), model.getKin(), false);
        return commandGateway.sendAndWait(command);
    }

    @PutMapping("/{employeeId}")
    public String updateEmployee(@Valid @RequestBody UpdateEmployeeModel model, @PathVariable String employeeId){
        UpdateEmployeeCommand command = new UpdateEmployeeCommand(employeeId,model.getFirstName(),model.getLastName(),model.getKin(),model.getIsDisciplined());
        return commandGateway.sendAndWait(command);
    }

    @DeleteMapping("/{employeeId}")
    public String deleteEmployee(@PathVariable String employeeId){
        DeleteEmployeeCommand command = new DeleteEmployeeCommand(employeeId);
        return commandGateway.sendAndWait(command);
    }
}
