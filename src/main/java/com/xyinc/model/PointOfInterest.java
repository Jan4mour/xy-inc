package com.xyinc.model;

import com.xyinc.resource.dto.PointOfInterestDto;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class PointOfInterest {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    private Double coordinateX;

    @NotNull
    private Double coordinateY;

    public PointOfInterest() {
    }

    public PointOfInterest(PointOfInterestDto point) {
        this();
        this.name = point.getName();
        this.coordinateX = point.getCoordinateX();
        this.coordinateY = point.getCoordinateY();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCoordinateX() {
        return coordinateX;
    }

    public void setCoordinateX(Double coordinateX) {
        this.coordinateX = coordinateX;
    }

    public Double getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateY(Double coordinateY) {
        this.coordinateY = coordinateY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PointOfInterest)) return false;
        PointOfInterest that = (PointOfInterest) o;
        return getId().equals(that.getId()) &&
                getName().equals(that.getName()) &&
                getCoordinateX().equals(that.getCoordinateX()) &&
                getCoordinateY().equals(that.getCoordinateY());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getCoordinateX(), getCoordinateY());
    }
}
