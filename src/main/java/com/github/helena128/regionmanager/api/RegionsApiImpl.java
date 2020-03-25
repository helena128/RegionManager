package com.github.helena128.regionmanager.api;

import com.github.helena128.regionmanager.service.RegionsService;
import io.swagger.api.RegionsApi;
import io.swagger.model.Region;
import io.swagger.model.RegionInput;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RegionsApiImpl implements RegionsApi {

    RegionsService regionsService;

    @Override
    public ResponseEntity<Region> addRegion(@RequestBody RegionInput regionInput) {
        return new ResponseEntity<>(regionsService.addRegion(regionInput), HttpStatus.CREATED);
    }
}
