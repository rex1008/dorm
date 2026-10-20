package com.dorm.enums;

public enum SexEnum {

    M("男"),
    F("女"),

    ;

    private final String desc;

    SexEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public static String getDescByName(String name) {
        for (SexEnum e : values()) {
            if (e.name().equals(name)) {
                return e.getDesc();
            }
        }
        return null;
    }
}
