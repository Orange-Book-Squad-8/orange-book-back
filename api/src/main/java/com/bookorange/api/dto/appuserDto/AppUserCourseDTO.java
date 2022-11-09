package com.bookorange.api.dto.appuserDto;

import com.bookorange.api.domain.AppUser;
import com.bookorange.api.dto.courseDto.CourseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AppUserCourseDTO {

    private List<CourseDTO> subscribedCourses = new ArrayList<>();

    private List<CourseDTO> archivedCourses = new ArrayList<>();

    private List<CourseDTO> myCourses = new ArrayList<>();

    private List<Long> watchedLesson;

    public AppUserCourseDTO(AppUser user, List<Long> watchedLesson) {
        this.subscribedCourses = user.getSubscribedCourses().stream().map(CourseDTO::new).toList();
        this.archivedCourses = user.getArchivedCourses().stream().map(CourseDTO::new).toList();
        this.myCourses = user.getMyCourses().stream().map(CourseDTO::new).toList();
        this.watchedLesson = watchedLesson;
    }
}
