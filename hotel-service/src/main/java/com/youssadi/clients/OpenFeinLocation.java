package com.youssadi.clients;

import com.youssadi.dto.responses.LocationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(path = "/api/locations", value="location")
public interface OpenFeinLocation {
    @GetMapping("/{id}")
    LocationResponse getLocationById(@PathVariable("id") long id);
}


