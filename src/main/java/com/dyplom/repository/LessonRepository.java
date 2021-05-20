package com.dyplom.repository;

import com.dyplom.dto.LessonDTO;
import com.dyplom.model.Lesson;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface LessonRepository extends CrudRepository<Lesson, Long> {

    @Query(" SELECT NEW LessonDTO (L.lessonId, L.lessonName, L.classNumber, L.date, T.teacherName, " +
            "G.groupName) FROM Lesson L LEFT JOIN Teacher T ON L.teacherId = T.teacherId LEFT JOIN Group G ON " +
            "L.groupId=G.groupId WHERE L.lessonId = ?1")
    LessonDTO getLessonDtoById(int lessonId);

    @Query(" SELECT NEW LessonDTO (L.lessonId, L.lessonName, L.classNumber, L.date, T.teacherName, " +
            "G.groupName) FROM Lesson L LEFT JOIN Teacher T ON L.teacherId = T.teacherId LEFT JOIN Group G ON " +
            "L.groupId=G.groupId  ORDER BY L.lessonId")
    List<LessonDTO> getAllLessonDtos();

    @Modifying
    @Query("DELETE FROM Lesson L WHERE L.groupId =?1")
    void deleteAllDataWithSpecifiedGroup(Integer groupId);

    @Query("FROM Lesson WHERE lessonId =?1")
    Lesson getLessonById(Integer lessonId);

    @Modifying
    @Query("UPDATE Lesson SET lessonName = ?1,classNumber = ?2, date = ?3, teacherId = ?4, groupId =?5 WHERE lessonId = ?6")
    void editLesson(String lessonName, Integer classNumber, Date date, Integer teacherId, Integer groupId, Integer lessonId);

    @Query("SELECT COUNT(L) FROM Lesson L")
    Integer getCountOfLessons();

    @Query(" SELECT NEW LessonDTO (L.lessonId, L.lessonName, L.classNumber, L.date, T.teacherName, " +
            "G.groupName) FROM Lesson L LEFT JOIN Teacher T ON L.teacherId = T.teacherId LEFT JOIN Group G ON " +
            "L.groupId=G.groupId WHERE L.groupId=?1 ORDER BY L.lessonId")
    List<LessonDTO> getScheduleForStudent(Integer groupId);

    @Query(" SELECT NEW LessonDTO (L.lessonId, L.lessonName, L.classNumber, L.date, T.teacherName, " +
            "G.groupName) FROM Lesson L LEFT JOIN Teacher T ON L.teacherId = T.teacherId LEFT JOIN Group G ON " +
            "L.groupId=G.groupId WHERE L.teacherId=?1 ORDER BY L.lessonId")
    List<LessonDTO> getScheduleForTeacher(Integer teacherId);
}
