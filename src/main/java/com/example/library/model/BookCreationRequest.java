package com.example.library.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import com.example.library.validation.DefaultMessages;

import java.time.LocalDate;

public class BookCreationRequest {

    @NotNull(message = DefaultMessages.BOOK_TITLE)
    @Schema(description = "Название книги")
    private String title;

    @NotNull(message = DefaultMessages.BOOK_AUTHOR)
    @Schema(description = "Автор книги")
    private String author;

    @NotNull(message = DefaultMessages.BOOK_DATE)
    @Schema(description = "Дата публикации")
    private LocalDate publishedDate;
}
