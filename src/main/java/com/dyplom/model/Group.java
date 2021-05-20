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
@Table(name = "groups", schema = "public")
public class Group {
    @Id
    @Column(name = "group_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int groupId;
    @NotNull
    @Size(min = 4,max=4, message = "Group name should consist of 4 symbols!")
    @Column(name = "group_name")
    private String groupName;
}
