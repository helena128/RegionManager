package com.github.helena128.regionmanager.service.validator;

import io.swagger.model.RegionInput;

/**
 * Validates business logic of the input
 */
public interface RegionsValidator {

    /**
     * Validates request to add region
     *
     * @param regionInput - request to add region
     */
    void validateRegionCreation(RegionInput regionInput);
}
