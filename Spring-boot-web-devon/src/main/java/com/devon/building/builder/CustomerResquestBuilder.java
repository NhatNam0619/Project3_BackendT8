package com.devon.building.builder;

import com.devon.building.entity.User;

import java.util.List;

public class CustomerResquestBuilder {

    private String fullName;
    private Long phone;
    private String email;
    private Long staffid;
    private String status;


    private CustomerResquestBuilder(Builder builder) {
        this.fullName = builder.fullName;
        this.phone = builder.phone;
        this.email = builder.email;
        this.staffid = builder.staffid;
        this.status = builder.status;
    }

    public String getFullName() {
        return fullName;
    }

    public Long getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public Long getStaffid() {
        return staffid;
    }

    public String getStatus() {
        return status;
    }


    public static class Builder {
        private String fullName;
        private Long phone;
        private String email;
        private Long staffid;
        private String status;

        public Builder setFullName(String fullName) {
            this.fullName = fullName;return this;
        }

        public Builder setPhone(Long phone) {
            this.phone = phone;return this;
        }

        public Builder setEmail(String email) {
            this.email = email;return this;
        }

        public Builder setStaffid(Long staffid) {
            this.staffid = staffid;return this;
        }

        public Builder setStatus(String status) {
            this.status = status;return this;
        }
        public CustomerResquestBuilder build() {
            return new CustomerResquestBuilder(this);
        }
    }
}
