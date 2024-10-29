package com.boughrous.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import com.boughrous.clients.OpenFeinLocation;
import com.boughrous.dto.responses.LocationResponse;
import org.springframework.stereotype.Service;



@Service
public class CircuitLocationService {

    @Autowired
    private OpenFeinLocation openFeinlocation;
    
    Logger logger = LoggerFactory.getLogger(CircuitLocationService.class);
    int count = 1;

    @CircuitBreaker(name = "locationService", fallbackMethod = "getDummyLocation")
    public LocationResponse getLocationById(long locationId) {
        logger.info("count of calls of this method: " + count);
        count++;
        LocationResponse locationResponse = openFeinlocation.getLocationById(locationId);
        return locationResponse;
    }

    public LocationResponse getDummyLocation(long locationId, Throwable throwable) {
        logger.info("Error: " + throwable.getMessage());
        return new LocationResponse();
    }
}
