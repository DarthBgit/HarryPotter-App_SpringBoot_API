package com.harrypotter.services;

import com.harrypotter.dto.MagicObjectsResponseDto;
import com.harrypotter.entities.MagicObjects;
import com.harrypotter.exceptions.ResourceNotFoundException;
import com.harrypotter.mappers.MagicObjectsMapper;
import com.harrypotter.repositories.IMagicObjects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MagicObjectsService {

    @Autowired
    private IMagicObjects iMagicObjects;

    @Autowired
    private MagicObjectsMapper magicObjectsMapper;

    public Page<MagicObjectsResponseDto> findAllObjects(Pageable pageable){
        return iMagicObjects.findAll(pageable).map(magicObjectsMapper::toDto);
    }

    public MagicObjectsResponseDto findByName(String name) {
        MagicObjects magicObject = iMagicObjects.findByName(name);
        if (magicObject == null) {
            throw new ResourceNotFoundException("Magic object not found with name: " + name);
        }
        return magicObjectsMapper.toDto(magicObject);
    }

    public Page<MagicObjectsResponseDto> findHorocruxesList(Pageable pageable){
        return iMagicObjects.findHorocruxesList(pageable).map(magicObjectsMapper::toDto);
    }

    public Page<MagicObjectsResponseDto> findWandersList(Pageable pageable){
        return iMagicObjects.findWanderList(pageable).map(magicObjectsMapper::toDto);
    }

    public Page<MagicObjectsResponseDto> findHollows(Pageable pageable){
        return iMagicObjects.findHollowList(pageable).map(magicObjectsMapper::toDto);
    }

    public Page<MagicObjectsResponseDto> findOthersObjects(Pageable pageable){
        return iMagicObjects.findOthersObjectsList(pageable).map(magicObjectsMapper::toDto);
    }

    public Page<MagicObjectsResponseDto> findQuddichObjects(Pageable pageable){
        return iMagicObjects.findQuddichObjectsList(pageable).map(magicObjectsMapper::toDto);
    }

    public MagicObjectsResponseDto createMagicObject(MagicObjectsResponseDto magicObjectDto) {
        MagicObjects magicObject = magicObjectsMapper.toEntity(magicObjectDto);
        magicObject.setId(null);
        return magicObjectsMapper.toDto(iMagicObjects.save(magicObject));
    }

    public MagicObjectsResponseDto updateMagicObject(Integer id, MagicObjectsResponseDto magicObjectDto) {
        MagicObjects existingMagicObject = iMagicObjects.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Magic object not found with id: " + id));
        magicObjectsMapper.updateEntityFromDto(magicObjectDto, existingMagicObject);
        return magicObjectsMapper.toDto(iMagicObjects.save(existingMagicObject));
    }

}
