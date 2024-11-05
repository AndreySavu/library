package com.example.library.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookMessage {
    private String operation;
    private Long id;
    private Object bookData;
}
