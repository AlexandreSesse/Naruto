package com.adsis.exemploSpring.DTOs;

import com.adsis.exemploSpring.models.Jutsu;
import com.adsis.exemploSpring.models.Ninja;

import java.util.Set;

public record JutsuDTO(
        Long id,
        String nome,
        String descricao,
        int dificuldade,
        Set<Ninja> ninjas
) {
    public JutsuDTO(Jutsu jutsu){
        this(
                jutsu.getId(),
                jutsu.getNome(),
                jutsu.getDescricao(),
                jutsu.getDificuldade(),
                jutsu.getNinjas()
        );
    }
}

