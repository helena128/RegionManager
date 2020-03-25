package com.github.helena128.regionmanager.repository;

import com.github.helena128.regionmanager.repository.model.RegionEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface RegionsMybatisMapper {

    @Insert("INSERT into regions (id, name, short_name) VALUES (#{id}, #{name}, #{shortName})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    RegionEntity addRegionEntity(RegionEntity regionEntity);
}
