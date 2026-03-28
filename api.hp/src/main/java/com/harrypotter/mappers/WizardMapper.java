package com.harrypotter.mappers;

import com.harrypotter.dto.WizardResponseDto;
import com.harrypotter.entities.Wizard;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface WizardMapper {

	@Mapping(target = "wandId", source = "mw.id")
	@Mapping(target = "magicObjectId", source = "mo.id")
	WizardResponseDto toDto(Wizard wizard);

	@Mapping(target = "mw", ignore = true)
	@Mapping(target = "mo", ignore = true)
	Wizard toEntity(WizardResponseDto wizardDto);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "mw", ignore = true)
	@Mapping(target = "mo", ignore = true)
	void updateEntityFromDto(WizardResponseDto wizardDto, @MappingTarget Wizard existingWizard);

}
