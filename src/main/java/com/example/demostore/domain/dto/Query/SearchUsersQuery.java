package com.example.demostore.domain.dto.Query;

import lombok.Data;

@Data
public class SearchUsersQuery {

    private String id;
    private String username;
    private String fullName;

}
