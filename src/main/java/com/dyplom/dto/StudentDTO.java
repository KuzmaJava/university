package com.dyplom.dto;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
    @Id
    private int studentId;
    @NotNull
    @Size(min = 2, max = 30, message = "Name should consist of 2 to 30 symbols!")
    private String studentName;
    @NotNull
    @Size(min = 2, max = 30, message = "Surname should consist of 2 to 30 symbols!")
    private String studentSurname;
    @NotNull
    @Min(value = 10, message = "Student age should be more than 10!")
    private int studentAge;
    @NotNull
    @Min(value = 1900, message = "Entry year should be more than 1900!")
    @Max(value = 2021, message = "Entry year should be less than 2021!")
    private int entryYear;
    @NotNull
    @Min(value = 2020, message = "Graduate year should be not less than 2020!")
    private int graduateYear;
    @NotNull
    @Size(min = 3, message = "Faculty name should consist of minimum 3 symbols!")
    private String facultyName;
    @NotNull
    @Size(min = 4, max = 4,message = "Group name should consist of 4 symbols!")
    private String groupName;
}
