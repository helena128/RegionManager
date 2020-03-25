package com.github.helena128.regionmanager.service.validator;

import com.github.helena128.regionmanager.execptions.RegionDoesNotExistException;
import com.github.helena128.regionmanager.execptions.UniqueConstraintException;
import com.github.helena128.regionmanager.repository.RegionsMybatisMapper;
import io.swagger.model.RegionInput;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RegionsValidatorImpl implements RegionsValidator {

    RegionsMybatisMapper mybatisMapper;

    @Override
    public void validateRegionCreation(RegionInput regionInput) {
        // Here can happen also another validation - e.g. for only letters in string
        validateFieldUniqueness(regionInput);
    }

    @Override
    public void validateRegionExistence(Integer id) {
        if (isNull(mybatisMapper.findRegionEntityById(id.longValue()))) {
            throw new RegionDoesNotExistException(id);
        }
    }

    @Override
    public void validateRegionUpdate(Integer id, RegionInput regionInput) {
        validateRegionExistence(id);
        validateFieldUniqueness(regionInput);
    }

    private void validateFieldUniqueness(RegionInput regionInput) {
        checkRegionNameUnique(regionInput.getName());
        checkRegionShortNameValid(regionInput.getShortName());
    }

    private void checkRegionShortNameValid(final String shortName) {
        if (mybatisMapper.checkRegionWithShortNameExists(shortName)) {
            throw new UniqueConstraintException(FieldType.SHORT_NAME.toString());
        }
    }

    private void checkRegionNameUnique(final String name) {
        if (mybatisMapper.checkRegionWithNameExists(name)) {
            throw new UniqueConstraintException(FieldType.NAME.toString());
        }
    }

    enum FieldType {
        NAME("name"), SHORT_NAME("shortName");

        private final String fieldName;

        FieldType(String fieldName) {
            this.fieldName = fieldName;
        }

        @Override
        public String toString() {
            return fieldName;
        }
    }
}
