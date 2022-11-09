package com.bookorange.api.dto.appuserDto;

import com.bookorange.api.domain.AppUser;
import com.bookorange.api.domain.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AppUserCourseDTO {

    private List<Course> subscribedCourses = new ArrayList<>();

    private List<Course> archivedCourses = new ArrayList<>();

    private List<Course> myCourses = new ArrayList<>();

    private List<Long> watchedLesson;
    
    public AppUserCourseDTO(AppUser user, List<Long> watchedLesson) {
        this.subscribedCourses = user.getSubscribedCourses();
        this.archivedCourses = user.getArchivedCourses();
        this.myCourses = user.getMyCourses();
        this.watchedLesson = watchedLesson;
    }
}
