package com.harrypotter.mappers;

import com.harrypotter.dto.MagicObjectsResponseDto;
import com.harrypotter.entities.MagicObjects;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MagicObjectsMapper {
    MagicObjectsResponseDto toDto(MagicObjects magicObject);

    @Mapping(target = "ownerWander", ignore = true)
    @Mapping(target = "ownerMagicObject", ignore = true)
    MagicObjects toEntity(MagicObjectsResponseDto magicObjectDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "ownerWander", ignore = true)
    @Mapping(target = "ownerMagicObject", ignore = true)
    void updateEntityFromDto(MagicObjectsResponseDto magicObjectDto, @MappingTarget MagicObjects existingMagicObject);
}

