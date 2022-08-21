package com.example.demostore.domain.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Document(collection = "books") @Data
public class Book implements Serializable {

    @Id
    private ObjectId id;

    @CreatedBy
    private ObjectId creatorId;
    @LastModifiedBy
    private ObjectId modifierId;

    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime modifiedAt;

    private String title;
    private String about;
    private String language;
    private Set<String> genres = new HashSet<>();
    private String isbn13;
    private String isbn10;
    private String publisher;
    private LocalDate publishDate;
    private int hardcover;

    private Set<ObjectId> authorIds = new HashSet<>();

}
