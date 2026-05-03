package com.devon.building.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

@Entity
@Table(name = "assignmentbuilding")
public class AssignmentBuilding extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "buildingid")
    Building building;

    @ManyToOne
    @JoinColumn(name = "staffid",nullable = false)
    User user;
}
