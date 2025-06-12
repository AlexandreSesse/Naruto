package com.adsis.exemploSpring.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adsis.exemploSpring.models.Ninja;

@Repository
public interface NinjaRepository extends JpaRepository<Ninja, Long> {

    List<Ninja> findByVilaId(Long vilaId);

    List<Ninja> findByNomeContainingIgnoreCase(String nome);

    List<Ninja> findByIdadeGreaterThan(int idade);

}
