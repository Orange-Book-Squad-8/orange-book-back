package com.bookorange.api.dto.appuserDto;

import com.bookorange.api.enumerator.StackCategories;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserCreateDTO {

    @ApiModelProperty(value = "Nome do usuário, deve conter no mínimo 3 caracteres")
    @NotBlank(message = "field not found")
    @Length(min = 3, max = 30)
    private String username;

    @ApiModelProperty(value = "A senha deve conter no mínimo 6 caracteres")
    @NotBlank(message = "field not found")
    @Length(min = 6, message = "password must contain at least 6 characters")
    private String password;

    @ApiModelProperty(value = "Digite um email válido")
    @NotBlank(message = "field not found")
    @Email(message = "email must be unique")
    private String email;

    @ApiModelProperty(value = "Escolha as Stack (FRONT_END, BACK_END, FULLSTACK, UI, UX, SOFT_SKILLS)")
    @NotNull(message = "cannot be null")
    private List<StackCategories> stackCategories;

    @ApiModelProperty(value = "Escolha o tipo do usuário 'user1' ou 'admin'")
    @NotNull(message = "cannot be null")
    private String role;
}
