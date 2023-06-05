package edu.hdu.chat.service;

import edu.hdu.chat.dto.Group;
import edu.hdu.chat.dto.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {
    @Autowired
    JdbcTemplate jdbcTemplate;

    // 模糊搜索群组
    public List<Group> serchGroup(String groupName) {
        String sql="select * from groups where name like '%"+groupName+"%'";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Group.class));
    }

    // 加入群组，并且如已经加入则跳过
    public void addGroup(Integer gId, Integer uId) {
        String sql = "insert ignore into group_members value(?,?)";
        int affected = jdbcTemplate.update(sql, gId, uId);
        System.out.println(uId+"加入群组"+gId+" res: " + affected);
    }

    public void exitGroup(Integer gId, Integer uId) {
        String sql = "delete FROM group_members where g_id="+gId+" and u_id="+uId;
        int update = jdbcTemplate.update(sql);
    }
}
