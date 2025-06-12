package com.adsis.exemploSpring.models;

import com.adsis.exemploSpring.DTOs.VilaDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "tb_vila")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vila {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private int numeroHabitantes;

    @OneToMany(mappedBy = "vila")
    private List<Ninja> ninjas;

    public Vila(VilaDTO vila){
        this.id = vila.id();
        this.nome = vila.nome();
        this.numeroHabitantes = vila.numeroHabitantes();
    }
}