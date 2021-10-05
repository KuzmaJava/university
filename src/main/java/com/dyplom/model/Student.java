package com.dyplom.model;

import lombok.*;

import javax.persistence.*;

@Entity
@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "students", schema = "public")
public class Student {
    @Id 
    @Column(name = "student_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int studentId;
    @Column(name = "student_name")
    private String studentName;

    @Column(name = "student_login")
    private String studentLogin;

    @Column(name = "student_password")
    private String studentPassword;

    @Column(name = "student_role")
    private String studentRole;

    @Column(name = "student_surname")
    private String studentSurname;
    @Column(name = "student_age")
    private int studentAge;
    @Column(name = "entry_year")
    private int entryYear;
    @Column(name = "graduate_year")
    private int graduateYear;
    @Column(name = "faculty_name")
    private String facultyName;
    @Column(name = "group_id")
    private int groupId;
}