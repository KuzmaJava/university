package com.dyplom.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LessonDTO {
    @Id
    private int id;
    private String name;
    private int classNumber;
    @DateTimeFormat(pattern = "yyyy-dd-MM HH:mm:ss")
    private Date date;
    private String teacherName;
    private String groupName;
}