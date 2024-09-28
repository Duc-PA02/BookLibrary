package com.booklibrary.bookservice.command.controller;

import com.booklibrary.bookservice.command.command.CreateBookCommmand;
import com.booklibrary.bookservice.command.command.DeleteBookCommand;
import com.booklibrary.bookservice.command.command.UpdateBookCommand;
import com.booklibrary.bookservice.command.model.BookRequestModel;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.axonframework.commandhandling.gateway.CommandGateway;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/books")
@RequiredArgsConstructor
public class BookCommandController {
    private final CommandGateway commandGateway;
    @PostMapping
    public String addBook(@Valid @RequestBody BookRequestModel model){
        CreateBookCommmand commmand = new CreateBookCommmand(UUID.randomUUID().toString(), model.getName(), model.getAuthor(), true);
        return commandGateway.sendAndWait(commmand);
    }
    @PutMapping("/{bookId}")
    public String updateBook(@Valid @RequestBody BookRequestModel model, @PathVariable String bookId){

        UpdateBookCommand command = new UpdateBookCommand(bookId, model.getName(), model.getAuthor(), model.getIsReady());
        return commandGateway.sendAndWait(command);
    }

    @DeleteMapping("/{bookId}")
    public String deleteBook(@PathVariable String bookId){
        DeleteBookCommand command = new DeleteBookCommand(bookId);
        return commandGateway.sendAndWait(command);
    }
}
