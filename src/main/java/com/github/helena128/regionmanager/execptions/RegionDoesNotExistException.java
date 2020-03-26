package com.github.helena128.regionmanager.execptions;

import static java.lang.String.format;

public class RegionDoesNotExistException extends RuntimeException {

    private static final String MESSAGE = "Region with id %d does not exist";

    public RegionDoesNotExistException(Integer id) {
        super(format(MESSAGE, id));
    }
}
