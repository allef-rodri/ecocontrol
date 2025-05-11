package br.com.fiap.ecocontrol.service;

import br.com.fiap.ecocontrol.dto.SetorCadastroDto;
import br.com.fiap.ecocontrol.dto.SetorExibicaoDto;
import br.com.fiap.ecocontrol.model.Setor;
import br.com.fiap.ecocontrol.repository.SetorRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SetorService {
    @Autowired
    private SetorRepository setorRepository;

    public SetorService(SetorRepository setorRepository) {
        this.setorRepository = setorRepository;
    }

    public List<SetorExibicaoDto> listarTodos() {
        return setorRepository.findAll().stream()
                .map(SetorExibicaoDto::new)
                .collect(Collectors.toList());
    }

    public SetorExibicaoDto buscarPorId(Long id) {
        return setorRepository.findById(id)
                .map(SetorExibicaoDto::new)
                .orElseThrow(() -> new EntityNotFoundException("Setor não encontrado com o ID: " + id));
    }

    @Transactional
    public SetorExibicaoDto criar(SetorCadastroDto dto) {
        Setor setor = new Setor();
        setor.setDeSetor(dto.deSetor());
        setor.setLocalizacao(dto.localizacao());

        Setor setorSalvo = setorRepository.save(setor);
        return new SetorExibicaoDto(setorSalvo);
    }

    @Transactional
    public SetorExibicaoDto atualizar(Long id, SetorCadastroDto dto) {
        Setor setor = setorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Setor não encontrado com o ID: " + id));

        setor.setDeSetor(dto.deSetor());
        setor.setLocalizacao(dto.localizacao());

        Setor setorAtualizado = setorRepository.save(setor);
        return new SetorExibicaoDto(setorAtualizado);
    }

    @Transactional
    public void deletar(Long id) {
        if (!setorRepository.existsById(id)) {
            throw new EntityNotFoundException("Setor não encontrado com o ID: " + id);
        }
        setorRepository.deleteById(id);
    }
}
