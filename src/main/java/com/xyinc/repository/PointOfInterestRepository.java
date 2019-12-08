package com.xyinc.repository;

import com.xyinc.model.PointOfInterest;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.NotBlank;

public interface PointOfInterestRepository extends JpaRepository<PointOfInterest, Long> {

}
