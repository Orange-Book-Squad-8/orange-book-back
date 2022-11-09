package com.bookorange.api.controller;

import com.bookorange.api.domain.Lesson;
import com.bookorange.api.domain.Section;
import com.bookorange.api.dto.sectionDto.SectionAddLessonDTO;
import com.bookorange.api.dto.sectionDto.SectionDTO;
import com.bookorange.api.dto.sectionDto.SectionEditLessonDTO;
import com.bookorange.api.service.LessonService;
import com.bookorange.api.service.SectionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/sections")
@AllArgsConstructor
public class SectionController {

    private final SectionService sectionService;
    private final LessonService lessonService;


    @PutMapping(value = "/update")
    public ResponseEntity<Section> updateSection(@Valid @RequestBody SectionDTO sectionDTO) {
        try {
            Section sectionUpdate = sectionService.update(sectionDTO);
            return ResponseEntity.ok(sectionUpdate);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping(value = "/new_lesson")
    public ResponseEntity<Section> addLesson(@RequestBody SectionAddLessonDTO sectionEditLessonDTO) {
        try {
            Lesson lesson = lessonService.findById(sectionEditLessonDTO.getLessonId());
            sectionService.addLesson(new SectionEditLessonDTO(sectionEditLessonDTO.getSectionId(), lesson));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping(value = "/remove_lesson")
    public ResponseEntity<Void> removeLesson(@RequestBody SectionAddLessonDTO sectionEditLessonDTO) {
        try {
            Lesson lesson = lessonService.findById(sectionEditLessonDTO.getLessonId());
            sectionService.removeLesson(new SectionEditLessonDTO(sectionEditLessonDTO.getSectionId(), lesson));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
