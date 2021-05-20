package com.dyplom.controller;


import com.dyplom.dto.LessonDTO;
import com.dyplom.model.Teacher;
import com.dyplom.service.LessonService;
import com.dyplom.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/teacherViews")
public class TeacherController {

    private static final String TEACHER_MODEL = "teacher";

    @Autowired
    LessonService lessonService;

    @Autowired
    TeacherService teacherService;

    @GetMapping("schedule")
    public String schedule(Model model) {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String teacherLogin = loggedInUser.getName();
        Integer teacherId = teacherService.getTeacherIdByTeacherLogin(teacherLogin);
        List<LessonDTO> lessons = lessonService.getScheduleForTeacher(teacherId);

        model.addAttribute("lessons", lessons);
        return "/teacherViews/teacherSchedule";
    }

    @GetMapping("teacherPersonalArea")
    public String teacherPersonalArea(Model model) {
        return "/teacherViews/teacherPersonalArea";
    }

    @GetMapping("addTeacher")
    public String addTeacher(Model model) {
        model.addAttribute(TEACHER_MODEL, new Teacher());
        return "teacherViews/addTeacher";
    }

    @GetMapping("teachers")
    public String showTeachers(Model model) {
        List<Teacher> teacherDTOs = teacherService.getAll();
        model.addAttribute("teachers", teacherDTOs);
        return "/teacherViews/teachersPage";
    }

    @GetMapping("removeTeacher")
    public String removeTeacher(Model model, @RequestParam(value = "teacherId") Integer teacherId) {
        teacherService.removeTeacher(teacherService.getTeacherById(teacherId));
        return "/teacherViews/removeTeacher";
    }

    @GetMapping("editTeacher")
    public String editTeacher(Model model, @RequestParam(value = "teacherId") Integer teacherId) {
        Teacher teacher = teacherService.getTeacherById(teacherId);
        model.addAttribute(TEACHER_MODEL, teacher);
        return "/teacherViews/editTeacher";
    }

    @GetMapping(value = "teacherDescription")
    public String readTeacher(Model model, @RequestParam(value = "teacherId") Integer teacherId) {

        Teacher teacher = teacherService.getTeacherById(teacherId);
        model.addAttribute(TEACHER_MODEL, teacher);
        return "/teacherViews/teacherDescription";
    }

    @PostMapping("successTeacherAddition")
    public String addTeacher(@ModelAttribute("teacher") @Validated Teacher teacher, Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute(TEACHER_MODEL, teacher);
            return "/teacherViews/addTeacher";
        }

        teacherService.addTeacher(teacher);
        return "/teacherViews/successTeacherAddition";
    }

    @PostMapping("successTeacherEdition")
    public String editTeacherName(@ModelAttribute("teacher") Teacher teacher) {
        teacherService.editTeacher(teacher);
        return "/teacherViews/successTeacherEdition";
    }
}
