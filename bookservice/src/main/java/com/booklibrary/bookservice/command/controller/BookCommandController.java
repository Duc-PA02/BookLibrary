package com.booklibrary.bookservice.command.controller;

import com.booklibrary.bookservice.command.command.CreateBookCommmand;
import com.booklibrary.bookservice.command.model.BookRequestModel;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.axonframework.commandhandling.gateway.CommandGateway;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/books")
@RequiredArgsConstructor
public class BookCommandController {
    private final CommandGateway commandGateway;
    @PostMapping
    public String addBook(@RequestBody BookRequestModel model){
        CreateBookCommmand commmand = new CreateBookCommmand(UUID.randomUUID().toString(), model.getName(), model.getAuthor(), true);
        return commandGateway.sendAndWait(commmand);
    }
}
