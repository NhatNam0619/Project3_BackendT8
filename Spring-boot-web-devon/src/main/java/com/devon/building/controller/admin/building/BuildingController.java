package com.devon.building.controller.admin.building;


import com.devon.building.converter.BuildingDTOsConverter;
import com.devon.building.entity.Building;
import com.devon.building.enums.District;
import com.devon.building.enums.TypeCode;
import com.devon.building.model.dto.BuildingDTO;
import com.devon.building.model.dto.BuildingResponseDTO;
import com.devon.building.model.request.BuildingSearchRequest;
import com.devon.building.pagination.PaginationResult;
import com.devon.building.service.BuildingService;
import com.devon.building.service.UserService;
import com.devon.building.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller(value="buildingControllerOfAdmin")
@RequestMapping("/admin/buildings")
@RequiredArgsConstructor
public class BuildingController {

    private final UserService userService;
    private final BuildingService buildingService;
    private final BuildingDTOsConverter buildingDTOsConverter;

    @GetMapping("/list")
    public ModelAndView getBuilding(@ModelAttribute("params") BuildingSearchRequest params, @RequestParam(value = "page", defaultValue = "1") String pageStr){
        if (!SecurityUtil.hasRole("MANAGER")) {
            params.setStaffid(userService.getUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getId());}
        ModelAndView mav = new ModelAndView("admin/buildings/buildingList");
        params.setTableId("building");
        int page = 1;
        try {
            page = Integer.parseInt(pageStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        final int MAX_RESULT = params.getMaxPageItems();
        final int MAX_NAVIGATION_PAGE = 2;
        PaginationResult<BuildingResponseDTO> buildings = buildingService.listBuildingsInfo(params, page, MAX_RESULT, MAX_NAVIGATION_PAGE);
        mav.addObject("districtid", District.getDistrict());
        mav.addObject("staffs",userService.getAllRoleStaff());
        mav.addObject("types", TypeCode.getType());
        mav.addObject("buildingResponseDTOs",buildings.getList());
        mav.addObject("model", buildings);
        return mav;
    }


    @GetMapping("/edit")
    public ModelAndView createBuilding(){
        ModelAndView mav = new ModelAndView("admin/buildings/buildingEdit");
        BuildingDTO building = new BuildingDTO();
        mav.addObject("building",building);
        mav.addObject("districtid", District.getDistrict());
        mav.addObject("types", TypeCode.getType());
        return mav;
    }

    @PreAuthorize("hasRole('MANAGER') or @buildingSecurity.isOwner(#buildingId)")
    @GetMapping("/edit/{buildingId}")
    public ModelAndView editBuilding(@PathVariable Long buildingId){
        ModelAndView mav = new ModelAndView("admin/buildings/buildingEdit");
        Building building = buildingService.getBuiding(buildingId);
        //if(SecurityUtil.hasRole("MANAGER") || building.getUsers().contains(userService.getUsername(SecurityContextHolder.getContext().getAuthentication().getName()))){
        BuildingDTO buildingDTO = buildingDTOsConverter.toBuildingsDTO(building);
        mav.addObject("building",buildingDTO);
        mav.addObject("districtid", District.getDistrict());
        mav.addObject("types", TypeCode.getType());
        return mav;}
        //else return new ModelAndView("403");
    }

