package com.express.domain.model.user;

public enum UserGrade {
    USER(101,"일반 유저"),
    ADMIN(102,"관리자 유저"),
    MASTER(103, "마스터 유저");
    private  int code;
    private String gradeName;

    UserGrade(int code, String gradeName) {
        this.code = code;
        this.gradeName = gradeName;
    }

    public int getCode() {
        return code;
    }

    public String getGradeName() {
        return gradeName;
    }
}
