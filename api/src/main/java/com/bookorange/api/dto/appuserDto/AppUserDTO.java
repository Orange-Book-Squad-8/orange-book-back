package com.bookorange.api.dto.appuserDto;

import com.bookorange.api.domain.AppUser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class AppUserDTO {
    private Long id;



    @NotBlank(message = "field not found")
    @Length(min = 3, max = 30, message = "username needs at least 3 characters")
    private String username;


    @NotBlank(message = "field not found")
    @Email(message = "email not valid")
    private String email;




    public AppUserDTO(AppUser user) {
        id = user.getId();
        username = user.getUsername();
        email = user.getEmail();
    }

}
