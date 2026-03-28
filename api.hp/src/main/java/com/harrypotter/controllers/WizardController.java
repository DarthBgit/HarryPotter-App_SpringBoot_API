package com.harrypotter.controllers;

import java.util.List;

import com.harrypotter.dto.WizardResponseDto;
import com.harrypotter.services.WizardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/wizards")
public class WizardController {

    @Autowired
    private WizardService wizardService;

    @GetMapping("/pages/{page}")
    public Page<WizardResponseDto> findAllSorted(@PathVariable Integer page) {
        return wizardService.findAllSorted(PageRequest.of(page, 8));
    }

    @GetMapping("/id/{id}")
    public WizardResponseDto findById(@PathVariable Integer id) {
        return wizardService.findById(id);
    }

    @GetMapping("/{name}")
    public WizardResponseDto findByName(@PathVariable String name) {
        return wizardService.findByName(name);
    }

    @GetMapping("/search/{criteria}/{name}")
    public List<WizardResponseDto> searcherWizard(
            @PathVariable String criteria,
            @PathVariable String name){
        return switch (criteria) {
            case "searching" -> wizardService.findAllCoincidences(name);
            case "family" -> wizardService.findFamilyByName(name);
            default -> throw new IllegalArgumentException("Invalid criteria: " + criteria);
        };
    }

    @GetMapping("/{criteria}/{page}")
    public Page<WizardResponseDto> findByCriteria(
            @PathVariable String criteria,
            @PathVariable Integer page) {

        return switch (criteria) {
            case "mortifagos" -> wizardService.findMortifagosList(PageRequest.of(page, 8));
            case "animals" -> wizardService.findAnimalsFantasticList(PageRequest.of(page, 8));
            case "students" -> wizardService.findStudentsList(PageRequest.of(page, 8));
            case "teachers" -> wizardService.findTeachersList(PageRequest.of(page, 8));
            case "others" -> wizardService.findOthersList(PageRequest.of(page, 8));
            default -> throw new IllegalArgumentException("Invalid criteria: " + criteria);
        };
    }

    @PostMapping
    public WizardResponseDto createWizard(@Valid @RequestBody WizardResponseDto wizardDto) {
        return wizardService.createWizard(wizardDto);
    }

    @PutMapping("/id/{id}")
    public WizardResponseDto updateWizard(@PathVariable Integer id, @Valid @RequestBody WizardResponseDto wizardDto) {
        return wizardService.updateWizard(id, wizardDto);
    }

    @DeleteMapping("/id/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteWizard(@PathVariable Integer id) {
        wizardService.deleteWizard(id);
    }
}