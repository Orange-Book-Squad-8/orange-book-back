package com.bookorange.api.controller;

import com.bookorange.api.domain.Lesson;
import com.bookorange.api.dto.lessonDto.LessonCreateDTO;
import com.bookorange.api.dto.lessonDto.LessonDTO;
import com.bookorange.api.enumerator.ContentType;
import com.bookorange.api.service.LessonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/lessons")
@AllArgsConstructor
public class LessonController {

    private final LessonService lessonService;

    @PostMapping(value = "/lessons")
    public ResponseEntity<LessonDTO> createLesson(@RequestBody LessonCreateDTO lessonCreateDTO) {
        try {
            Lesson lessonCreated = lessonService.create(lessonCreateDTO);
            return ResponseEntity.ok(new LessonDTO(lessonCreated));
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping
    public ResponseEntity<Lesson> findById(@RequestParam("lessonId") Long lessonId) {
        try {
            Lesson lesson = lessonService.findById(lessonId);
            return ResponseEntity.ok(lesson);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<Lesson>> findAll() {
        try {
            List<Lesson> allLessons = lessonService.findAll();
            return ResponseEntity.ok(allLessons);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping(value = "/topic/{topic}")
    public ResponseEntity<List<Lesson>> listByTopic(@PathVariable("topic") String topic) {
        try {
            List<Lesson> lessonsByTopic = lessonService.findByTopic(topic);
            return ResponseEntity.ok(lessonsByTopic);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping(value = "/content/{content}")
    public ResponseEntity<List<Lesson>> listByContentType(@PathVariable("content") ContentType content) {
        try {
            List<Lesson> lessonsByContentType = lessonService.findByContentType(content);
            return ResponseEntity.ok(lessonsByContentType);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping(value = "/update")
    public ResponseEntity<Lesson> updateLesson(@RequestBody LessonDTO lessonDTO) {
        try {
            Lesson lessonUpdate = lessonService.update(lessonDTO);
            return ResponseEntity.ok(lessonUpdate);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<Void> deleteLesson(@Valid @RequestParam("lessonId") Long lessonId) {
        try {
            lessonService.delete(lessonId);
            return ResponseEntity.ok().build();
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
