package com.dyplom.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "lessons", schema = "public")
public class Lesson {
    @Id
    @Column(name = "lesson_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int lessonId;
    @Column(name = "lesson_name")
    private String lessonName;
    @Column(name = "class_number")
    private int classNumber;
    @Column(name = "date")
    private Date date;
    @Column(name = "teacher_id")
    private int teacherId;
    @Column(name = "group_id")
    private int groupId;

    public Lesson(String lessonName, Integer classNumber, Timestamp date, Integer teacherId, Integer groupId) {
        this.lessonName = lessonName;
        this.classNumber = classNumber;
        this.date = date;
        this.teacherId = teacherId;
        this.groupId = groupId;
    }
}
