package com.yemmu.demomember.entity;

public class MemberPostDto {

    private String name;
    private String email;
    private String phone;

    public MemberPostDto() {}
    public MemberPostDto(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Member toEntity(){
        return new Member(name, email, phone);
    }
}
