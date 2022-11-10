package com.bookorange.api.dto.appuserDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserLoginDTO {

    @Column(unique = true)
    @NotBlank(message = "field not found")
    @Length(min = 3, max = 30)
    private String username;

    @NotBlank(message = "field not found")
    @Length(min = 6, message = "password must contain at least 6 characters")
    private String password;
}