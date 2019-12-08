package com.xyinc.service;

import com.xyinc.model.PointOfInterest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface  PointOfInterestService {

    void registerPointOfInterest(PointOfInterest point);

    Optional<List<PointOfInterest>> retrieveAllPointOfInterest();

    Optional<PointOfInterest> findPointOfInterestById(Long id);

    void deletePointOfInterest(PointOfInterest pointOfInterest);

    List<PointOfInterest> findByProximity(Double coordinateX, Double coordinateY, Double dMax);
}
