package com.example.demostore.domain.dto.Request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
public class UpdateUserRequest {

    @NotBlank
    private String fullName;
    private Set<String> authorities;

}