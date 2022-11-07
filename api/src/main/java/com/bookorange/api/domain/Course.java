package com.bookorange.api.domain;

import com.bookorange.api.enumerator.Difficulty;
import com.bookorange.api.enumerator.StackCategories;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String description;

    private String creator;

    private StackCategories category;

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
        sections.add(section);
    }

    public void removeSection(Section section) {
        if (section == null) throw new IllegalArgumentException("section cannot be null");
        if (!sections.contains(section)) throw new IllegalArgumentException("section does not exist");
        sections.remove(section);
    }

}
