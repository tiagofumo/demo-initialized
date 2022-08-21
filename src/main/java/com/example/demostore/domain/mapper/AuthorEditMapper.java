package com.example.demostore.domain.mapper;

import com.example.demostore.domain.dto.Request.EditAuthorRequest;
import com.example.demostore.domain.model.Author;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = "spring", uses = ObjectIdMapper.class)
public interface AuthorEditMapper {

    Author create(EditAuthorRequest request);

    @BeanMapping(nullValueCheckStrategy = ALWAYS, nullValuePropertyMappingStrategy = IGNORE)
    void update(EditAuthorRequest request, @MappingTarget Author author);

}
