package com.booklibrary.employeeservice.command.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeCreatedEvent {
    private String id;
    private String firstName;
    private String LastName;
    private String Kin;
    private Boolean isDisciplined;
}
