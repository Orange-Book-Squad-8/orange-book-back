package com.bookorange.api.staticmethods;

import com.bookorange.api.domain.AppUser;
import com.bookorange.api.domain.Role;
import com.bookorange.api.enumerator.StackCategories;

import java.util.List;
import java.util.Optional;

public class StaticFields {

    public static final Long ID = 1L;

    public static final String BODY = """
                {
                  "email": "email24@gmail.com",
                  "password": "123214543",
                  "role": "admin",
                  "stackCategories": [
                    "BACK_END"
                  ],
                  "username": "string12"
                }
                """;
    public static final Role ROLE = Role.builder()
            .id(1L)
            .name("admin")
            .build();

    public static final AppUser USER = AppUser.builder()
            .email("email24@gmail.com")
            .password("123214543")
            .role(ROLE)
            .stackCategories(List.of(StackCategories.BACK_END))
            .username("string12")
            .build();


    public static final String BODY_UPDATE = """
            {
              "email": "emailnovo@email.com",
              "id": 1,
              "username": "usernovo"
            }
            """;

    public static Optional<AppUser> returnOptionalAppUser(){
        return Optional.ofNullable(AppUser.builder()
                .email("email24@gmail.com")
                .id(1L)
                .username("string12")
                .build());

    }

}
