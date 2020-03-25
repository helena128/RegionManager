package com.github.helena128.regionmanager.api;

import com.github.helena128.regionmanager.execptions.RegionDoesNotExistException;
import com.github.helena128.regionmanager.execptions.UniqueConstraintException;
import io.swagger.model.ErrorModel;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RegionsApiExceptionHandler {

    @ExceptionHandler(RegionDoesNotExistException.class)
    public ResponseEntity<ErrorModel> handleRegionDoesNotExist(RegionDoesNotExistException ex) {
        val error = new ErrorModel().message(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UniqueConstraintException.class)
    public ResponseEntity<ErrorModel> handleUniqueConstraint(UniqueConstraintException ex) {
        val error = new ErrorModel().message(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
