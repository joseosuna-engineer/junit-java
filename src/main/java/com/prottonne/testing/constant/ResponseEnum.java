package com.prottonne.testing.constant;

public enum ResponseEnum {
    CODE_A("A"),
    CODE_B("B"),
    CODE_C("C");

    private final String data;

    private ResponseEnum(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }
}
