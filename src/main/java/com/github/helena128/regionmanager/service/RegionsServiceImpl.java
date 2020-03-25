package com.github.helena128.regionmanager.service;

import com.github.helena128.regionmanager.repository.RegionsMybatisMapper;
import com.github.helena128.regionmanager.service.mapper.RegionsMapstructMapper;
import com.github.helena128.regionmanager.service.validator.RegionsValidator;
import io.swagger.model.Region;
import io.swagger.model.RegionInput;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RegionsServiceImpl implements RegionsService {

    RegionsValidator regionsValidator;
    RegionsMapstructMapper mapstructMapper;
    RegionsMybatisMapper mybatisMapper;

    @Override
    public Region addRegion(RegionInput regionInput) {
        //log.info("Called method to add region: " + regionInput.toString());
        regionsValidator.validateRegionCreation(regionInput);
        val addedRegionEntityId = mybatisMapper.addRegionEntity(mapstructMapper.mapRegionInputToRegionEntity(regionInput));
        //log.info("Added region, id: " + addedRegionEntity.getId());
        return mapstructMapper.mapRegionionEntityToRegion(mybatisMapper.findRegionEntityById(addedRegionEntityId));
    }

    @Override
    public Region findRegion(Integer id) {
        regionsValidator.validateRegionExistence(id);
        return mapstructMapper.mapRegionionEntityToRegion(mybatisMapper.findRegionEntityById(id.longValue()));
    }

    @Override
    public List<Region> findRegions() {
        return null;
    }

    @Override
    public void removeRegion(String id) {

    }

    @Override
    public Region updateRegion(String id, RegionInput regionInput) {
        return null;
    }
}
