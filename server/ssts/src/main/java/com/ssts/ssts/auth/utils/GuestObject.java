package com.ssts.ssts.auth.utils;

import lombok.Getter;

@Getter
public class GuestObject {
    private String email;

    public GuestObject(String email) {
        this.email = email;
    }
}
