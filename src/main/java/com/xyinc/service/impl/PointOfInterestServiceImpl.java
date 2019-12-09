package com.xyinc.service.impl;

import com.xyinc.model.PointOfInterest;
import com.xyinc.repository.PointOfInterestRepository;
import com.xyinc.resource.PointOfInterestResource;
import com.xyinc.service.PointOfInterestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PointOfInterestServiceImpl implements PointOfInterestService {



    @Autowired
    private PointOfInterestRepository pointOfInterestRepository;

    @Override
    public PointOfInterest registerPointOfInterest(PointOfInterest point) {
        return pointOfInterestRepository.save(point);
    }

    @Override
    public Optional<List<PointOfInterest>> retrieveAllPointOfInterest() {
        return Optional.of(pointOfInterestRepository.findAll());
    }

    @Override
    public Optional<PointOfInterest> findPointOfInterestById(Long id) {
        return pointOfInterestRepository.findById(id);
    }

    @Override
    public void deletePointOfInterest(PointOfInterest pointOfInterest) {
        pointOfInterestRepository.delete(pointOfInterest);
    }

        @Override
    public List<PointOfInterest> findByProximity(Double coordinateX, Double coordinateY, Double dMax) {
        Optional<List<PointOfInterest>> allPoints = this.retrieveAllPointOfInterest();
        List<PointOfInterest> collect = null;
        if (allPoints.isPresent()){
            Stream<PointOfInterest> filterDistance = allPoints.get().stream().filter(p -> getDistance(coordinateX, coordinateY, p) < dMax);
            collect = filterDistance.collect(Collectors.toList());
        }
        return collect;

    }

    private Double getDistance(Double coordinateX, Double coordinateY, PointOfInterest poi) {
        return Math.sqrt(Math.pow(poi.getCoordinateX() - coordinateX, 2) + Math.pow(poi.getCoordinateY() - coordinateY, 2));
    }
}
