package com.bookorange.api.dto.courseDto;

import com.bookorange.api.enumerator.Difficulty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseDifficultyDTO {
    private Long id;
    private Difficulty difficulty;
}
