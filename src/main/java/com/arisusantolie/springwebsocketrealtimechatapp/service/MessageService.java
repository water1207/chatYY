package com.arisusantolie.springwebsocketrealtimechatapp.service;

import com.arisusantolie.springwebsocketrealtimechatapp.dto.GoodMessage;
import com.arisusantolie.springwebsocketrealtimechatapp.dto.MessageDTO;
import com.arisusantolie.springwebsocketrealtimechatapp.dto.MessageGroupDTO;
import com.arisusantolie.springwebsocketrealtimechatapp.dto.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.Date;
import java.util.List;
import java.util.Map;

@Service
public class MessageService {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void sendMessage(String to, MessageDTO message) {
        Date createdTime = new Date(System.currentTimeMillis());
        jdbcTemplate.update("insert into messages (message_text,message_from,message_to,created_datetime) " +
                "values (?,?,?,? )",message.getMessage(),message.getFromLogin(),to,createdTime);
        UserInfo sender = getUserInfo(message.getFromLogin());
        System.out.println("sender" + sender);
        UserInfo receiver = getUserInfo(Integer.parseInt(to));
        System.out.println("receiver" + receiver);
        GoodMessage goodMessage = new GoodMessage(
                sender,
                receiver,
                message.getMessage(),
                createdTime
        );
        simpMessagingTemplate.convertAndSend("/topic/messages/" + to, message);

    }

    public List<Map<String,Object>> getListMessage(@PathVariable("from") Integer from, @PathVariable("to") Integer to){
        return jdbcTemplate.queryForList("select * from messages where (message_from=? and message_to=?) " +
                "or (message_to=? and message_from=?) order by created_datetime asc",from,to,from,to);
    }


    public List<Map<String,Object>> getListMessageGroups(@PathVariable("groupid") Integer groupid){
        return jdbcTemplate.queryForList("select gm.*,us.name as name from group_messages gm " +
                "join users us on us.id=gm.user_id " +
                "where gm.group_id=? order by created_datetime asc",groupid);
    }


    public void sendMessageGroup(Integer to, MessageGroupDTO message) {

        jdbcTemplate.update("INSERT INTO `group_messages`(`group_id`, `user_id`, `messages`, `created_datetime`) " +
                "VALUES (?,?,?,current_timestamp )",to,message.getFromLogin(),message.getMessage());
        message.setGroupId(to);
        simpMessagingTemplate.convertAndSend("/topic/messages/group/" + to, message);

    }

    public UserInfo getUserInfo(Integer id) {
        return jdbcTemplate.queryForObject
                ("select * from users where id = ?",
                    new BeanPropertyRowMapper<UserInfo>(UserInfo.class),
                    id);
    }


}
