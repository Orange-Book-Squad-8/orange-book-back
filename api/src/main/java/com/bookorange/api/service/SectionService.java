package com.bookorange.api.service;

import com.bookorange.api.dto.sectionDto.CreateSectionDTO;
import com.bookorange.api.dto.sectionDto.SectionAddLessonDTO;
import com.bookorange.api.dto.sectionDto.SectionDTO;
import com.bookorange.api.dto.sectionDto.SectionRemoveLessonDTO;

public interface SectionService {
    SectionDTO create(CreateSectionDTO createSectionDTO);

    SectionDTO update(SectionDTO sectionDTO);

    void delete(Long sectionId);

    void addLesson(SectionAddLessonDTO sectionAddLessonDTO);

    void removeLesson(SectionRemoveLessonDTO sectionRemoveLessonDTO);

    SectionDTO findById(Long sectionId);
}
