package com.arisusantolie.springwebsocketrealtimechatapp.service;

import com.arisusantolie.springwebsocketrealtimechatapp.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ChatService {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    JdbcTemplate jdbcTemplate;

    // 获取用户信息
    public UserInfo getUserInfo(Integer uId) {
        return jdbcTemplate.queryForObject
                ("select * from users where u_id = ?",
                        new BeanPropertyRowMapper<UserInfo>(UserInfo.class),
                        uId);
    }

    public void sendMessage(Message message) {
        System.out.println("ChatService" + message.toString());
        Date time = new Date(System.currentTimeMillis());
        jdbcTemplate.update("insert into messages (`from`, `to`, `content`, `time`) " +
                "values (?,?,?,?)",message.getFrom(),message.getTo(),message.getContent(),time);
        message.setTime(time);
        simpMessagingTemplate.convertAndSend("/topic/messages/" + message.getTo(), message);
    }
    public void sendMessageGroup(GroupMessage message) {
        Date time = new Date(System.currentTimeMillis());
        jdbcTemplate.update("INSERT INTO `group_messages`(`g_id`, `u_id`, `content`, `time`) " +
                "VALUES (?,?,?,? )",message.getG_id(), message.getU_id(), message.getContent(), time);
        simpMessagingTemplate.convertAndSend("/topic/messages/group/" + message.getG_id(), message);

    }

    // 发送标准化数据
    public void sendGoodMessage(Message message) {
        Date time = new Date(System.currentTimeMillis());
        jdbcTemplate.update("insert into messages (from, to, content, time) " +
                "values (?,?,?,?)",message.getFrom(),message.getTo(),message.getContent(),time);
        UserInfo sender = getUserInfo(message.getFrom());
        System.out.println("sender" + sender);
        UserInfo receiver = getUserInfo(message.getTo());
        System.out.println("receiver" + receiver);
        GoodMessage goodMessage = new GoodMessage(
                sender,
                receiver,
                message.getContent(),
                time
        );
        simpMessagingTemplate.convertAndSend("/topic/messages/" + message.getTo(), message);
    }

    public List<Message> getHistoryMessage(Integer from, Integer to){
        return jdbcTemplate.query("select * from messages where (`from` = ? and `to` = ?) " +
                "or (`to` = ? and `from` = ?) order by time asc",new BeanPropertyRowMapper<Message>(Message.class),from,to,from,to);
    }

    public List<GroupMessage> getHistoryGroupMessage(Integer gId) {
        return jdbcTemplate.query("select * from group_messages where g_id = ?", new BeanPropertyRowMapper<GroupMessage>(GroupMessage.class), gId);
    }
}
