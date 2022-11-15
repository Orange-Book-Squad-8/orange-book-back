package com.bookorange.api.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "TB_WATCHED")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserWatchedLesson {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private AppUser appUser;

    @OneToOne(cascade = CascadeType.ALL)
    private Lesson lesson;

    private Boolean watched = false;

    private LocalDate removedAt;
}
