package com.harrypotter.mappers;

import com.harrypotter.dto.CreatureResponseDto;
import com.harrypotter.entities.Creature;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = CreatureTagMapper.class)
public interface CreatureMapper {

    CreatureResponseDto toDto(Creature creature);

    @Mapping(target = "tags", ignore = true)
    Creature toEntity(CreatureResponseDto creatureDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tags", ignore = true)
    void updateEntityFromDto(CreatureResponseDto creatureDto, @MappingTarget Creature existingCreature);
}

