package com.dyplom.repository;


import com.dyplom.model.Teacher;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TeacherRepository extends CrudRepository<Teacher, Long> {

    @Query("FROM Teacher WHERE teacherId = ?1")
    Teacher getTeacherById(Integer teacherId);

    @Modifying
    @Query("UPDATE Teacher SET teacherName = ?1, position = ?2 WHERE teacherId = ?3")
    void editTeacher(String teacherName, String position, Integer teacherId);

    @Query("SELECT teacherId FROM Teacher WHERE teacherName = ?1")
    Integer getTeacherIdByName(String name);

    @Query("SELECT COUNT(T) FROM Teacher T")
    Integer getCountOfTeachers();

    @Query("FROM Teacher WHERE teacherLogin=?1")
    Teacher findBylogin(String username);

    @Query("SELECT teacherId FROM Teacher WHERE teacherLogin= ?1")
    Integer getTeacherIdByTeacherLogin(String teacherLogin);
}