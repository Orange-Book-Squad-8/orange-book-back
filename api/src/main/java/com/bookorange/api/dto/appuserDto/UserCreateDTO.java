package com.bookorange.api.dto.appuserDto;

import com.bookorange.api.domain.Role;
import com.bookorange.api.enumerator.ContentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserCreateDTO {
    private Long id;

    private String username;

    private String password;

    private String email;

    private ContentType contentType;

    private Role role;
}
