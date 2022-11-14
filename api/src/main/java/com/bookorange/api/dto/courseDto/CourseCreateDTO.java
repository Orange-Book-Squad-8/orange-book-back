package com.bookorange.api.dto.courseDto;

import com.bookorange.api.domain.Section;
import com.bookorange.api.enumerator.Difficulty;
import com.bookorange.api.enumerator.StackCategories;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CourseCreateDTO {
    private String title;

    @ApiModelProperty(value = "Nome do curso")
    @NotBlank(message = "field not found")
    private String description;

    @ApiModelProperty(value = "Criador do curso")
    @NotBlank(message = "field not found")
    private String creator;

    @ApiModelProperty(value = "Escolha as Stacks (FRONT_END, BACK_END, FULLSTACK, UI, UX, SOFT_SKILLS)")

    @NotNull(message = "cannot be null")
    private StackCategories category;

    @ApiModelProperty(value = "Escolha a Dificuldade por (BEGINNER, INTERMEDIATE, ADVANCED, FULL_FORMATION)")
    @NotNull(message = "cannot be null")
    private Difficulty difficulty;

    @ApiModelProperty(value = "Escolha se o curso será visível ou não para os usuários")
    private Boolean visible;

    @ApiModelProperty(value = "Lista de seções do curso")
    private List<Section> sections = new ArrayList<>();
}
