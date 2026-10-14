package com.dorm.context;

import com.dorm.bean.dto.TokenDTO;

public class UserContext {

    private static ThreadLocal<TokenDTO> USER_INFO = new ThreadLocal<>();

    public static void set(TokenDTO tokenDTO) {
        USER_INFO.set(tokenDTO);
    }

    public static TokenDTO get() {
        return USER_INFO.get();
    }

    public static void remove() {
        USER_INFO.remove();
    }
}
