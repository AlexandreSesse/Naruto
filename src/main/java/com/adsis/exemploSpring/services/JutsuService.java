package com.adsis.exemploSpring.services;

import com.adsis.exemploSpring.DTOs.JutsuDTO;
import com.adsis.exemploSpring.models.Jutsu;
import com.adsis.exemploSpring.repositories.JutsuRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JutsuService {

    private final JutsuRepository repository;

    @Autowired
    public JutsuService(JutsuRepository repository) {
        this.repository = repository;
    }

    public List<JutsuDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(JutsuDTO::new)
                .collect(Collectors.toList());
    }

    public JutsuDTO buscarPorId(Long id) {
        Jutsu jutsu = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Jutsu não encontrado"));
        return new JutsuDTO(jutsu);
    }

    public JutsuDTO salvar(Jutsu jutsu) {
        Jutsu salvo = repository.save(jutsu);
        return new JutsuDTO(salvo);
    }

    public JutsuDTO atualizar(Long id, Jutsu dadosAtualizados) {
        Jutsu jutsu = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Jutsu não encontrado"));

        jutsu.setNome(dadosAtualizados.getNome());
        jutsu.setDescricao(dadosAtualizados.getDescricao());
        jutsu.setDificuldade(dadosAtualizados.getDificuldade());

        return new JutsuDTO(repository.save(jutsu));
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Jutsu não encontrado");
        }
        repository.deleteById(id);
    }
}