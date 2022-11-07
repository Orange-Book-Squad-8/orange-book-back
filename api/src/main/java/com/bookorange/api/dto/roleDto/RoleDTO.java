package com.bookorange.api.dto.roleDto;

import com.bookorange.api.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoleDTO {

    private Long id;
    private String roleName;

    public RoleDTO(Role role) {
        id = role.getId();
        roleName = role.getName();
    }
}