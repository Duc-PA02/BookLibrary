package com.booklibrary.bookservice.query.controller;

import com.booklibrary.bookservice.command.model.BookRequestModel;
import com.booklibrary.bookservice.query.model.BookResponseModel;
import com.booklibrary.bookservice.query.queries.GetAllBookQuery;
import com.booklibrary.bookservice.query.queries.GetBookDetailQuery;
import com.booklibrary.commonservice.service.KafkaService;
import lombok.RequiredArgsConstructor;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/books")
@RequiredArgsConstructor
public class BookQueryController {
    private final QueryGateway queryGateway;
    private final KafkaService kafkaService;
    @GetMapping
    public List<BookResponseModel> getAllBooks(){
        GetAllBookQuery query = new GetAllBookQuery();
        return queryGateway.query(query, ResponseTypes.multipleInstancesOf(BookResponseModel.class)).join();
    }
    @GetMapping("{bookId}")
    public BookResponseModel getBookDetail(@PathVariable String bookId){
        GetBookDetailQuery query = new GetBookDetailQuery(bookId);
        return queryGateway.query(query, ResponseTypes.instanceOf(BookResponseModel.class)).join();
    }
    @PostMapping("/sendMessage")
    public void sendMessage(@RequestParam String message){
        kafkaService.sendMessage("test", message);
    }
}
