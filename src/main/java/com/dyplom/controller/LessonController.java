package com.dyplom.controller;


import com.dyplom.dto.LessonDTO;
import com.dyplom.dto.LessonDTOForm;
import com.dyplom.model.Lesson;
import com.dyplom.service.GroupService;
import com.dyplom.service.LessonService;
import com.dyplom.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@Controller
@RequestMapping("/lessonViews")
public class LessonController {

    private static final String LESSON_MODEL = "lesson";

    @Autowired
    LessonService lessonService;

    @Autowired
    TeacherService teacherService;

    @Autowired
    GroupService groupService;


    @GetMapping("editLesson")
    public String editLesson(Model model, @RequestParam(value = "lessonId") Integer lessonId) {
        LessonDTO lessonDTO = lessonService.getLessonDtoById(lessonId);
        model.addAttribute(LESSON_MODEL, lessonDTO);
        return "/lessonViews/editLesson";
    }

    @GetMapping("lessons")
    public String lessonsPage(Model model) {
        List<LessonDTO> lessonDTOList = lessonService.getAllLessonDtos();
        model.addAttribute("lessons", lessonDTOList);
        return "/lessonViews/lessonsPage";
    }


    @PostMapping("successLessonEdition")
    public String editLessonName(@ModelAttribute("lesson") LessonDTO lessonDTO) {
        Lesson lesson = new Lesson(lessonDTO.getId(), lessonDTO.getName(), lessonDTO.getClassNumber(),
                lessonDTO.getDate(), teacherService.getTeacherIdByName(lessonDTO.getTeacherName()),
                groupService.getGroupIdByName(lessonDTO.getGroupName()));
        lessonService.editLesson(lesson);
        return "/lessonViews/successLessonEdition";
    }

    @PostMapping("successLessonAddition")
    public String addLesson(@ModelAttribute("lesson") @Validated LessonDTOForm lessonDTOForm, Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute(LESSON_MODEL, lessonDTOForm);
            return "/lessonViews/addLesson";
        }

        Timestamp lessonDate = new Timestamp(lessonDTOForm.getYear() - 1900, lessonDTOForm.getMonth() + 1,
                lessonDTOForm.getDay(), lessonDTOForm.getHour(), lessonDTOForm.getMinute(), 00, 00);
        Lesson lesson = new Lesson(lessonDTOForm.getName(), lessonDTOForm.getClassNumber(), lessonDate, teacherService.getTeacherIdByName(lessonDTOForm.getTeacherName()),
                groupService.getGroupIdByName(lessonDTOForm.getGroupName()));
        lessonService.addLesson(lesson);
        return "/lessonViews/successLessonAddition";
    }

    @GetMapping(value = "lessonDescription")
    public String readStudent(Model model, @RequestParam(value = "lessonId") Integer lessonId) {
        LessonDTO lessonDTO = lessonService.getLessonDtoById(lessonId);
        model.addAttribute(LESSON_MODEL, lessonDTO);
        return "/lessonViews/lessonDescription";
    }

    @GetMapping("removeLesson")
    public String removeLesson(Model model, @RequestParam(value = "lessonId") int lessonId) {
        lessonService.removeLesson(lessonService.getLessonById(lessonId));
        return "/lessonViews/removeLesson";
    }

    @GetMapping("addLesson")
    public String addStudent(Model model) {
        model.addAttribute(LESSON_MODEL, new LessonDTOForm());
        return "/lessonViews/addLesson";
    }

}



