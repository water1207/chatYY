package com.arisusantolie.springwebsocketrealtimechatapp.dto;

import org.springframework.data.relational.core.sql.In;

import java.sql.Date;

public class Message {
    private Integer id;
    private Integer from;
    private Integer to;
    private String content;
    private Date time;

    public Message(Integer id, Integer from, Integer to, String content, Date time) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.content = content;
        this.time = time;
    }

    public Message() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFrom() {
        return from;
    }

    public void setFrom(Integer from) {
        this.from = from;
    }

    public Integer getTo() {
        return to;
    }

    public void setTo(Integer to) {
        this.to = to;
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

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", from=" + from +
                ", to=" + to +
                ", content='" + content + '\'' +
                ", time=" + time +
                '}';
    }
}
