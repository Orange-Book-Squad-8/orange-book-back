package com.bookorange.api.dto.courseDto;

import com.bookorange.api.enumerator.StackCategories;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseCategoryDTO {
    private Long id;
    private StackCategories category;
}
