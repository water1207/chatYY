package edu.hdu.chat.dto;

import java.sql.Date;

public class GoodMessage {
    UserInfo sender;
    UserInfo receiver;
    String content;
    Date createdTime;

    public GoodMessage(UserInfo sender, UserInfo receiver, String content, Date createdTime) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.createdTime = createdTime;
    }

    public GoodMessage() {}

    public UserInfo getSender() {
        return sender;
    }

    public void setSender(UserInfo sender) {
        this.sender = sender;
    }

    @Override
    public String toString() {
        return "GoodMessage{" +
                "sender=" + sender +
                ", receiver=" + receiver +
                ", content='" + content + '\'' +
                ", createdTime=" + createdTime +
                '}';
    }
}
