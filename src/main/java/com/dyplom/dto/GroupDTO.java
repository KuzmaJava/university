package com.dyplom.dto;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GroupDTO {
    @Id
    private int groupId;
    private String groupName;
}