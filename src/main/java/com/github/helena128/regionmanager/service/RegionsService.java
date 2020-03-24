package com.github.helena128.regionmanager.service;

import io.swagger.model.Region;
import io.swagger.model.RegionInput;

/**
 * Handles business logic for actions with regions.
 */
public interface RegionsService {

    /**
     * Adds region into th system
     *
     * @param regionInput - region info.
     * @return result of adding region into the system.
     */
    Region addRegion(RegionInput regionInput);
}
