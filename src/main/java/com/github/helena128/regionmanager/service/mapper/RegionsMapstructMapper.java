package com.github.helena128.regionmanager.service.mapper;

import com.github.helena128.regionmanager.repository.model.RegionEntity;
import io.swagger.model.Region;
import io.swagger.model.RegionInput;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class RegionsMapstructMapper {

    public abstract RegionEntity mapRegionInputToRegionEntity(RegionInput regionInput);
    public abstract Region mapRegionionEntityToRegion(RegionEntity regionEntity);
}
