package com.xyinc.resource.dto;

import com.xyinc.handler.PointOfInterestExceptionHandler;
import com.xyinc.model.PointOfInterest;
import com.xyinc.service.PointOfInterestService;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

public class PointOfInterestDto {
    @NotNull
    private String name;
    @PositiveOrZero
    private Double coordinateX;
    @PositiveOrZero
    private Double coordinateY;

    public PointOfInterestDto() {
    }

    public PointOfInterestDto(PointOfInterest pointOfInterest) {
        this.name = pointOfInterest.getName();
        this.coordinateX = pointOfInterest.getCoordinateX();
        this.coordinateY = pointOfInterest.getCoordinateY();
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
}
