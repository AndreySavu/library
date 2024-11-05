package com.example.library.controller;

import com.example.library.model.Book;
import com.example.library.model.BookCreationRequest;
import com.example.library.model.BookUpdateRequest;
import com.example.library.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Operation(summary = "1. Получение списка всех книг.")
    @GetMapping
    public List<Book> getBooks() {
        return bookService.getBooks();
    }

    @Operation(summary = "2. Получение книги по идентификатору.")
    @GetMapping("/{id}")
    public Book getBookById(@PathVariable long id) {
        return bookService.getBookById(id);

    }

    @Operation(summary = "3. Добавление новой книги",
            description = "Формал даты yyyy-mm-dd")
    @PostMapping
    public Book createBook(@Valid @RequestBody BookCreationRequest bookCreationRequest) {
        return bookService.createBook(bookCreationRequest);
    }

    @Operation(summary = "4. Обновление информации о книге.",
            description = "Формал даты yyyy-mm-dd")
    @PutMapping("/{id}")
    public Book updateBook(@PathVariable long id, @Valid @RequestBody BookUpdateRequest bookUpdateRequest) {
        return bookService.updateBook(id, bookUpdateRequest);
    }

    @Operation(summary = "5. Удаление книги по идентификатору.")
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable long id) {
        bookService.deleteBook(id);
    }
}

