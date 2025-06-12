package com.adsis.exemploSpring.repositories;

import com.adsis.exemploSpring.models.Vila;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VilaRepository extends JpaRepository<Vila, Long> {
    List<Vila> findByNome(String nome);
    List<Vila> findByNomeContainingIgnoreCase(String nome);
}