package com.adsis.exemploSpring.DTOs;

import com.adsis.exemploSpring.models.Ninja;
import com.adsis.exemploSpring.models.Vila;

import java.util.List;

public record VilaDTO(
        Long id,
        String nome,
        int numeroHabitantes,
        List<Long> ninjas
) {
    public VilaDTO(Vila vila) {
        this(
                vila.getId(),
                vila.getNome(),
                vila.getNumeroHabitantes(),
                vila.getNinjas() != null
                        ? vila.getNinjas().stream().map(Ninja::getId).toList()
                        : List.of()
        );
    }
}