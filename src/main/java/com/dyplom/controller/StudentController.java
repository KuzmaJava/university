package com.dyplom.controller;


import com.dyplom.dto.LessonDTO;
import com.dyplom.dto.StudentDTO;
import com.dyplom.model.Lesson;
import com.dyplom.model.Student;
import com.dyplom.service.GroupService;
import com.dyplom.service.LessonService;
import com.dyplom.service.StudentService;
import com.dyplom.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/studentViews")
public class StudentController {

    private static final String STUDENT_MODEL = "student";
    @Autowired
    StudentService studentService;
    @Autowired
    TeacherService teacherService;
    @Autowired
    GroupService groupService;
    @Autowired
    LessonService lessonService;


    @GetMapping("students")
    public String showStudents(Model model) {
        List<StudentDTO> students = studentService.getAllStudentDtos();
        model.addAttribute("students", students);
        return "studentViews/studentsPage";
    }

    @GetMapping("schedule")
    public String schedule(Model model) {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String studentLogin = loggedInUser.getName();
        Integer groupId = studentService.getGroupIdByStudentLogin(studentLogin);
        List<LessonDTO> lessons = lessonService.getScheduleForStudent(groupId);
        model.addAttribute("lessons", lessons);
        return "studentViews/studentSchedule";
    }

    @GetMapping("studentPersonalArea")
    public String studentPersonalArea(Model model) {
        return "studentViews/studentPersonalArea";
    }

    @GetMapping("editStudent")
    public String editStudent(Model model, @RequestParam(value = "studentId") Integer studentId) {
        StudentDTO studentDTO = studentService.getStudentDtoById(studentId);
        model.addAttribute(STUDENT_MODEL, studentDTO);
        return "studentViews/editStudent";
    }

    @GetMapping("addStudent")
    public String addStudent(Model model) {
        model.addAttribute(STUDENT_MODEL, new StudentDTO());
        return "studentViews/addStudent";
    }

    @GetMapping(value = "studentDescription")
    public String readStudent(Model model, @RequestParam(value = "studentId") Integer studentId) {
        StudentDTO studentDTO = studentService.getStudentDtoById(studentId);
        model.addAttribute("studentDto", studentDTO);
        return "studentViews/studentDescription";
    }

    @GetMapping("removeStudent")
    public String removeStudent(Model model, @RequestParam(value = "studentId") Integer studentId) {
        studentService.removeStudent(studentService.getStudentById(studentId));
        return "studentViews/removeStudent";
    }
}
