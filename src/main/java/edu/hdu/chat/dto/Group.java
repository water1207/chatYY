package edu.hdu.chat.dto;

public class Group {
    private Integer g_id;
    private String name;
    private String head_img;

    public Group(Integer g_id, String name, String head_img) {
        this.g_id = g_id;
        this.name = name;
        this.head_img = head_img;
    }

    public Group() {}

    public Integer getG_id() {
        return g_id;
    }

    public void setG_id(Integer g_id) {
        this.g_id = g_id;
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
}
