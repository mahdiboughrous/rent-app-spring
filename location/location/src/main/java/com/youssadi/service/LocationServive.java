package com.youssadi.service;

import com.youssadi.domain.Location;
import com.youssadi.dto.requests.LocationRequest;
import com.youssadi.dto.responses.LocationResponse;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface LocationServive {
    public long addLocation(LocationRequest location);
    public LocationResponse getLocationById(long id);
    public List<LocationResponse> getAllLocations();
    public void updateLocation(LocationRequest location, long locationId);
    public void deleteLocation(long locationId);
}


