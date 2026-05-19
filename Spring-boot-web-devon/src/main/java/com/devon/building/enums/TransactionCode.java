package com.devon.building.enums;

import lombok.Getter;

import java.util.LinkedHashMap;
import java.util.Map;

@Getter
public enum TransactionCode {
    CSKH("Chăm Sóc Khách Hàng"),
    DDX("Dẫn Đi Xem")
    ;
    private final String name;
    TransactionCode(String name) {
        this.name = name;
    }

    public static Map<String, String> getStatus(){
        Map<String,String> types = new LinkedHashMap<>();
        for(TransactionCode t : TransactionCode.values() )
        {
            types.put(t.toString(),t.name);
        }
        return types;
    }

    public static String getName(String code) {
        if (code == null) return null;
        for (TransactionCode s : TransactionCode.values()) {
            if (s.name().equals(code)) {
                return s.getName();
            }
        }
        return null;
    }
}
