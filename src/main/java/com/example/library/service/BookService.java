package com.example.library.service;

import com.example.library.exception.ResourceNotFoundException;
import com.example.library.model.Book;
import com.example.library.model.BookCreationRequest;
import com.example.library.model.BookMessage;
import com.example.library.model.BookUpdateRequest;
import com.example.library.repository.BookRepository;
import lombok.Setter;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Setter
@Service
public class BookService {

    @Value("${queue.name}")
    private String queueName;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private BookRepository bookRepository;

    public Book createBook(BookCreationRequest bookCreationRequest) {
        BookMessage bookMessage = new BookMessage("CREATE", null, bookCreationRequest);
        return (Book) amqpTemplate.convertSendAndReceive(queueName, bookMessage);
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Книга не найдена"));
    }

    public Book updateBook(long id, BookUpdateRequest bookUpdateRequest) {
        BookMessage bookMessage = new BookMessage("UPDATE", id, bookUpdateRequest);
        return (Book) amqpTemplate.convertSendAndReceive(queueName, bookMessage);
    }

    public void deleteBook(long id) {
        amqpTemplate.convertAndSend(queueName, new BookMessage("DELETE", id, null));
    }

}