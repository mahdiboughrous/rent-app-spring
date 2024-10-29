package com.boughrous.service;

import com.boughrous.domain.Hotel;
import com.boughrous.dto.requests.HotelRequest;
import com.boughrous.dto.responses.HotelResponse;

public interface HotelService {
    public long createHotel(HotelRequest hotelRequest);
    public HotelResponse getHotel(long hotelId);

}
