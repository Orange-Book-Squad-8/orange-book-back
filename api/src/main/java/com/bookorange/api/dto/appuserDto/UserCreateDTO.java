package com.bookorange.api.dto.appuserDto;

import com.bookorange.api.enumerator.ContentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserCreateDTO {
    private String username;

    private String password;

    private String email;

    private ContentType contentType;

    private String role;
}
