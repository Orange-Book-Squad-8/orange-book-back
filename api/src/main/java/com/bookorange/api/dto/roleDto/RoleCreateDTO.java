package com.bookorange.api.dto.roleDto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoleCreateDTO {

    @ApiModelProperty(value = "O tipo do usuário -> 'user1' ou 'admin' ")
    private String roleName;
}
