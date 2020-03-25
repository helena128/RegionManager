package com.github.helena128.regionmanager.repository;

import com.github.helena128.regionmanager.repository.model.RegionEntity;
import org.apache.ibatis.annotations.*;

@Mapper
public interface RegionsMybatisMapper {

    @Insert("INSERT into regions (id, name, short_name) VALUES (#{id}, #{name}, #{shortName})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    Long addRegionEntity(RegionEntity regionEntity);

    @Select("SELECT id as id, name as name, short_name as shortName FROM regions WHERE id=#{id}")
    RegionEntity findRegionEntityById(@Param("id") Long id);

    @Select("SELECT EXISTS (SELECT 1 FROM regions WHERE name=#{name})")
    boolean checkRegionWithNameExists(@Param("name") String name);

    @Select("SELECT EXISTS (SELECT 1 FROM regions WHERE short_name=#{shortName})")
    boolean checkRegionWithShortNameExists(@Param("shortName") String shortName);
}
