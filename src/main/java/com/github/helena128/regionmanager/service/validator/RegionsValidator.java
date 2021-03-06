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

    /**
     * Validates that region exists
     * @param id - ID of the region
     */
    void validateRegionExistence(Integer id);

    /**
     * Validates update of the region
     *
     * @param id - identificator of the region
     * @param regionInput - update values
     */
    void validateRegionUpdate(Integer id, RegionInput regionInput);
}
