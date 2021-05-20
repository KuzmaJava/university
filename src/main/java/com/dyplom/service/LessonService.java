package com.dyplom.service;


import com.dyplom.dto.LessonDTO;
import com.dyplom.model.Lesson;
import com.dyplom.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LessonService {

    @Autowired
    LessonRepository repo;

    public void removeLesson(Lesson lesson) {
        repo.delete(lesson);
    }

    public LessonDTO getLessonDtoById(Integer lessonId) {
        return repo.getLessonDtoById(lessonId);
    }

    public List<LessonDTO> getAllLessonDtos() {
        return repo.getAllLessonDtos();
    }

    public void addLesson(Lesson lesson) {
        repo.save(lesson);
    }

    public Lesson getLessonById(int lessonId) {
        return repo.getLessonById(lessonId);
    }

    public void editLesson(Lesson lesson) {
        repo.editLesson(lesson.getLessonName(), lesson.getClassNumber(), lesson.getDate(), lesson.getTeacherId(), lesson.getGroupId(),
                lesson.getLessonId());
    }

    public Integer getCountOfLessons() {
        return repo.getCountOfLessons();
    }

    public List<LessonDTO> getScheduleForStudent(Integer groupId) {
        return repo.getScheduleForStudent(groupId);
    }


    public List<LessonDTO> getScheduleForTeacher(Integer teacherId) {
        return repo.getScheduleForTeacher(teacherId);
    }
}
