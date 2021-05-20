package com.dyplom.service;

import com.dyplom.dto.StudentDTO;
import com.dyplom.model.Student;
import com.dyplom.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StudentService {

    @Autowired
    StudentRepository repo;

    public List<StudentDTO> getAllStudentDtos() {
        return repo.getAllStudentDtos();
    }

    public void addStudent(Student student) {
        repo.save(student);
    }

    public void editStudent(Student student) {
        repo.editStudent(student.getStudentName(), student.getStudentSurname(), student.getStudentAge(),
                student.getEntryYear(), student.getGraduateYear(), student.getFacultyName(),
                student.getGroupId(), student.getStudentId());
    }

    public StudentDTO getStudentDtoById(Integer studentId) {
        return repo.getStudentDtoById(studentId);
    }

    public void removeStudent(Student student) {
        repo.delete(student);
    }

    public Student getStudentById(Integer studentId) {
        return repo.getStudentById(studentId);
    }

    public Integer getCountOfStudents() {
        return repo.getCountOfStudents();
    }

    public Student findByLogin(String username) {
        return repo.findByLogin(username);
    }

    public Integer getGroupIdByStudentLogin(String studentLogin) {
        return repo.getGroupIdByStudentLogin(studentLogin);
    }
}
