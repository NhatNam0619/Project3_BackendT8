package com.devon.building.entity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

@Entity
@Table(name = "rentarea")
public class RentArea extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "buildingid") // ,insertable = false, updatable = false)
    Building building;

    @Column(name = "value", nullable = false)
    Long value;

}
