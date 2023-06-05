package edu.hdu.chat.controller;

import edu.hdu.chat.dto.Group;
import edu.hdu.chat.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class GroupControllers {
    @Autowired
    GroupService groupService;

    @RequestMapping("/searchGroup")
    public List<Group> searchGroupByName(@RequestParam("name") String groupName) {
        return groupService.serchGroup(groupName);
    }

    @RequestMapping("/addGroup")
    public void addGroup(@RequestParam("gId") Integer gId, @RequestParam("uId") Integer uId) {
        groupService.addGroup(gId, uId);
    }


}
