package com.bookorange.api.service;

import com.bookorange.api.domain.Section;
import com.bookorange.api.dto.sectionDto.SectionCreateDTO;
import com.bookorange.api.dto.sectionDto.SectionDTO;
import com.bookorange.api.dto.sectionDto.SectionEditLessonDTO;

public interface SectionService {
    Section create(SectionCreateDTO createSection);

    Section update(SectionDTO sectionDTO);

    void delete(Long sectionId);

    void addLesson(SectionEditLessonDTO sectionEditLessonDTO);

    void removeLesson(SectionEditLessonDTO sectionEditLessonDTO);

    Section findById(Long sectionId);
}
