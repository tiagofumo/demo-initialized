package com.example.demostore.service;

import com.example.demostore.domain.dto.Page;
import com.example.demostore.domain.dto.Query.SearchAuthorsQuery;
import com.example.demostore.domain.dto.Request.EditAuthorRequest;
import com.example.demostore.domain.dto.View.AuthorView;
import com.example.demostore.domain.mapper.AuthorEditMapper;
import com.example.demostore.domain.mapper.AuthorViewMapper;
import com.example.demostore.domain.model.Author;
import com.example.demostore.domain.model.Book;
import com.example.demostore.repository.AuthorRepo;
import com.example.demostore.repository.BookRepo;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepo authorRepo;
    private final BookRepo bookRepo;
    private final AuthorEditMapper authorEditMapper;
    private final AuthorViewMapper authorViewMapper;

    @Transactional
    public AuthorView create(EditAuthorRequest request) {
        Author author = authorEditMapper.create(request);

        author = authorRepo.save(author);

        return authorViewMapper.toAuthorView(author);
    }

    @Transactional
    public AuthorView update(ObjectId id, EditAuthorRequest request) {
        Author author = authorRepo.getById(id);
        authorEditMapper.update(request, author);

        author = authorRepo.save(author);

        return authorViewMapper.toAuthorView(author);
    }

    @Transactional
    public AuthorView delete(ObjectId id) {
        Author author = authorRepo.getById(id);

        authorRepo.delete(author);
        bookRepo.deleteAll(bookRepo.findAllById(author.getBookIds()));

        return authorViewMapper.toAuthorView(author);
    }

    public AuthorView getAuthor(ObjectId id) {
        return authorViewMapper.toAuthorView(authorRepo.getById(id));
    }

    public List<AuthorView> getAuthors(Iterable<ObjectId> ids) {
        return authorViewMapper.toAuthorView(authorRepo.findAllById(ids));
    }

    public List<AuthorView> getBookAuthors(ObjectId bookId) {
        Book book = bookRepo.getById(bookId);
        return authorViewMapper.toAuthorView(authorRepo.findAllById(book.getAuthorIds()));
    }

    public List<AuthorView> searchAuthors(Page page, SearchAuthorsQuery query) {
        return authorViewMapper.toAuthorView(authorRepo.searchAuthors(page, query));
    }

}
