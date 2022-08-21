package com.example.demostore.domain.mapper;

import com.example.demostore.domain.dto.Request.CreateUserRequest;
import com.example.demostore.domain.dto.Request.UpdateUserRequest;
import com.example.demostore.domain.model.Role;
import com.example.demostore.domain.model.User;
import lombok.NoArgsConstructor;
import org.mapstruct.*;

import static java.util.stream.Collectors.toSet;
import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = "spring", uses = ObjectIdMapper.class)
public abstract class UserEditMapper {

    @Mapping(target = "authorities", ignore = true)
    public abstract User create(CreateUserRequest request);

    @BeanMapping(nullValueCheckStrategy = ALWAYS, nullValuePropertyMappingStrategy = IGNORE)
    @Mapping(target = "authorities", ignore = true)
    public abstract void update(UpdateUserRequest request, @MappingTarget User user);

    @AfterMapping
    protected void afterCreate(CreateUserRequest request, @MappingTarget User user) {
        if (request.getAuthorities() != null) {
            user.setAuthorities(request.getAuthorities().stream().map(Role::new).collect(toSet()));
        }
    }

    @AfterMapping
    protected void afterUpdate(UpdateUserRequest request, @MappingTarget User user) {
        if (request.getAuthorities() != null) {
            user.setAuthorities(request.getAuthorities().stream().map(Role::new).collect(toSet()));
        }
    }

}
