package com.youssadi.service;

import com.youssadi.clients.OpenFeinLocation;
import com.youssadi.dao.HotelRepository;
import com.youssadi.domain.Hotel;
import com.youssadi.dto.requests.HotelRequest;
import com.youssadi.dto.responses.HotelResponse;
import com.youssadi.dto.responses.LocationResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service

public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private OpenFeinLocation openFeinLocation;



    @Override
    public long createHotel(HotelRequest hotelRequest) {
        Hotel hotel = new Hotel();
        hotel.setName(hotelRequest.getName());
        hotel.setNumberOfStars(hotelRequest.getNumberOfStars());
        hotel.setNumberOfRooms(hotelRequest.getNumberOfRooms());
        hotel.setLocationId(hotelRequest.getLocationId());
        hotelRepository.save(hotel);
        return hotel.getHotelId();
    }

    @Override
    public HotelResponse getHotel(long hotelId) {
        HotelResponse hotelResponse = new HotelResponse();
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(
                ()-> new RuntimeException("Hotel with id " + hotelId + " not found"));
        hotelResponse.setHotelId(hotel.getHotelId());
        hotelResponse.setName(hotel.getName());
        hotelResponse.setNumberOfStars(hotel.getNumberOfStars());
        hotelResponse.setNumberOfRooms(hotel.getNumberOfRooms());
        //hotelResponse.setLocationId(hotel.getLocationId());
        hotelResponse.setLocationResponse(getLocationById(hotel.getLocationId()));

        return hotelResponse;


    }
    public LocationResponse getLocationById(long locationId){
        /*RestTemplate restTemplate = new RestTemplate();
        String uri = "http://localhost:8081/api/locations/{id}";
        LocationResponse locationResponse = restTemplate.getForObject(uri, LocationResponse.class, locationId);*/

        //LocationResponse locationResponse = locationFeignClient.getLocationById(locationId);
        return openFeinLocation.getLocationById(locationId);
    }


}
