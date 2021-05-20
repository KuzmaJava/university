package com.dyplom.repository;

import com.dyplom.dto.StudentDTO;
import com.dyplom.model.Student;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentRepository extends CrudRepository<Student, Long> {

    @Query("SELECT NEW StudentDTO (S.studentId, S.studentName, S.studentSurname, S.studentAge, S.entryYear," +
            "S.graduateYear, S.facultyName, G.groupName) FROM Student S LEFT JOIN Group G ON S.groupId=G.groupId ORDER BY S.studentId")
    List<StudentDTO> getAllStudentDtos();

    @Modifying
    @Query("UPDATE Student SET studentName = ?1,studentSurname = ?2," +
            "studentAge = ?3, entryYear = ?4, graduateYear = ?5, facultyName = ?6, groupId = ?7 WHERE studentId = ?8")
    void editStudent(String studentName, String studentSurname, Integer studentAge, Integer entryYear,
                     Integer graduateYear, String facultyName, Integer groupId, Integer studentId);

    @Query("SELECT NEW StudentDTO (S.studentId, S.studentName, S.studentSurname, S.studentAge, S.entryYear," +
            "S.graduateYear, S.facultyName, G.groupName) FROM Student S LEFT JOIN Group G ON S.groupId=G.groupId WHERE S.studentId=?1")
    StudentDTO getStudentDtoById(Integer studentId);

    @Query("FROM Student WHERE studentId =?1")
    Student getStudentById(Integer studentId);

    @Modifying
    @Query("DELETE FROM Student S WHERE S.groupId =?1")
    void deleteAllDataWithSpecifiedStudent(Integer groupId);

    @Query("SELECT COUNT(S) FROM Student S")
    Integer getCountOfStudents();

    @Query("FROM Student WHERE studentLogin=?1")
    Student findByLogin(String username);

    @Query("SELECT s.groupId FROM Student s WHERE s.studentLogin=?1")
    Integer getGroupIdByStudentLogin(String studentLogin);
}