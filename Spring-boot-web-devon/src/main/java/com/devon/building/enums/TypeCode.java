package com.devon.building.enums;

import lombok.Getter;

import java.util.LinkedHashMap;
import java.util.Map;

@Getter
public enum TypeCode {
    TANG_TRET("Tầng Trệt"),
    NGUYEN_CAN("Nguyên Căn"),
    NOI_THAT("Nội Thất")
    ;
    private final String name;
    TypeCode(String name) {
        this.name = name;
    }
    public static Map<String, String> getType(){
            Map<String,String> types = new LinkedHashMap<>();
            for(TypeCode t : TypeCode.values() )
            {
                types.put(t.toString(),t.name);
            }
            return types;
    }
}
