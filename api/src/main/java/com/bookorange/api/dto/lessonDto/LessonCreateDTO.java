package com.bookorange.api.dto.lessonDto;

import com.bookorange.api.enumerator.ContentType;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LessonCreateDTO {

    @ApiModelProperty(value = "Nome da lição")
    @NotBlank(message = "field not found")
    private String title;

    @ApiModelProperty(value = "Descrição da lição")
    @NotBlank(message = "field not found")
    private String description;

    @ApiModelProperty(value = "Nome do autor da lição")
    @NotBlank(message = "field not found")
    private String author;

    @ApiModelProperty(value = "Digite um link válido")
    @NotBlank(message = "field not found")
    @Pattern(regexp = "https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_\\+.~#?&//=]*)")
    private String link;

    @ApiModelProperty(value = "Tópico da lição")
    @NotBlank(message = "field not found")
    private String topic;

    @ApiModelProperty(value = "Escolha o tipo da lição por (VIDEO, ARTICLE, COURSE, BOOK)")
    @NotNull(message = "cannot be null")
    private ContentType contentType;

    @ApiModelProperty(value = "O tempo de duração da lição")
    @Positive
    @Min(1)
    private Integer durationInMinutes;
}
