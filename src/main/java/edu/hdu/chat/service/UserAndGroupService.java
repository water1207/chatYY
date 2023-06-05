package edu.hdu.chat.service;

import edu.hdu.chat.dto.Group;
import edu.hdu.chat.dto.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserAndGroupService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Map<String,Object>> fetchAll(String myId) {
        List<Map<String,Object>> getAllUser=jdbcTemplate.queryForList("select * from users where id!=?",myId);

        return getAllUser;
    }

    public List<UserInfo> fetchMyFriends(Integer uId) {
        return jdbcTemplate.query("SELECT * from users where u_id != ?", new BeanPropertyRowMapper<UserInfo>(UserInfo.class), uId);
    }

    public List<Group> fetchMyGroups(Integer uId) {
        List<Group> groupList=jdbcTemplate.query("SELECT gr.* FROM `groups` gr " +
                "join group_members gm on gm.g_id=gr.g_id and gm.u_id=?", new BeanPropertyRowMapper<Group>(Group.class), uId);

        return groupList;
    }
    public List<Group> fetchAllGroups() {
        List<Group> groupList=jdbcTemplate.query("SELECT * FROM `groups`"
                , new BeanPropertyRowMapper<Group>(Group.class));

        return groupList;
    }

    public List<Map<String, Object>> fetchMemberByGroupId(Integer gId){
        return
                jdbcTemplate.queryForList("select u.* from users u join group_members gm On u.u_id = gm.u_id where gm.g_id = ?"
                        , gId);
    }
 }
