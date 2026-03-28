package com.harrypotter.mappers;

import com.harrypotter.dto.CreatureTagResponseDto;
import com.harrypotter.entities.CreatureTag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CreatureTagMapper {

    @Mapping(target = "creatureId", source = "ct.id")
    CreatureTagResponseDto toDto(CreatureTag creatureTag);

    @Mapping(target = "ct", ignore = true)
    CreatureTag toEntity(CreatureTagResponseDto creatureTagDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "ct", ignore = true)
    void updateEntityFromDto(CreatureTagResponseDto creatureTagDto, @MappingTarget CreatureTag existingCreatureTag);
}

