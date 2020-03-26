package com.github.helena128.regionmanager.repository.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@EqualsAndHashCode
@ToString
public class RegionEntity implements Serializable {

    private static final long serialVersionUID = -6673532093586278058L;

    private Long id;
    private String name;
    private String shortName;
}
