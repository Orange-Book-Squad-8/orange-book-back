package com.bookorange.api.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "watched_table")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class WatchedList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private AppUser appUser;

    @OneToOne(cascade = CascadeType.ALL)
    private Lesson lesson;

    private Boolean watched = false;

    private Boolean lastWatched = false;

    private Date removedAt;
}
