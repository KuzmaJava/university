package com.dyplom.repository;

import com.dyplom.model.Group;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface GroupRepository extends CrudRepository<Group, Long> {


    @Query("SELECT groupId FROM Group WHERE groupName = ?1")
    Integer getGroupIdByName(String groupName);

    @Query("FROM Group WHERE groupId =?1")
    Group getGroupById(Integer groupId);

    @Modifying
    @Query("UPDATE Group SET groupName = ?1 WHERE groupId = ?2")
    void changeGroupName(String groupName, Integer groupId);

    @Query("SELECT COUNT(G) FROM Group G")
    Integer getCountOfGroups();
}
