package com.example.demo.domain;

import lombok.Data;

@Data
public class Member {

    private int id;
    private String name;

    public Member() {
    }

    public Member(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
