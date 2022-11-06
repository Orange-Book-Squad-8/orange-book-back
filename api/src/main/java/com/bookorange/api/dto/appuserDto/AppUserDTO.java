package com.bookorange.api.dto.appuserDto;

import com.bookorange.api.domain.AppUser;
import com.bookorange.api.domain.Role;
import com.bookorange.api.enumerator.ContentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AppUserDTO {
    private Long id;

    private String username;

    private String email;

    private ContentType contentType;

    private Set<String> badges;

    private Role role;

    public AppUserDTO(AppUser user) {
        id = user.getId();
        username = user.getUsername();
        email = user.getEmail();
        contentType = user.getContentType();
        badges = user.getBadges();
        role = user.getRole();
    }

}
