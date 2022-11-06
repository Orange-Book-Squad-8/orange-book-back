package com.bookorange.api.dto.roleDto;

import com.bookorange.api.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoleDTO {
    private String roleName;
    private Long roleId;

    public RoleDTO(Role role) {
        roleName = role.getName();
        roleId = role.getId();
    }
}