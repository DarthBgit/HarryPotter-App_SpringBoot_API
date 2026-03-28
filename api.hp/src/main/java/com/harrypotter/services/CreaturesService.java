package com.harrypotter.services;

import com.harrypotter.dto.CreatureResponseDto;
import com.harrypotter.entities.Creature;
import com.harrypotter.exceptions.ResourceNotFoundException;
import com.harrypotter.mappers.CreatureMapper;
import com.harrypotter.repositories.ICreatureTagRepository;
import com.harrypotter.repositories.ICreaturesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CreaturesService {

    @Autowired
    private ICreaturesRepository iCreaturesRepository;

    @Autowired
    private ICreatureTagRepository iCreatureTagRepository;

    @Autowired
    private CreatureMapper creatureMapper;

    /*TODO remove???*/
    public Page<CreatureResponseDto> findAll(Pageable pageable){
        return iCreaturesRepository.findAll(pageable).map(creatureMapper::toDto);
    }

    public Page<CreatureResponseDto> findDangerCreaturesList(Pageable pageable){
        return iCreaturesRepository.findDangerCreaturesList(pageable).map(creatureMapper::toDto);
    }

    public Page<CreatureResponseDto> findNoDangerCreaturesList(Pageable pageable){
        return iCreaturesRepository.findNoDangerCreaturesList(pageable).map(creatureMapper::toDto);
    }

    public CreatureResponseDto findByRace(String raze){
        Creature creature = iCreaturesRepository.findByRaze(raze);
        if (creature == null) {
            throw new ResourceNotFoundException("Creature not found with raze: " + raze);
        }
        return creatureMapper.toDto(creature);
    }

    public CreatureResponseDto findById(Integer id) {
        return iCreaturesRepository.findById(id)
                .map(creatureMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Creature not found with id: " + id));
    }

    public CreatureResponseDto createCreature(CreatureResponseDto creatureDto) {
        Creature creature = creatureMapper.toEntity(creatureDto);
        creature.setId(null);
        return creatureMapper.toDto(iCreaturesRepository.save(creature));
    }

    public CreatureResponseDto updateCreature(Integer id, CreatureResponseDto creatureDto) {
        Creature existingCreature = iCreaturesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Creature not found with id: " + id));
        creatureMapper.updateEntityFromDto(creatureDto, existingCreature);
        return creatureMapper.toDto(iCreaturesRepository.save(existingCreature));
    }

    public void deleteCreature(Integer id) {
        if (!iCreaturesRepository.existsById(id)) {
            throw new ResourceNotFoundException("Creature not found with id: " + id);
        }

        iCreatureTagRepository.deleteByCtId(id);
        iCreaturesRepository.deleteById(id);
    }
}
