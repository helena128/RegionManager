package com.github.helena128.regionmanager.repository;

import com.github.helena128.regionmanager.repository.model.RegionEntity;
import io.swagger.model.RegionInput;
import org.apache.ibatis.annotations.*;

import java.util.List;

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

    @Select("SELECT id as id, name as name, short_name as shortName FROM regions")
    List<RegionEntity> findAllRegions();

    @Update("UPDATE regions SET name=#{input.name}, short_name=#{input.shortName} WHERE id=#{id}")
    void updateRegionById(@Param("id") Long id, @Param("input") RegionInput input);

    @Delete("DELETE FROM regions WHERE id=#{id}")
    void removeById(Long id);

    @Delete("DELETE FROM regions")
    void removeAllRegions();
}
