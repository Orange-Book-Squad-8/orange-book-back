package com.bookorange.api.dto.courseDto;

import com.bookorange.api.domain.Course;
import com.bookorange.api.enumerator.Difficulty;
import com.bookorange.api.enumerator.StackCategories;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseDTO {
    private Long id;

    private String title;

    private String description;

    private String creator;

    private StackCategories category;

    private Difficulty difficulty;

    private Boolean visible;


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
