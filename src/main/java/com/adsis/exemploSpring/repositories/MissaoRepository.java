package com.adsis.exemploSpring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adsis.exemploSpring.models.Missao;

@Repository
public interface MissaoRepository extends JpaRepository<Missao, Long> {

}
