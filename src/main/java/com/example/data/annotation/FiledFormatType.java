package com.example.data.annotation;

public enum FiledFormatType {

    DATE("DATE", "日期"),
    DECIMAL("DECIMAL", "小数"),
    STRING("STRING", "字符串"),
    DICT("DICT", "数据字典"),
    ;
    String type;
    String name;

    FiledFormatType(String type, String name) {
        this.type = type;
        this.name = name;
    }


    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

}
