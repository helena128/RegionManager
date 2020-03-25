package com.github.helena128.regionmanager.service;

import com.github.helena128.regionmanager.repository.RegionsMybatisMapper;
import com.github.helena128.regionmanager.service.mapper.RegionsMapstructMapper;
import com.github.helena128.regionmanager.service.validator.RegionsValidator;
import io.swagger.model.Region;
import io.swagger.model.RegionInput;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@Log4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RegionsServiceImpl implements RegionsService {

    RegionsValidator regionsValidator;
    RegionsMapstructMapper mapstructMapper;
    RegionsMybatisMapper mybatisMapper;

    @Override
    public Region addRegion(RegionInput regionInput) {
        log.info("Called method to add region: " + regionInput.toString());
        regionsValidator.validateRegionCreation(regionInput);
        val addedRegionEntity = mybatisMapper.addRegionEntity(mapstructMapper.mapRegionInputToRegionEntity(regionInput));
        log.info("Added region, id: " + addedRegionEntity.getId());
        return mapstructMapper.mapRegionionEntityToRegion(addedRegionEntity);
    }
}
