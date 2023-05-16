package edu.hdu.chat.dto;

public class UserInfo {
    Integer u_id;
    String name;
    String head_img;

    public UserInfo(Integer u_id, String name, String head_img) {
        this.u_id = u_id;
        this.name = name;
        this.head_img = head_img;
    }

    public UserInfo() {}

    public Integer getU_id() {
        return u_id;
    }

    public void setU_id(Integer u_id) {
        this.u_id = u_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHead_img() {
        return head_img;
    }

    public void setHead_img(String head_img) {
        this.head_img = head_img;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "u_id=" + u_id +
                ", name='" + name + '\'' +
                ", head_img='" + head_img + '\'' +
                '}';
    }
}
