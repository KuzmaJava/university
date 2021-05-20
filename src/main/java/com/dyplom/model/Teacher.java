package com.dyplom.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "teachers", schema = "public")
public class Teacher {
    @Id
    @Column(name = "teacher_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int teacherId;
    @Column(name = "teacher_name")
    @NotNull
    @Size(min = 4, max = 75, message = "Teacher name should be not less than 4 symbols and not more than 75 symbols!")
    private String teacherName;

    @Column(name = "teacher_login")
    private String teacherLogin;

    @Column(name = "teacher_password")
    private String teacherPassword;

    @Column(name = "teacher_role")
    private String teacherrole;

    @Column(name = "position")
    @NotNull
    @Size(min = 3, max = 50, message = "Teacher position should be not less than 3 symbols and not more than 50 symbols!")
    private String position;

    public Teacher(String teacherName, String position) {
        this.teacherName = teacherName;
        this.position = position;
    }
}