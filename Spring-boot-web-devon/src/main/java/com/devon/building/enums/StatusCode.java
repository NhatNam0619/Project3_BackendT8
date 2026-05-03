package com.devon.building.enums;

import lombok.Getter;

import java.util.LinkedHashMap;
import java.util.Map;

@Getter
public enum StatusCode {
    CHUA_XU_LY("Chưa xử lý"),
    DANG_XU_LY("Đang xử lý"),
    DA_XU_LY("Đã xử lý")
    ;
    private final String name;
    StatusCode(String name) {
        this.name = name;
    }
    public static Map<String, String> getStatus(){
        Map<String,String> types = new LinkedHashMap<>();
        for(StatusCode t : StatusCode.values() )
        {
            types.put(t.toString(),t.name);
        }
        return types;
    }

    public static String getName(String code) {
        if (code == null) return null;
        for (StatusCode s : StatusCode.values()) {
            if (s.name().equals(code)) {
                return s.getName();
            }
        }
        return null;
    }
}
