package com.github.helena128.regionmanager.service;

import io.swagger.model.OperationResultWithRegionList;
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

    /**
     * Retrieves region by id
     *
     * @param id -region identificator
     * @return region
     */
    Region findRegion(Integer id);

    /**
     * Retrieves all regions
     *
     * @return list of regions that were found.
     */
    OperationResultWithRegionList findRegions();

    /**
     * Removes region by id
     * @param id - identifier of the region that should be removed
     */
    void removeRegion(Integer id);

    /**
     * Updates region
     *
     * @param id - region identificator
     * @param regionInput - update values
     * @return updated region
     */
    Region updateRegion(Integer id, RegionInput regionInput);
}
