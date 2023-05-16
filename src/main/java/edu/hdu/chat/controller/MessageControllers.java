package edu.hdu.chat.controller;

import edu.hdu.chat.dto.Group;
import edu.hdu.chat.dto.GroupMessage;
import edu.hdu.chat.dto.Message;
import edu.hdu.chat.dto.UserInfo;
import edu.hdu.chat.service.ChatService;
import edu.hdu.chat.service.UserAndGroupService;
import org.springframework.beans.factory.annotation.Autowired;
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
    UserAndGroupService userAndGroupService;

    // 发送频道消息
    @MessageMapping("/chat/group")
    public void sendMessageToGroup(GroupMessage message) {
        System.out.println(message.toString());
        chatService.sendMessageGroup(message);
    }
    // 发送群聊消息
    @MessageMapping("/chat/personal")
    public void sendMessagePersonal(Message message) {
        System.out.println(message.toString());
        chatService.sendMessage(message);
    }
    // 根据好友与自己Id 查询聊天记录
    @RequestMapping("/getHistoryMessage")
    public List<Message> getHistoryMessage(@RequestParam("from") Integer from, @RequestParam("to") Integer to){
        return chatService.getHistoryMessage(from, to);
    }
    // 根据频道Id 查询频道聊天记录
    @RequestMapping("getHistoryGroupMessage2")
    public List<Map<String, Object>> getHistoryGroupMessage2(@RequestParam("g_id") Integer gId) {
        return chatService.getHistoryGroupMessage2(gId);
    }
    /*@RequestMapping("getHistoryGroupMessage")
    public List<GroupMessage> getHistoryGroupMessage(@RequestParam("g_id") Integer gId) {
        return chatService.getHistoryGroupMessage(gId);
    }*/

    // 获取所有用户
    @RequestMapping("/fetchAllUsers")
    public List<Map<String, Object>> fetchAll1(@RequestParam("u_id") String uId) {
        return userAndGroupService.fetchAll(uId);
    }

    // 获取我的好友
    @RequestMapping("/fetchMyFriends")
    public List<UserInfo> fecthMyFriends(@RequestParam("u_id") Integer uId) {
        return userAndGroupService.fetchMyFriends(uId);
    }

    // 获取我加入的群组
    @RequestMapping("/fetchMyGroups")
    public List<Group> fetchMyGroups(@RequestParam("u_id") Integer uId) {
        return userAndGroupService.fetchMyGroups(uId);
    }

    // 获取个人信息
    @RequestMapping("/userInfo")
    public UserInfo demo(@RequestParam("u_id") Integer uId) {
        return chatService.getUserInfo(uId);
    }
}
