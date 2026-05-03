package com.devon.building.enums;


import lombok.Getter;

import java.util.LinkedHashMap;
import java.util.Map;

@Getter
public enum District {

    QUAN_1("Quận 1"),
    QUAN_2("Quận 2"),
    QUAN_3("Quận 3"),
    QUAN_4("Quận 4"),
    QUAN_5("Quận 5"),
    QUAN_6("Quận 6"),
    QUAN_7("Quận 7"),
    QUAN_8("Quận 8"),
    QUAN_10("Quận 10"),
    QUAN_TB("Quận Tân Bình"),
    QUAN_BT("Quận Bình Thạnh"),
    QUAN_GV("Quận Gò Vấp"),
    QUAN_TD("Quận Thủ Đức"),
    QUAN_PN("Quận Phú Nhuận"),
    ;

    private final String name;

    District(String name) {
        this.name = name;
    }

    public static Map<String, String> getDistrict()
    {
        Map<String,String> district = new LinkedHashMap<>();
        for(District d : District.values() )
        {
            district.put(d.toString(),d.name);
        }
        return district;
    }

    public String getName()
    {
        return name;
    }
}
