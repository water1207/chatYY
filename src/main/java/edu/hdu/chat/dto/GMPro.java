package edu.hdu.chat.dto;

public class GMPro extends GroupMessage{
    private String name;

    private String head_img;

    public GMPro(GroupMessage gm) {
        super(gm.getId(), gm.getG_id(), gm.getU_id(), gm.getContent(), gm.getTime());
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
        return "GMPro{" +
                "name='" + name + '\'' +
                ", head_img='" + head_img + '\'' +
                '}';
    }
}
