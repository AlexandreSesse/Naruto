package com.adsis.exemploSpring.services;

import com.adsis.exemploSpring.DTOs.NinjaDTO;
import com.adsis.exemploSpring.Exceptions.NaoEncontradoException;
import com.adsis.exemploSpring.models.Ninja;
import com.adsis.exemploSpring.repositories.MissaoRepository;
import com.adsis.exemploSpring.repositories.NinjaRepository;
import com.adsis.exemploSpring.repositories.VilaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NinjaService {

    private final NinjaRepository ninjaRepository;
    private final MissaoRepository missaoRepository;
    private final VilaRepository vilaRepository; // adiciona

    public NinjaService(NinjaRepository ninjaRepository, MissaoRepository missaoRepository, VilaRepository vilaRepository) {
        this.ninjaRepository = ninjaRepository;
        this.missaoRepository = missaoRepository;
        this.vilaRepository = vilaRepository;
    }

    public NinjaDTO salvar(NinjaDTO ninjaDTO) {
        Ninja ninja = new Ninja(ninjaDTO);
        ninja.setMissao(missaoRepository.findById(ninjaDTO.missaoId()).orElse(null));
        ninja.setVila(vilaRepository.findById(ninjaDTO.vilaId()).orElse(null));

        return new NinjaDTO(ninjaRepository.save(ninja));
    }

    public NinjaDTO salvar(Long id, NinjaDTO ninjaDTO) {
        Ninja ninjaExistente = ninjaRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("Ninja não foi encontrado!"));

        ninjaExistente.setNome(ninjaDTO.nome());
        ninjaExistente.setIdade(ninjaDTO.idade());
        ninjaExistente.setCla(ninjaDTO.cla());

        ninjaExistente.setMissao(missaoRepository.findById(ninjaDTO.missaoId()).orElse(null));
        ninjaExistente.setVila(vilaRepository.findById(ninjaDTO.vilaId()).orElse(null));

        return new NinjaDTO(ninjaRepository.save(ninjaExistente));
    }

    public List<NinjaDTO> listar() {
        return ninjaRepository.findAll().stream()
                .map(NinjaDTO::new)
                .toList();
    }

    public List<NinjaDTO> listarPorNome(String nome) {
        return ninjaRepository.findByNomeContainingIgnoreCase(nome).stream()
                .map(NinjaDTO::new)
                .toList();
    }

    public NinjaDTO buscarPorId(Long id) {
        return ninjaRepository.findById(id)
                .map(NinjaDTO::new)
                .orElseThrow(() -> new NaoEncontradoException("Ninja não foi encontrado!"));
    }

    public void deletar(Long id) {
        ninjaRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("Ninja não foi encontrado!"));
        ninjaRepository.deleteById(id);
    }
}