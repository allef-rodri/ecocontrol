package br.com.fiap.ecocontrol.service;

import br.com.fiap.ecocontrol.dto.SetorCadastroDto;
import br.com.fiap.ecocontrol.dto.SetorExibicaoDto;
import br.com.fiap.ecocontrol.exception.setor.ErroAtualizarSetorException;
import br.com.fiap.ecocontrol.exception.setor.ErroCriarSetorException;
import br.com.fiap.ecocontrol.exception.setor.ErroExcluirSetorException;
import br.com.fiap.ecocontrol.exception.setor.SetorNaoEncontradoException;
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
        try {
            return setorRepository.findAll().stream()
                    .map(SetorExibicaoDto::new)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new SetorNaoEncontradoException("Erro ao listar setores, erro: " + e);
        }
    }

    public SetorExibicaoDto buscarPorId(Long id) {
        try {
            return setorRepository.findById(id)
                    .map(SetorExibicaoDto::new)
                    .orElseThrow(() -> new EntityNotFoundException("Setor não encontrado com o ID: " + id));
        } catch (Exception e) {
            throw new SetorNaoEncontradoException("Erro ao buscar setor por ID, erro:" + e);
        }
    }

    @Transactional
    public SetorExibicaoDto criar(SetorCadastroDto dto) {
        try {
            Setor setor = new Setor();
            setor.setDeSetor(dto.deSetor());
            setor.setLocalizacao(dto.localizacao());

            Setor setorSalvo = setorRepository.save(setor);
            return new SetorExibicaoDto(setorSalvo);
        } catch (Exception e) {
            throw new ErroCriarSetorException("Erro ao cadastrar setor, erro: " + e);
        }
    }

    @Transactional
    public SetorExibicaoDto atualizar(Long id, SetorCadastroDto dto) {
        try {
            Setor setor = setorRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Setor não encontrado com o ID: " + id));

            setor.setDeSetor(dto.deSetor());
            setor.setLocalizacao(dto.localizacao());

            Setor setorAtualizado = setorRepository.save(setor);
            return new SetorExibicaoDto(setorAtualizado);
        } catch (Exception e) {
            throw new ErroAtualizarSetorException("Erro ao atualizar setor, erro: " + e);
        }
    }

    @Transactional
    public void deletar(Long id) {
        try {
            if (!setorRepository.existsById(id)) {
                throw new EntityNotFoundException("Setor não encontrado com o ID: " + id);
            }
            setorRepository.deleteById(id);
        } catch (Exception e) {
            throw new ErroExcluirSetorException("Erro ao deletar setor, erro: " + e);
        }
    }
}
