package com.bookorange.api.dto.courseDto;

import com.bookorange.api.enumerator.ContentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseContentDTO {
    private Long id;
    private ContentType contentType;
}
