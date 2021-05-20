package com.dyplom.service;

import com.dyplom.model.Group;
import com.dyplom.repository.GroupRepository;
import com.dyplom.repository.LessonRepository;
import com.dyplom.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GroupService {

    @Autowired
    GroupRepository repo;

    @Autowired
    LessonRepository lessonRepo;

    @Autowired
    StudentRepository studentRepo;

    public Integer getGroupIdByName(String groupName) {
        return repo.getGroupIdByName(groupName);
    }

    public List<Group> getAll() {
        return (List<Group>) repo.findAll();
    }

    public Group getGroupById(Integer groupId) {
        return repo.getGroupById(groupId);
    }

    public void addGroup(Group group) {
        repo.save(group);
    }

    public void changeGroupName(Group group) {
        repo.changeGroupName(group.getGroupName(), group.getGroupId());
    }

    public void removeGroup(Group group) {
        lessonRepo.deleteAllDataWithSpecifiedGroup(group.getGroupId());
        studentRepo.deleteAllDataWithSpecifiedStudent(group.getGroupId());
        repo.delete(group);
    }

    public Integer getCountOfGroups() {
        return repo.getCountOfGroups();
    }
}
