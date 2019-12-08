package com.xyinc.resource.dto;

import javax.validation.constraints.PositiveOrZero;

public class PointOfInterestRequest {
    @PositiveOrZero
    Double dMax;
    @PositiveOrZero
    private Double coordinateX;
    @PositiveOrZero
    private Double coordinateY;

}
