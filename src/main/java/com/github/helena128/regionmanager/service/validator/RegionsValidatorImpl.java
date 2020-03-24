package com.github.helena128.regionmanager.service.validator;

import io.swagger.model.RegionInput;
import org.springframework.stereotype.Service;

@Service
public class RegionsValidatorImpl implements RegionsValidator {

    @Override
    public void validateRegionCreation(RegionInput regionInput) {

    }

    private void isRegionShortNameValid() {

    }

    private void isRegionNameUnique() {

    }
}
