package com.example.giao.Bean;

public class Information {
    private String username;
    private Integer integral;
    private String phone;
    private Boolean is_active;
    private String head_image;
    private Boolean is_member;

    public String getHead_image() {
        return head_image;
    }

    public void setHead_image(String head_image) {
        this.head_image = head_image;
    }

    public Boolean getIs_active() {
        return is_active;
    }

    public void setIs_active(Boolean is_active) {
        this.is_active = is_active;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public Boolean getIs_member() {
        return is_member;
    }

    public void setIs_member(Boolean is_member) {
        this.is_member = is_member;
    }
}
