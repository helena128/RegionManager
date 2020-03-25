package com.github.helena128.regionmanager.util;


import io.swagger.model.RegionInput;

public class RegionTestUtil {

    public static RegionInput buildRegionInput(final String name, final String shortName) {
        return new RegionInput().name(name).shortName(shortName);
    }
}
