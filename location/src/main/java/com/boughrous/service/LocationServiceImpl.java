package com.boughrous.service;

import com.boughrous.exceptions.LocationNotFoundException;
import com.boughrous.dao.LocationRepository;
import com.boughrous.domain.Location;
import com.boughrous.dto.requests.LocationRequest;
import com.boughrous.dto.responses.LocationResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class LocationServiceImpl implements  LocationServive{

    @Autowired
    private LocationRepository locationRepository;


    @Override
    public long addLocation(LocationRequest location) {
        Location loc = new Location();
        loc.setLongitude(location.getLongitude());
        loc.setLatitude(location.getLatitude());
        locationRepository.save(loc);
        return loc.getId();
    }

    public LocationResponse getLocationById(long id) {
        Optional<Location> location = locationRepository.findById(id);
        LocationResponse locationResponse = new LocationResponse();
        if (location.isPresent()) {
            Location loc = location.get();
            locationResponse.setLocationId(loc.getId());
            locationResponse.setLatitude(loc.getLatitude());
            locationResponse.setLongitude(loc.getLongitude());
        }else {
            throw new LocationNotFoundException("Location with id = "+ id + " does not exist");
        }


        return locationResponse;
    }
    public List<LocationResponse> getAllLocations() {
        List<Location> locs = locationRepository.findAll();
        List<LocationResponse> locations = new ArrayList<LocationResponse>();
        for(Location loc : locs) {
            LocationResponse locationResponse = new LocationResponse();
            locationResponse.setLongitude(loc.getLongitude());
            locationResponse.setLatitude(loc.getLatitude());
            locationResponse.setLocationId(loc.getId());
            locations.add(locationResponse);
        }
        return locations;
    }

    @Override
    public void updateLocation(LocationRequest location, long locationId) {
        Optional<Location> location1 = locationRepository.findById(locationId);
        if (location1.isPresent()) {
            Location loc = location1.get();
            loc.setLongitude(location.getLongitude());
            loc.setLatitude(location.getLatitude());
            locationRepository.save(loc);
        }


    }

    @Override
    public void deleteLocation(long locationId) {
        Optional<Location> location1 = locationRepository.findById(locationId);
        if (location1.isPresent()) {
            locationRepository.delete(location1.get());
        }

    }
}
