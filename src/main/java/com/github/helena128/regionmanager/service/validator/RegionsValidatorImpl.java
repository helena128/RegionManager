package com.github.helena128.regionmanager.service.validator;

import com.github.helena128.regionmanager.repository.RegionsMybatisMapper;
import io.swagger.model.RegionInput;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RegionsValidatorImpl implements RegionsValidator {

    RegionsMybatisMapper mybatisMapper;

    @Override
    public void validateRegionCreation(RegionInput regionInput) {
        // Here can happen also another validation - e.g. for only letters in string
        checkRegionNameUnique(regionInput.getName());
        checkRegionShortNameValid(regionInput.getShortName());
    }

    @Override
    public void validateRegionExistence(String id) {
        // TODO
    }

    private void checkRegionShortNameValid(final String shortName) {
        if (mybatisMapper.checkRegionWithShortNameExists(shortName)) {
            throw new RuntimeException(); // TODO: change type of exception
        }
    }

    private void checkRegionNameUnique(final String name) {
        if (mybatisMapper.checkRegionWithNameExists(name)) {
            throw new RuntimeException(); // TODO: change type of exception
        }
    }
}
