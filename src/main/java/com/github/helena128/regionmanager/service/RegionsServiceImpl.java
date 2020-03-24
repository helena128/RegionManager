package com.github.helena128.regionmanager.service;

import com.github.helena128.regionmanager.service.validator.RegionsValidator;
import io.swagger.model.Region;
import io.swagger.model.RegionInput;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RegionsServiceImpl implements RegionsService {

    RegionsValidator regionsValidator;

    @Override
    public Region addRegion(RegionInput regionInput) {

        regionsValidator.validateRegionCreation(regionInput);
        return null;
    }
}
