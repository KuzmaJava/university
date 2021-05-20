package com.dyplom.dto;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LessonDTOForm {

    private int id;
    @NotNull
    @Size(min = 2, max = 45, message = "Lesson name should be not less than 2 and not more than 45!")
    private String name;
    @NotNull
    @Min(value = 1, message = "Class number can not be null!")
    @Max(value = 999, message = "Class number can not be more than 999!")
    private int classNumber;
    @NotNull
    @Size(min = 3, max = 75, message = "Teacher name should be more than 3 and less than 75!")
    private String teacherName;
    @NotNull
    @Size(min = 4, max = 4, message = "Group name should consist of 4 symbols!")
    private String groupName;
    @NotNull
    @Min(value = 2020, message = "Lesson year can not be less than 2020!")
    @Max(value = 2030, message = "Lesson year can not be more than 2030!")
    private int year;
    @NotNull
    @Min(value = 1, message = "Month can not be less than 1!")
    @Max(value = 12, message = "Month can not be more than 12!")
    private int month;
    @NotNull
    @Min(value = 1, message = "Day can not be less than 1!")
    @Max(value = 31, message = "Day can not be more than 31!")
    private int day;
    @NotNull
    @Min(value = 1, message = "Hour can not be less than 1!")
    @Max(value = 24, message = "Hour can not be more than 24!")
    private int hour;
    @NotNull
    @Min(value = 1, message = "Minute can not be less than 1!")
    @Max(value = 60, message = "Minute can not be more than 60!")
    private int minute;
}
