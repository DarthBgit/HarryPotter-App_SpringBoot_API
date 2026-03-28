package com.harrypotter.repositories;

import com.harrypotter.entities.MagicObjects;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IMagicObjects extends JpaRepository<MagicObjects,Integer> {

    @Query(value = "SELECT * FROM object order by name ASC", nativeQuery = true)
    public Page<MagicObjects> findAll(Pageable pageable);

    @Query(value = "SELECT * FROM object WHERE name = :name", nativeQuery = true)
    public MagicObjects findByName(String name);

    @Query(value = "SELECT * FROM object where is_horocruxe = 1", nativeQuery = true)
    public Page<MagicObjects> findHorocruxesList(Pageable pageable);

    @Query(value = "SELECT * FROM object where is_hollow = 1", nativeQuery = true)
    public Page<MagicObjects> findHollowList(Pageable pageable);

    @Query(value = "SELECT * FROM object where is_wander = 1", nativeQuery = true)
    public Page<MagicObjects> findWanderList(Pageable pageable);

    @Query(value = "SELECT * FROM object where is_other = 1", nativeQuery = true)
    public Page<MagicObjects> findOthersObjectsList(Pageable pageable);

    @Query(value = "SELECT * FROM object where is_quiddich = '1'", nativeQuery = true)
    public Page<MagicObjects> findQuddichObjectsList(Pageable pageable);
}
