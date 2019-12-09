package com.xyinc.resource.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

public class PointOfInterestRequest {
    @NotNull
    @PositiveOrZero
    private Double dMax;
    @PositiveOrZero
    private Double coordinateX;
    @PositiveOrZero
    private Double coordinateY;

    public PointOfInterestRequest() {

    }

    public Double getdMax() {
        return dMax;
    }

    public void setdMax(Double dMax) {
        this.dMax = dMax;
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
