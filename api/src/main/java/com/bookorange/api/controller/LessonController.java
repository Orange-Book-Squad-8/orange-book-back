package com.bookorange.api.controller;

import com.bookorange.api.domain.Lesson;
import com.bookorange.api.dto.lessonDto.LessonCreateDTO;
import com.bookorange.api.dto.lessonDto.LessonDTO;
import com.bookorange.api.enumerator.ContentType;
import com.bookorange.api.service.LessonService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;


@RestController
@RequestMapping(value = "/lessons")
@AllArgsConstructor
@CrossOrigin(origins = "http://127.0.0.1:5173")
public class LessonController {

    private final LessonService lessonService;


    @ApiOperation("API responsável por criar uma lição")
    @PostMapping(value = "/create")
    public ResponseEntity<LessonDTO> createLesson(@Valid @RequestBody LessonCreateDTO lessonCreateDTO) {
        try {
            Lesson lessonCreated = lessonService.create(lessonCreateDTO);
            URI uri = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/create").buildAndExpand(lessonCreated.getId()).toUri();
            return ResponseEntity.created(uri).build();
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @ApiOperation("API responsável por buscar uma única lição")
    @GetMapping
    public ResponseEntity<Lesson> findById(@RequestParam("lessonId") Long lessonId) {
        try {
            Lesson lesson = lessonService.findById(lessonId);
            return ResponseEntity.ok(lesson);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @ApiOperation("API responsável por buscar todas as lições")
    @GetMapping(value = "/all")
    public ResponseEntity<List<Lesson>> findAll() {
        try {
            List<Lesson> allLessons = lessonService.findAll();
            return ResponseEntity.ok(allLessons);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @ApiOperation("API responsável por buscar as lições por determinado tópico")
    @GetMapping(value = "/topic/{topic}")
    public ResponseEntity<List<Lesson>> listByTopic(@PathVariable("topic") String topic) {
        try {
            List<Lesson> lessonsByTopic = lessonService.findByTopic(topic);
            return ResponseEntity.ok(lessonsByTopic);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @ApiOperation("API responsável por buscar as lições por determinado tipo (livro, curso, artigo)")
    @GetMapping(value = "/content/{content}")
    public ResponseEntity<List<Lesson>> listByContentType(@PathVariable("content") ContentType content) {
        try {
            List<Lesson> lessonsByContentType = lessonService.findByContentType(content);
            return ResponseEntity.ok(lessonsByContentType);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @ApiOperation("API responsável por atualizar uma lição")
    @PutMapping(value = "/update")
    public ResponseEntity<Lesson> updateLesson(@Valid @RequestBody LessonDTO lessonDTO) {
        try {
            Lesson lessonUpdate = lessonService.update(lessonDTO);
            return ResponseEntity.ok(lessonUpdate);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @ApiOperation("API responsável por remover uma lição")
    @DeleteMapping(value = "/delete")
    public ResponseEntity<Void> deleteLesson(@RequestParam("lessonId") Long lessonId) {
        try {
            lessonService.delete(lessonId);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }


}
