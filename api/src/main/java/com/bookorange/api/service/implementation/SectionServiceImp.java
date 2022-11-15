package com.bookorange.api.service.implementation;

import com.bookorange.api.domain.Section;
import com.bookorange.api.dto.sectionDto.SectionCreateDTO;
import com.bookorange.api.dto.sectionDto.SectionDTO;
import com.bookorange.api.dto.sectionDto.SectionEditLessonDTO;
import com.bookorange.api.handler.exception.ObjectNotFoundException;
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
        Section section = new Section();
        section.setName(createSection.getName());
        return sectionRepository.save(section);
    }

    @Override
    public Section update(SectionDTO sectionDTO) {
        Section section = findById(sectionDTO.getId());
        section.setName(sectionDTO.getName());
        return sectionRepository.save(section);
    }

    @Override
    public void delete(Long sectionId) {
        sectionRepository.deleteById(sectionId);
    }


    @Override
    public void addLesson(SectionEditLessonDTO sectionEditLessonDTO) {
        Section section = findById(sectionEditLessonDTO.getSectionId());
        section.addLesson(sectionEditLessonDTO.getLesson());
        sectionRepository.save(section);
    }

    @Override
    public void removeLesson(SectionEditLessonDTO sectionEditLessonDTO) {
        Section section = findById(sectionEditLessonDTO.getSectionId());
        section.removeLesson(sectionEditLessonDTO.getLesson());
        sectionRepository.save(section);
    }

    @Override
    public Section findById(Long sectionId) {
        return sectionRepository.findById(sectionId).orElseThrow(() -> new ObjectNotFoundException("Could not find section"));
    }

}
