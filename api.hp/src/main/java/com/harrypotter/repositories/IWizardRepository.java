
package com.harrypotter.repositories;

import com.harrypotter.entities.Wizard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface IWizardRepository extends JpaRepository<Wizard,Integer>{

    @Query(value = "SELECT * FROM wizard order by name ASC", nativeQuery = true)
    public Page<Wizard> findAllSorted(Pageable pageable);

    @Query(value = "SELECT * FROM wizard WHERE name = :name", nativeQuery = true)
    public Wizard findByName(String name);

    @Query(value = "SELECT * FROM wizard WHERE name LIKE %:name%", nativeQuery = true)
    public List<Wizard> findAllCoincidences(String name);

    @Query(value = "SELECT * FROM wizard WHERE familyname = :name", nativeQuery = true)
    public List<Wizard> findFamilyByName(String name);

    @Query(value = "SELECT * FROM wizard WHERE mortifago = 1 order by name ASC ", nativeQuery = true)
    public Page<Wizard> findMortifagosList(Pageable pageable);

    @Query(value = "SELECT * FROM wizard WHERE animals_fantastics = 1", nativeQuery = true)
    public Page<Wizard> findAnimalsFantasticList(Pageable pageable);

    @Query(value = "SELECT * FROM wizard WHERE student = 1 order by name ASC", nativeQuery = true)
    public Page<Wizard> findStudentList(Pageable pageable);

    @Query(value = "SELECT * FROM wizard WHERE teacher = 1", nativeQuery = true)
    public Page<Wizard> findTeachersList(Pageable pageable);

    @Query(value = "SELECT * FROM wizard WHERE others = 1 order by name ASC", nativeQuery = true)
    public Page<Wizard> findOthersList(Pageable pageable);
    
}