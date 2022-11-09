package com.bookorange.api.dto.courseDto;

import com.bookorange.api.enumerator.Difficulty;
import com.bookorange.api.enumerator.StackCategories;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CourseCreateDTO {
    private String title;

    private String description;

    private String creator;

    private StackCategories category;

    private Difficulty difficulty;

    private Boolean visible;
}
