package com.tech.titan.satisfactory.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "buildings", uniqueConstraints = {
        @UniqueConstraint(columnNames = "name"),
})
public class Building extends RepresentationModel<Building> implements Serializable {

    private Integer buildingId;
    private String name;
    private BuildingType buildingType;

    @JsonIgnore
    private List<Recipe> recipes;

    public Building() {
    }

    public Building(String name, BuildingType buildingType) {
        this.name = name;
        this.buildingType = buildingType;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "building_id", unique = true, nullable = false)
    public Integer getBuildingId(){
        return buildingId;
    }

    public void setBuildingId(Integer buildingId) {
        this.buildingId = buildingId;
    }

    @Column(name = "name", unique = true, nullable = false, length = 20)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Enumerated(EnumType.ORDINAL)
    @JoinColumn(name = "building_type_id", referencedColumnName = "building_type_id")
    public BuildingType getBuildingType() {
        return buildingType;
    }

    public void setBuildingType(BuildingType buildingType) {
        this.buildingType = buildingType;
    }

    @OneToMany(mappedBy = "building")
    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    @Override
    public String toString() {
        return "Building{" +
                "buildingId=" + buildingId +
                ", name='" + name + '\'' +
                ", buildingType=" + buildingType +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Building building = (Building) o;
        return Objects.equals(buildingId, building.buildingId) && Objects.equals(name, building.name) && buildingType == building.buildingType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(buildingId, name, buildingType);
    }
}
