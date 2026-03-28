package com.harrypotter.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreatureTagResponseDto {
    private Integer id;
    private String name;
    private String resume;
    private String phrase;
    private String life;
    private String picture;
    private Integer creatureId;
}

