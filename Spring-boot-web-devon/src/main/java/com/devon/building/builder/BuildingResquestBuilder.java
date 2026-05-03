package com.devon.building.builder;
import java.util.List;

public class BuildingResquestBuilder {

    private String name;
    private List<String> type;
    private Long numberofbasement;
    private String ward;
    private String street;
    private String district;
    private String managername;
    private String managerphone;
    private Long floorarea;
    private Long arearentmin;
    private Long arearentmax;
    private Long rentpricemin;
    private Long rentpricemax;
    private Long staffid;
    //private Long level;

    private BuildingResquestBuilder(Builder builder) {
        this.name = builder.name;
        this.type = builder.type;
        this.numberofbasement = builder.numberofbasement;
        this.ward = builder.ward;
        this.street = builder.street;
        this.district = builder.district;
        this.managername = builder.managername;
        this.managerphone = builder.managerphone;
        this.floorarea = builder.floorarea;
        this.arearentmin = builder.arearentmin;
        this.arearentmax = builder.arearentmax;
        this.rentpricemin = builder.rentpricemin;
        this.rentpricemax = builder.rentpricemax;
        this.staffid = builder.staffid;
        //this.level = builder.level;
    }

    public String getName() {
        return name;
    }

    public List<String> getType() {
        return type;
    }

    public Long getNumberOfBasement() {
        return numberofbasement;
    }

    public String getWard() {
        return ward;
    }

    public String getStreet() {
        return street;
    }

    public String getDistrict() {
        return district;
    }

    public String getManagername() {
        return managername;
    }

    public String getManagerphone() {
        return managerphone;
    }

    public Long getFloorarea() {
        return floorarea;
    }

    public Long getArearentmin() {
        return arearentmin;
    }

    public Long getArearentmax() {
        return arearentmax;
    }

    public Long getRentpricemin() {
        return rentpricemin;
    }

    public Long getRentpricemax() {
        return rentpricemax;
    }

    public Long getStaffid() {
        return staffid;
    }

	/*public Long getLevel() {
		return level;
	}*/

    public static class Builder {
        private String name;
        private List<String> type;
        private Long numberofbasement;
        private String ward;
        private String street;
        private String district;
        private String managername;
        private String managerphone;
        private Long floorarea;
        private Long arearentmin;
        private Long arearentmax;
        private Long rentpricemin;
        private Long rentpricemax;
        private Long staffid;
        //private Long level;

		/*public Builder setLevel(Long level) {
			this.level = level;
			return this;
		}*/

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setType(List<String> type) {
            this.type = type;
            return this;
        }

        public Builder setNumberOfBasement(Long numberofbasement) {
            this.numberofbasement = numberofbasement;
            return this;
        }

        public Builder setWard(String ward) {
            this.ward = ward;
            return this;
        }

        public Builder setStreet(String street) {
            this.street = street;
            return this;
        }

        public Builder setDistrict(String district) {
            this.district = district;
            return this;
        }

        public Builder setManagername(String managername) {
            this.managername = managername;
            return this;
        }

        public Builder setManagerphone(String managerphone) {
            this.managerphone = managerphone;
            return this;
        }

        public Builder setFloorarea(Long floorarea) {
            this.floorarea = floorarea;
            return this;
        }

        public Builder setArearentmin(Long arearentmin) {
            this.arearentmin = arearentmin;
            return this;
        }

        public Builder setArearentmax(Long arearentmax) {
            this.arearentmax = arearentmax;
            return this;
        }

        public Builder setRentpricemin(Long rentpricemin) {
            this.rentpricemin = rentpricemin;
            return this;
        }

        public Builder setRentpricemax(Long rentpricemax) {
            this.rentpricemax = rentpricemax;
            return this;
        }

        public Builder setStaffid(Long staffid) {
            this.staffid = staffid;
            return this;
        }

        public BuildingResquestBuilder build() {
            return new BuildingResquestBuilder(this);
        }
    }
}
