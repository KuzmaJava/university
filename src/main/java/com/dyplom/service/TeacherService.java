package com.dyplom.service;

import com.dyplom.model.Teacher;
import com.dyplom.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TeacherService {

    @Autowired
    TeacherRepository repo;

    public List<Teacher> getAll() {
        return (List<Teacher>) repo.findAll();
    }

    public void addTeacher(Teacher teacher) {
        repo.save(teacher);
    }

    public void removeTeacher(Teacher teacher) {
        repo.delete(teacher);
    }

    public Teacher getTeacherById(Integer teacherId) {
        return repo.getTeacherById(teacherId);
    }

    public void editTeacher(Teacher teacher) {
        repo.editTeacher(teacher.getTeacherName(), teacher.getPosition(), teacher.getTeacherId());
    }

    public Integer getTeacherIdByName(String name) {
        return repo.getTeacherIdByName(name);
    }

    public Integer getCountOfTeachers() {
        return repo.getCountOfTeachers();
    }

    public Teacher findByLogin(String username) {
        return repo.findBylogin(username);
    }

    public Integer getTeacherIdByTeacherLogin(String teacherLogin) {
        return repo.getTeacherIdByTeacherLogin(teacherLogin);
    }
}
