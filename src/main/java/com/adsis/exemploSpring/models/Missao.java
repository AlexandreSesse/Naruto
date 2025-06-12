package com.adsis.exemploSpring.models;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.adsis.exemploSpring.DTOs.MissaoDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_missoes")
public class Missao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String nome;
    String dificuldade;
    String descricao;

    @OneToMany(mappedBy = "missao", cascade = CascadeType.PERSIST)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    List<Ninja> ninjas = new ArrayList<>();

    public Missao(MissaoDTO missao) {
        this.id = missao.id();
        this.nome = missao.nome();
        this.descricao = missao.descricao();
        this.dificuldade = missao.dificuldade();
    }

}
