package com.github.helena128.regionmanager.util;


import com.github.helena128.regionmanager.repository.model.RegionEntity;
import io.swagger.model.RegionInput;
import lombok.val;

public class RegionTestUtil {

    public static RegionInput buildRegionInput(final String name, final String shortName) {
        return new RegionInput().name(name).shortName(shortName);
    }

    public static RegionEntity buildRegionEntity(final String name, final String shortName) {
        val regionEntity = new RegionEntity();
        regionEntity.setName(name);
        regionEntity.setShortName(shortName);
        return regionEntity;
    }
}
