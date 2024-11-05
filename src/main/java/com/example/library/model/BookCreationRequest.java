package com.example.library.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import com.example.library.validation.DefaultMessages;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class BookCreationRequest {

    @NotBlank(message = DefaultMessages.BOOK_TITLE)
    @Schema(description = "Название книги")
    private String title;

    @NotBlank(message = DefaultMessages.BOOK_AUTHOR)
    @Schema(description = "Автор книги")
    private String author;

    @NotNull(message = DefaultMessages.BOOK_DATE)
    @Schema(description = "Дата публикации")
    private LocalDate publishedDate;
}
