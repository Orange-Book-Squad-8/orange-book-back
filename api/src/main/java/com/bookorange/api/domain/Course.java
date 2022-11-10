package com.bookorange.api.domain;

import com.bookorange.api.enumerator.Difficulty;
import com.bookorange.api.enumerator.StackCategories;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Builder
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "field not found")
    private String title;

    @NotBlank(message = "field not found")
    private String description;

    @NotBlank(message = "field not found")
    private String creator;

    @NotNull(message = "cannot be null")
    private StackCategories category;

    @NotNull(message = "cannot be null")
    private Difficulty difficulty;

    private Boolean visible = true;

    @OneToMany
    private List<Section> sections = new ArrayList<>();

    public Integer getDuration() {
        return sections.stream().mapToInt(Section::getDuration).sum();
    }

    public void addSection(Section section) {
        if (section == null) throw new IllegalArgumentException("section cannot be null");
        if (sections.contains(section)) throw new IllegalArgumentException("section already exists");
        if (sections.stream().map(Section::getName).toList().contains(section.getName()))
            throw new IllegalArgumentException("section name already exists in this course");
        sections.add(section);
    }

    public void removeSection(Section section) {
        if (section == null) throw new IllegalArgumentException("section cannot be null");
        if (!sections.contains(section)) throw new IllegalArgumentException("section does not exist");
        sections.remove(section);
    }

}
