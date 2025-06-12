package com.adsis.exemploSpring.repositories;

import com.adsis.exemploSpring.models.Jutsu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JutsuRepository extends JpaRepository<Jutsu, Long> {
    List<Jutsu> findByNomeContainingIgnoreCase(String nome);
    List<Jutsu> findByDificuldadeGreaterThan(int dificuldade);
}
