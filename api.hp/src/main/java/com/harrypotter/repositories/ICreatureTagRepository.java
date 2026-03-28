package com.harrypotter.repositories;

import com.harrypotter.entities.CreatureTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICreatureTagRepository extends JpaRepository<CreatureTag, Integer> {
    void deleteByCtId(Integer creatureId);
}

