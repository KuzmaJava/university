package com.dyplom.controller;


import com.dyplom.model.Group;
import com.dyplom.service.GroupService;
import com.dyplom.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/groupViews")
public class GroupController {

    private static final String GROUP_MODEL = "group";

    @Autowired
    GroupService groupServices;

    @Autowired
    StudentService studentService;

    @GetMapping("groups")
    public String groupsPage(Model model) {
        List<Group> groupList = groupService.getAll();
        model.addAttribute("groups", groupList);
        return "/groupViews/groupsPage";
    }

    @PostMapping("successGroupEdition")
    public String editGroup(@ModelAttribute("group") Group group) {
        Group updatedGroup = groupService.getGroupById(group.getGroupId());
        updatedGroup.setGroupName(group.getGroupName());
        groupService.changeGroupName(updatedGroup);
        return "/groupViews/successGroupEdition";
    }

    @GetMapping("editGroup")
    public String editGroup(Model model, @RequestParam(value = "groupId") Integer groupId) {
        Group group = groupService.getGroupById(groupId);
        model.addAttribute(GROUP_MODEL, group);
        return "/groupViews/editGroup";
    }

    @PostMapping("successGroupAddition")
    public String addGroup(@ModelAttribute("group") @Validated Group group, Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute(GROUP_MODEL, group);
            return "/groupViews/addGroup";
        }

        groupService.addGroup(group);
        return "/groupViews/successGroupAddition";
    }

    @GetMapping("addGroup")
    public String addStudent(Model model) {
        model.addAttribute(GROUP_MODEL, new Group());
        return "/groupViews/addGroup";
    }

    @GetMapping("groupDescription")
    public String readGroup(Model model, @RequestParam(value = "groupId") Integer groupId) {
        model.addAttribute(GROUP_MODEL, groupService.getGroupById(groupId));
        return "/groupViews/groupDescription";
    }

    @GetMapping("removeGroup")
    public String removeStudent(Model model, @RequestParam(value = "groupId") Integer groupId) {
        groupService.removeGroup(groupService.getGroupById(groupId));
        return "/groupViews/removeGroup";
    }
}
