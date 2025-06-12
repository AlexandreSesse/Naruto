package com.adsis.exemploSpring.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.adsis.exemploSpring.DTOs.MissaoDTO;
import com.adsis.exemploSpring.Exceptions.NaoEncontradoException;
import com.adsis.exemploSpring.models.Missao;
import com.adsis.exemploSpring.models.Ninja;
import com.adsis.exemploSpring.repositories.MissaoRepository;
import com.adsis.exemploSpring.repositories.NinjaRepository;

import jakarta.transaction.Transactional;

@Service
public class MissaoService {
    MissaoRepository missaoRepository;
    NinjaRepository ninjaRepository;

    public MissaoService(MissaoRepository missaoRepository, NinjaRepository ninjaRepository) {
        this.missaoRepository = missaoRepository;
        this.ninjaRepository = ninjaRepository;
    }

    public List<MissaoDTO> listar() {
        return missaoRepository.findAll().stream().map(missao -> new MissaoDTO(missao)).toList();
    }

    public MissaoDTO salvar(MissaoDTO missaoDTO) {
        Missao missaoEntity = new Missao(missaoDTO);
        if (missaoDTO.ninjasId() != null) {
            List<Ninja> ninjas = ninjaRepository.findAllById(missaoDTO.ninjasId()).stream()
                    .map(ninja -> {
                        ninja.setMissao(missaoEntity);
                        return ninja;
                    })
                    .toList();
            missaoEntity.setNinjas(ninjas);
        }

        return new MissaoDTO(missaoRepository.save(missaoEntity));
    }

    public MissaoDTO salvar(Long id, MissaoDTO missaoDTO) {
        missaoRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("Missao com id " + id + " nao encontrada"));

        Missao missaoEntity = new Missao(missaoDTO);
        missaoEntity.setId(id);

        if (missaoDTO.ninjasId() != null) {
            List<Ninja> ninjas = ninjaRepository.findAllById(missaoDTO.ninjasId()).stream()
                    .map(ninja -> {
                        ninja.setMissao(missaoEntity);
                        return ninja;
                    })
                    .toList();
            missaoEntity.setNinjas(ninjas);
        }

        return new MissaoDTO(missaoRepository.save(missaoEntity));
    }

    public MissaoDTO buscarPorId(Long id) {
        return missaoRepository.findById(id).map(missao -> new MissaoDTO(missao))
                .orElseThrow(() -> new NaoEncontradoException("Missao com id <" + id + "> não encontrado!"));
    }

    @Transactional
    public void deletar(Long id) {
        missaoRepository
                .findById(id)
                .orElseThrow(
                        () -> new NaoEncontradoException("Missao com id <" + id + "> não encontrado!"))
                .getNinjas()
                .stream()
                .forEach(ninja -> {
                    ninja.setMissao(null);
                    ninjaRepository.save(ninja);
                });

        missaoRepository.deleteById(id);

    }
}
