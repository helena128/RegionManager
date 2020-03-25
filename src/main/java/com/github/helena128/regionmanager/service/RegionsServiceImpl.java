package com.github.helena128.regionmanager.service;

import com.github.helena128.regionmanager.repository.RegionsMybatisMapper;
import com.github.helena128.regionmanager.service.mapper.RegionsMapstructMapper;
import com.github.helena128.regionmanager.service.validator.RegionsValidator;
import io.swagger.model.OperationResultWithRegionList;
import io.swagger.model.Region;
import io.swagger.model.RegionInput;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public OperationResultWithRegionList findRegions() {
        return mybatisMapper.findAllRegions()
                .stream()
                .map(mapstructMapper::mapRegionionEntityToRegion)
                .collect(Collectors.toCollection(OperationResultWithRegionList::new));
    }

    @Override
    public void removeRegion(Integer id) {
        // TODO: validation
        mybatisMapper.removeById(id.longValue());
    }

    @Override
    public Region updateRegion(Integer id, RegionInput regionInput) {
        mybatisMapper.updateRegionById(id.longValue(), regionInput); // update
        return mapstructMapper.mapRegionionEntityToRegion(mybatisMapper.findRegionEntityById(id.longValue()));
    }
}
