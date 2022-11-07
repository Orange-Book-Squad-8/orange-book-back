package com.bookorange.api.dto.appuserDto;

import com.bookorange.api.enumerator.StackCategories;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserCreateDTO {
    private String username;

    private String password;

    private String email;

    private List<StackCategories> stackCategories;

    private String role;
}
