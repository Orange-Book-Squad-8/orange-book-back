package com.bookorange.api.dto.appuserDto;

import com.bookorange.api.enumerator.StackCategories;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserCreateDTO {

    @Column(unique = true)
    @NotBlank(message = "field not found")
    @Length(min = 3, max = 30)
    private String username;

    @NotBlank(message = "field not found")
    @Length(min = 6, message = "password must contain at least 6 characters")
    private String password;

    @Column(unique = true)
    @NotBlank(message = "field not found")
    @Email(message = "email not valid")
    private String email;

    @NotNull(message = "cannot be null")
    private List<StackCategories> stackCategories;

    @NotNull(message = "cannot be null")
    private String role;
}
