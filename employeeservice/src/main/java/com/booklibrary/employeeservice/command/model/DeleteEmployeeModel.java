package com.booklibrary.employeeservice.command.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeleteEmployeeModel {

    @NotBlank(message = "Id is mandatory")
    private String id;
}
