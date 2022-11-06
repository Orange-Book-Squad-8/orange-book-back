package com.bookorange.api.service.implementation;

import com.bookorange.api.dto.sectionDto.CreateSectionDTO;
import com.bookorange.api.dto.sectionDto.SectionAddLessonDTO;
import com.bookorange.api.dto.sectionDto.SectionDTO;
import com.bookorange.api.dto.sectionDto.SectionRemoveLessonDTO;
import com.bookorange.api.repository.SectionRepository;
import com.bookorange.api.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SectionServiceImp implements SectionService {

    private final SectionRepository sectionRepository;

    @Autowired
    public SectionServiceImp(SectionRepository sectionRepository) {
        this.sectionRepository = sectionRepository;
    }

    @Override
    public SectionDTO create(CreateSectionDTO createSectionDTO) {
        return null;
    }

    @Override
    public SectionDTO update(SectionDTO sectionDTO) {
        return null;
    }

    @Override
    public void delete(Long sectionId) {

    }

    @Override
    public void addLesson(SectionAddLessonDTO sectionAddLessonDTO) {

    }

    @Override
    public void removeLesson(SectionRemoveLessonDTO sectionRemoveLessonDTO) {

    }

    @Override
    public SectionDTO findById(Long sectionId) {
        return null;
    }
}
