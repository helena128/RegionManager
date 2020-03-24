package com.github.helena128.regionmanager.api;

import io.swagger.api.RegionsApi;
import io.swagger.model.OperationResultWithRegionList;
import io.swagger.model.Region;
import io.swagger.model.RegionInput;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class RegionsApiImpl implements RegionsApi {

    @Override
    public ResponseEntity<Region> addRegion(RegionInput regionInput) {
        log.info("hi");
        return null;
    }

    @Override
    public ResponseEntity<Region> getRegionById(String id) {
        return null;
    }

    @Override
    public ResponseEntity<OperationResultWithRegionList> getRegions() {
        return null;
    }

    @Override
    public ResponseEntity<Void> removeRegion(String id) {
        return null;
    }

    @Override
    public ResponseEntity<Region> updateRegion(String id, RegionInput regionInput) {
        return null;
    }
}
