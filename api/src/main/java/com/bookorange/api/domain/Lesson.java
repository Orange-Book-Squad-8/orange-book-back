package com.bookorange.api.domain;

import com.bookorange.api.enumerator.ContentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Builder
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "field not found")
    private String title;
    @NotBlank(message = "field not found")
    private String description;
    @NotBlank(message = "field not found")
    private String author;
    @NotBlank(message = "field not found")
    @Pattern(regexp = "https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_\\+.~#?&//=]*)")
    private String link;

    @NotBlank(message = "field not found")
    private String topic;

    @NotNull
    private ContentType contentType;

    @Positive
    @Min(1)
    private Integer durationInMinutes;

    @Override
    public String toString() {
        return "Lesson{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", author='" + author + '\'' +
                ", link='" + link + '\'' +
                ", topic='" + topic + '\'' +
                ", contentType=" + contentType +
                ", durationInMinutes=" + durationInMinutes +
                '}';
    }
}
