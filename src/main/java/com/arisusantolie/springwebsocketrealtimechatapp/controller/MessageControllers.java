package com.arisusantolie.springwebsocketrealtimechatapp.controller;

import com.arisusantolie.springwebsocketrealtimechatapp.dto.*;
import com.arisusantolie.springwebsocketrealtimechatapp.service.ChatService;
import com.arisusantolie.springwebsocketrealtimechatapp.service.MessageService;
import com.arisusantolie.springwebsocketrealtimechatapp.service.UserAndGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class MessageControllers {

    @Autowired
    ChatService chatService;

    @Autowired
    MessageService messageService;

    @Autowired
    UserAndGroupService userAndGroupService;

    @MessageMapping("/chat/group")
    public void sendMessageToGroup(GroupMessage message) {
        System.out.println(message.toString());
        chatService.sendMessageGroup(message);
    }
    // ws调用的发送
    @MessageMapping("/chat/personal")
    public void sendMessagePersonal(Message message) {
        System.out.println(message.toString());
        chatService.sendMessage(message);
    }

    @RequestMapping("/getHistoryMessage")
    public List<Message> getHistoryMessage(@RequestParam("from") Integer from, @RequestParam("to") Integer to){
        return chatService.getHistoryMessage(from, to);
    }

    @RequestMapping("getHistoryGroupMessage")
    public List<GroupMessage> getHistoryGroupMessage(@RequestParam("g_id") Integer gId) {
        return chatService.getHistoryGroupMessage(gId);
    }

    @RequestMapping("/fetchAllUsers")
    public List<Map<String, Object>> fetchAll1(@RequestParam("u_id") String uId) {
        return userAndGroupService.fetchAll(uId);
    }

    @RequestMapping("/fetchMyFriends")
    public List<UserInfo> fecthMyFriends(@RequestParam("u_id") Integer uId) {
        return userAndGroupService.fetchMyFriends(uId);
    }

    @RequestMapping("/fetchMyGroups")
    public List<Group> fetchMyGroups(@RequestParam("u_id") Integer uId) {
        return userAndGroupService.fetchMyGroups(uId);
    }

    @RequestMapping("/userInfo")
    public UserInfo demo(@RequestParam("u_id") Integer uId) {
        return chatService.getUserInfo(uId);
    }
}
