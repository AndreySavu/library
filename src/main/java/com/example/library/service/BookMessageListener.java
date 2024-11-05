package com.example.library.service;

import com.example.library.exception.ResourceNotFoundException;
import com.example.library.model.Book;
import com.example.library.model.BookCreationRequest;
import com.example.library.model.BookMessage;
import com.example.library.model.BookUpdateRequest;
import com.example.library.repository.BookRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class BookMessageListener {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(queues = "bookQueue")
    public Book handleBookMessage(BookMessage bookMessage) {
        switch (bookMessage.getOperation()) {
            case "CREATE":
                BookCreationRequest createRequest = objectMapper.convertValue(bookMessage.getBookData(), BookCreationRequest.class);
                Book newBook = new Book(createRequest.getTitle(), createRequest.getAuthor(), createRequest.getPublishedDate());
                return bookRepository.save(newBook);
            case "UPDATE":
                BookUpdateRequest updateRequest = objectMapper.convertValue(bookMessage.getBookData(), BookUpdateRequest.class);
                Book bookToUpdate = bookRepository.findById(bookMessage.getId())
                        .orElseThrow(() -> new ResourceNotFoundException("Книга не найдена"));
                bookToUpdate.setTitle(updateRequest.getTitle());
                bookToUpdate.setAuthor(updateRequest.getAuthor());
                bookToUpdate.setPublishedDate(updateRequest.getPublishedDate());
                return bookRepository.save(bookToUpdate);
            case "DELETE":
                bookRepository.deleteById(bookMessage.getId());
                return null;
            default:
                throw new IllegalArgumentException("Неизвестная операция: " + bookMessage.getOperation());
        }
    }
}
