package com.bookorange.api.dto.courseDto;

import com.bookorange.api.domain.Section;
import com.bookorange.api.enumerator.Difficulty;
import com.bookorange.api.enumerator.StackCategories;
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

    @NotBlank(message = "field not found")
    private String description;

    @NotBlank(message = "field not found")
    private String creator;

    @NotNull(message = "cannot be null")
    private StackCategories category;

    @NotNull(message = "cannot be null")
    private Difficulty difficulty;

    private Boolean visible;

    private List<Section> sections = new ArrayList<>();
}
