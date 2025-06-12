package com.adsis.exemploSpring.models;

import com.adsis.exemploSpring.DTOs.NinjaDTO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_ninjas")
public class Ninja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    private int idade;

    private String cla;

    @ManyToOne
    @JoinColumn(name = "missao_id")
    private Missao missao;

    @ManyToOne
    @JoinColumn(name = "vila_id")
    private Vila vila;

    @ManyToMany
    @JoinTable(
            name = "ninja_jutsu",
            joinColumns = @JoinColumn(name = "ninja_id"),
            inverseJoinColumns = @JoinColumn(name = "jutsu_id")
    )
    private Set<Jutsu> jutsus = new HashSet<>();

    public Ninja(NinjaDTO ninja) {
        this.id = ninja.id();
        this.idade = ninja.idade();
        this.nome = ninja.nome();
        this.cla = ninja.cla();
    }

}
