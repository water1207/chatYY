package edu.hdu.chat.dto;

import org.springframework.data.relational.core.sql.In;

import java.sql.Date;

public class GroupMessage {
    private Integer id;
    private Integer g_id;
    private Integer u_id;
    private String content;

    private Date time;

    public GroupMessage(Integer id, Integer g_id, Integer u_id, String content, Date time) {
        this.id = id;
        this.g_id = g_id;
        this.u_id = u_id;
        this.content = content;
        this.time = time;
    }

    public GroupMessage() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getG_id() {
        return g_id;
    }

    public void setG_id(Integer g_id) {
        this.g_id = g_id;
    }

    public Integer getU_id() {
        return u_id;
    }

    public void setU_id(Integer u_id) {
        this.u_id = u_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
