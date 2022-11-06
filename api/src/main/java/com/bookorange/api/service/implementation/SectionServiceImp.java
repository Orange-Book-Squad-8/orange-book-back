package com.bookorange.api.service.implementation;

import com.bookorange.api.domain.Section;
import com.bookorange.api.dto.sectionDto.SectionCreateDTO;
import com.bookorange.api.dto.sectionDto.SectionDTO;
import com.bookorange.api.dto.sectionDto.SectionEditLessonDTO;
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
    public Section create(SectionCreateDTO createSection) {
        return null;
    }

    @Override
    public Section update(SectionDTO sectionDTO) {
        return null;
    }

    @Override
    public void delete(Long sectionId) {

    }

    @Override
    public void addLesson(SectionEditLessonDTO sectionEditLessonDTO) {

    }

    @Override
    public void removeLesson(SectionEditLessonDTO sectionEditLessonDTO) {

    }

    @Override
    public Section findById(Long sectionId) {
        return null;
    }
}
