package com.example.demostore.domain.mapper;

import com.example.demostore.domain.dto.View.BookView;
import com.example.demostore.domain.model.Book;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", uses = ObjectIdMapper.class)
public abstract class BookViewMapper {

    private UserViewMapper userViewMapper;

    @Autowired
    public void setUserViewMapper(UserViewMapper userViewMapper) {
        this.userViewMapper = userViewMapper;
    }

    public abstract BookView toBookView(Book book);

    public abstract List<BookView> toBookView(List<Book> books);

    @AfterMapping
    protected void after(Book book, @MappingTarget BookView bookView) {
        bookView.setCreator(userViewMapper.toUserViewById(book.getCreatorId()));
    }

}
