package com.bookorange.api.dto.courseDto;

import com.bookorange.api.domain.Course;
import com.bookorange.api.domain.Section;
import com.bookorange.api.enumerator.Difficulty;
import com.bookorange.api.enumerator.StackCategories;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

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


    private List<Section> sections;

    private List<Long> idSections;

    public CourseDTO(Course course) {
        id = course.getId();
        title = course.getTitle();
        description = course.getDescription();
        creator = course.getCreator();
        category = course.getCategory();
        difficulty = course.getDifficulty();
        visible = course.getVisible();
        sections = course.getSections();
        idSections = course.getIdSections();
    }

    public CourseDTO(CourseEditDTO course) {
        id = course.getId();
        title = course.getTitle();
        description = course.getDescription();
        creator = course.getCreator();
        category = course.getCategory();
        difficulty = course.getDifficulty();
        visible = course.getVisible();
    }
}
