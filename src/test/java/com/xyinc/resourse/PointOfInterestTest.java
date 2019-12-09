package com.xyinc.resourse;

import com.xyinc.model.PointOfInterest;
import com.xyinc.repository.PointOfInterestRepository;
import com.xyinc.service.PointOfInterestService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PointOfInterestTest {
    @Autowired
    private PointOfInterestService pointOfInterestService;

    @Autowired
    private PointOfInterestRepository pointOfInterestRepository;

    @Before
    public void setUp() {

    }

    @Test
    public void retrieveAllPointOfInterest() {
        Optional<List<PointOfInterest>> list = pointOfInterestService.retrieveAllPointOfInterest();

        Assert.assertNotNull("Not null ok", Optional.ofNullable(list));
        Assert.assertEquals("Expected size ok", 13, (Object) list.map(List::size).orElse(0));
    }

    @Test
    public void registerPointOfInterestTest() {

        PointOfInterest pointOfInterest = new PointOfInterest();
        pointOfInterest.setName("Beach");
        pointOfInterest.setCoordinateX((double) 31);
        pointOfInterest.setCoordinateY((double) 16);

        PointOfInterest point = pointOfInterestService.registerPointOfInterest(pointOfInterest);

        Assert.assertNotNull("Not null ok", point);
        Assert.assertNotNull("Id not null ok",point.getId());
        Assert.assertEquals("Name attribute match", "Beach", point.getName());

        pointOfInterestRepository.deleteById(point.getId());
    }

    @Test
    public void findPointsByCoordinatesTest(){
        List<PointOfInterest> list = pointOfInterestService.findByProximity((double)20, (double)10, (double)10);

        Assert.assertNotNull("Not null ok", list);
        Assert.assertEquals("Expected size ok", 4, list.size());
    }

}
