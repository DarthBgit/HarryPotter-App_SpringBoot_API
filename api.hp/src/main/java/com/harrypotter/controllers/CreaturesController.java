package com.harrypotter.controllers;

import com.harrypotter.dto.CreatureResponseDto;
import com.harrypotter.services.CreaturesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/creatures")
public class CreaturesController {

    @Autowired
    private CreaturesService creaturesService;

    @GetMapping("/pages/{page}")
    public Page<CreatureResponseDto> findAll(@PathVariable Integer page){
        return creaturesService.findAll(PageRequest.of(page, 8));
    }

    @GetMapping("/id/{id}")
    public CreatureResponseDto findById(@PathVariable Integer id) {
        return creaturesService.findById(id);
    }

    @GetMapping("/{raze}")
    public CreatureResponseDto findByRaze(@PathVariable String raze){
        return creaturesService.findByRace(raze);
    }

    @GetMapping("/{criteria}/{page}")
    public Page<CreatureResponseDto> findCreatureByCategory(@PathVariable Integer page, @PathVariable String criteria) {
        return switch (criteria) {
            case "danger" -> creaturesService.findDangerCreaturesList(PageRequest.of(page, 8));
            case "nodanger" -> creaturesService.findNoDangerCreaturesList(PageRequest.of(page, 8));
            default -> throw new IllegalArgumentException("Invalid criteria: " + criteria);
        };
    }

    @PostMapping
    public CreatureResponseDto createCreature(@Valid @RequestBody CreatureResponseDto creatureDto) {
        return creaturesService.createCreature(creatureDto);
    }

    @PutMapping("/id/{id}")
    public CreatureResponseDto updateCreature(@PathVariable Integer id, @Valid @RequestBody CreatureResponseDto creatureDto) {
        return creaturesService.updateCreature(id, creatureDto);
    }

    @DeleteMapping("/id/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCreature(@PathVariable Integer id) {
        creaturesService.deleteCreature(id);
    }
}
