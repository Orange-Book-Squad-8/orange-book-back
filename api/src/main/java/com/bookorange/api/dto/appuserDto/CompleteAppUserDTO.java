package com.bookorange.api.dto.appuserDto;

import com.bookorange.api.domain.AppUser;
import com.bookorange.api.domain.Role;
import com.bookorange.api.enumerator.StackCategories;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CompleteAppUserDTO {
    private Long id;
    private String username;
    private String email;
    private List<StackCategories> stackCategories = new ArrayList<>();
    private List<String> badges = new ArrayList<>();

    private Role role;

    public CompleteAppUserDTO(AppUser user) {
        id = user.getId();
        username = user.getUsername();
        email = user.getEmail();
        stackCategories = user.getStackCategories();
        badges = user.getBadges();
    }
}
