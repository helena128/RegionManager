package com.github.helena128.regionmanager.service;

import com.github.helena128.regionmanager.repository.RegionsMybatisMapper;
import com.github.helena128.regionmanager.repository.model.RegionEntity;
import com.github.helena128.regionmanager.service.mapper.RegionsMapstructMapper;
import com.github.helena128.regionmanager.service.validator.RegionsValidator;
import io.swagger.model.OperationResultWithRegionList;
import io.swagger.model.Region;
import io.swagger.model.RegionInput;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.github.helena128.regionmanager.config.CacheConfig.REGIONS_CACHE_NAME;

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
        return this.findRegion(addedRegionEntityId.intValue());
    }

    @Override
    @CachePut(value = REGIONS_CACHE_NAME, key = "#id")
    public Region findRegion(Integer id) {
        regionsValidator.validateRegionExistence(id);
        return mapstructMapper.mapRegionionEntityToRegion(mybatisMapper.findRegionEntityById(id.longValue()));
    }

    @Override
    public OperationResultWithRegionList findRegions() {
        return this.findAllRegions()
                .stream()
                .map(mapstructMapper::mapRegionionEntityToRegion)
                .collect(Collectors.toCollection(OperationResultWithRegionList::new));
    }

    @Override
    @CacheEvict(value = REGIONS_CACHE_NAME, key = "#id")
    public void removeRegion(Integer id) {
        regionsValidator.validateRegionExistence(id);
        mybatisMapper.removeById(id.longValue());
    }

    @Override
    @CachePut(value = REGIONS_CACHE_NAME, key = "#id")
    public Region updateRegion(Integer id, RegionInput regionInput) {
        mybatisMapper.updateRegionById(id.longValue(), regionInput); // update
        return mapstructMapper.mapRegionionEntityToRegion(mybatisMapper.findRegionEntityById(id.longValue()));
    }

    @Cacheable(value = REGIONS_CACHE_NAME)
    public List<RegionEntity> findAllRegions() {
        return mybatisMapper.findAllRegions();
    }
}
