package com.devon.building.model.request;

import com.devon.building.model.dto.AbstractDTO;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BuildingSearchRequest extends AbstractDTO {
    private String name;
    private Long floorarea;
    private String district;
    private String ward;
    private String street;
    private Long numberofbasement;
    private String direction;
    private Long level;
    private Long arearentmin;
    private Long arearentmax;
    private Long rentpricemin;
    private Long rentpricemax;
    private String managername;
    private String managerphone;
    private Long staffid;
    private List<String> type;
}
