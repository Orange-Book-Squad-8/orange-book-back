package com.bookorange.api.dto.courseDto;

import com.bookorange.api.domain.Course;
import com.bookorange.api.enumerator.Difficulty;
import com.bookorange.api.enumerator.StackCategories;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseDTO {
    private Long id;

    @NotBlank(message = "field not found")
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

    private Integer totalLessons;


    public CourseDTO(Course course) {
        id = course.getId();
        title = course.getTitle();
        description = course.getDescription();
        creator = course.getCreator();
        category = course.getCategory();
        difficulty = course.getDifficulty();
        visible = course.getVisible();
    }
}
