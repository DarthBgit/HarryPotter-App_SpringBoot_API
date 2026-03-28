package com.harrypotter.services;

import com.harrypotter.dto.WizardResponseDto;
import com.harrypotter.entities.MagicObjects;
import com.harrypotter.exceptions.ResourceNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

import com.harrypotter.entities.Wizard;
import com.harrypotter.mappers.WizardMapper;
import com.harrypotter.repositories.IMagicObjects;
import com.harrypotter.repositories.IWizardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class WizardService {

    @Autowired
    private IWizardRepository iWizardRepository;

    @Autowired
    private IMagicObjects iMagicObjects;

    @Autowired
    private WizardMapper wizardMapper;

    public Page<WizardResponseDto> findAllSorted(Pageable pageable) {
        return iWizardRepository.findAllSorted(pageable).map(wizardMapper::toDto);
    }

    public List<WizardResponseDto> findFamilyByName(String name) {
        return iWizardRepository.findFamilyByName(name)
                .stream()
                .map(wizardMapper::toDto)
                .collect(Collectors.toList());
    }

    public WizardResponseDto findByName(String name) {
        Wizard wizard = iWizardRepository.findByName(name);
        if (wizard == null) {
            throw new ResourceNotFoundException("Wizard not found with name: " + name);
        }
        return wizardMapper.toDto(wizard);
    }

    public WizardResponseDto findById(Integer id) {
        return iWizardRepository.findById(id)
                .map(wizardMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Wizard not found with id: " + id));
    }

    public List<WizardResponseDto> findAllCoincidences(String name) {
        return iWizardRepository.findAllCoincidences(name)
                .stream()
                .map(wizardMapper::toDto)
                .collect(Collectors.toList());
    }

    public Page<WizardResponseDto> findMortifagosList(Pageable pageable) {
        return iWizardRepository.findMortifagosList(pageable).map(wizardMapper::toDto);
    }

    public Page<WizardResponseDto> findAnimalsFantasticList(Pageable pageable) {
        return iWizardRepository.findAnimalsFantasticList(pageable).map(wizardMapper::toDto);
    }

    public Page<WizardResponseDto> findStudentsList(Pageable pageable) {
        return iWizardRepository.findStudentList(pageable).map(wizardMapper::toDto);
    }

    public Page<WizardResponseDto> findTeachersList(Pageable pageable) {
        return iWizardRepository.findTeachersList(pageable).map(wizardMapper::toDto);
    }

    public Page<WizardResponseDto> findOthersList(Pageable pageable) {
        return iWizardRepository.findOthersList(pageable).map(wizardMapper::toDto);
    }

    public WizardResponseDto createWizard(WizardResponseDto wizardDto) {
        Wizard wizard = wizardMapper.toEntity(wizardDto);
        wizard.setId(null);
        assignRelations(wizard, wizardDto);
        return wizardMapper.toDto(iWizardRepository.save(wizard));
    }

    public WizardResponseDto updateWizard(Integer id, WizardResponseDto wizardDto) {
        Wizard existingWizard = iWizardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Wizard not found with id: " + id));
        wizardMapper.updateEntityFromDto(wizardDto, existingWizard);
        assignRelations(existingWizard, wizardDto);
        return wizardMapper.toDto(iWizardRepository.save(existingWizard));
    }

    public void deleteWizard(Integer id) {
        if (!iWizardRepository.existsById(id)) {
            throw new ResourceNotFoundException("Wizard not found with id: " + id);
        }
        iWizardRepository.deleteById(id);
    }

    private void assignRelations(Wizard wizard, WizardResponseDto wizardDto) {
        wizard.setMw(findMagicObject(wizardDto.getWandId()));
        wizard.setMo(findMagicObject(wizardDto.getMagicObjectId()));
    }

    private MagicObjects findMagicObject(Integer magicObjectId) {
        if (magicObjectId == null) {
            return null;
        }

        return iMagicObjects.findById(magicObjectId)
                .orElseThrow(() -> new ResourceNotFoundException("Magic object not found with id: " + magicObjectId));
    }
}