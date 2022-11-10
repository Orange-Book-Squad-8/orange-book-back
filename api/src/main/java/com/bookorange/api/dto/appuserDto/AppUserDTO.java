package com.bookorange.api.dto.appuserDto;

import com.bookorange.api.domain.AppUser;
import com.bookorange.api.domain.Role;
import com.bookorange.api.enumerator.StackCategories;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AppUserDTO {
    private Long id;


    @Column(unique = true)
    @NotBlank(message = "field not found")
    @Length(min = 3, max = 30)
    private String username;

    @Column(unique = true)
    @NotBlank(message = "field not found")
    @Email(message = "email not valid")
    private String email;


    private List<StackCategories> stackCategories;

    private List<String> badges;

    private Role role;

    public AppUserDTO(AppUser user) {
        id = user.getId();
        username = user.getUsername();
        email = user.getEmail();
        stackCategories = user.getStackCategories();
        badges = user.getBadges();
        role = user.getRole();
    }

}
