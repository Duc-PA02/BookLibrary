package com.booklibrary.bookservice.query.projection;

import com.booklibrary.bookservice.command.data.Book;
import com.booklibrary.bookservice.command.data.BookRepository;
import com.booklibrary.bookservice.query.model.BookResponseModel;
import com.booklibrary.bookservice.query.queries.GetAllBookQuery;
import com.booklibrary.bookservice.query.queries.GetBookDetailQuery;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BookProjection {
    public final BookRepository bookRepository;

    @QueryHandler
    public List<BookResponseModel> handle(GetAllBookQuery query){
        List<Book> bookList = bookRepository.findAll();
        List<BookResponseModel> listBookResponse = new ArrayList<>();
        bookList.forEach(book -> {
            BookResponseModel model = new BookResponseModel();
            BeanUtils.copyProperties(book, model);
            listBookResponse.add(model);
        });
        return listBookResponse;
    }

    @QueryHandler
    public BookResponseModel handler(GetBookDetailQuery query){
        BookResponseModel model = new BookResponseModel();
        bookRepository.findById(query.getId()).ifPresent(book -> {
            BeanUtils.copyProperties(book, model);
        });
        return model;
    }
}
