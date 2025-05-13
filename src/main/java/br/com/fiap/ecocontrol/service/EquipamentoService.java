package br.com.fiap.ecocontrol.service;

import br.com.fiap.ecocontrol.dto.EquipamentoCadastroDto;
import br.com.fiap.ecocontrol.dto.EquipamentoExibicaoDto;
import br.com.fiap.ecocontrol.exception.equipamento.*;
import br.com.fiap.ecocontrol.model.Equipamento;
import br.com.fiap.ecocontrol.model.Setor;
import br.com.fiap.ecocontrol.repository.EquipamentoRepository;
import br.com.fiap.ecocontrol.repository.SetorRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EquipamentoService {
    @Autowired
    private EquipamentoRepository equipamentoRepository;

    @Autowired
    private SetorRepository setorRepository;

    public List<EquipamentoExibicaoDto> listarTodos() {
        try {
            return equipamentoRepository.findAll().stream()
                    .map(EquipamentoExibicaoDto::new)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new ErroListagemEquipamentoException("Erro ao listar equipamentos");
        }
    }

    public EquipamentoExibicaoDto buscarPorId(Long id) {
        try {
            return equipamentoRepository.findById(id)
                    .map(EquipamentoExibicaoDto::new)
                    .orElseThrow(() -> new EntityNotFoundException("Equipamento não encontrado com o ID: " + id));
        } catch (Exception e) {
            throw new EquipamentoNaoEncontradoException("Erro ao buscar equipamento com ID: " + id);
        }
    }

    @Transactional
    public EquipamentoExibicaoDto criar(EquipamentoCadastroDto dto) {
        try {
            Setor setor = setorRepository.findById(dto.idSetor())
                    .orElseThrow(() -> new EntityNotFoundException("Setor não encontrado com o ID: " + dto.idSetor()));

            Equipamento equipamento = new Equipamento();
            equipamento.setDeEquipamento(dto.deEquipamento());
            equipamento.setTipo(dto.tipo());
            equipamento.setConsumoMaximo(dto.consumoMaximo());
            equipamento.setStatus(dto.status());
            equipamento.setSetor(setor);

            return new EquipamentoExibicaoDto(equipamentoRepository.save(equipamento));
        } catch (Exception e) {
            throw new ErroCadastroEquipamentoException("Erro ao cadastrar equipamento");
        }
    }

    @Transactional
    public EquipamentoExibicaoDto atualizar(Long id, EquipamentoCadastroDto dto) {
        try {
            Equipamento equipamento = equipamentoRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Equipamento não encontrado com o ID: " + id));

            Setor setor = setorRepository.findById(dto.idSetor())
                    .orElseThrow(() -> new EntityNotFoundException("Setor não encontrado com o ID: " + dto.idSetor()));

            equipamento.setDeEquipamento(dto.deEquipamento());
            equipamento.setTipo(dto.tipo());
            equipamento.setConsumoMaximo(dto.consumoMaximo());
            equipamento.setStatus(dto.status());
            equipamento.setSetor(setor);

            return new EquipamentoExibicaoDto(equipamentoRepository.save(equipamento));
        } catch (Exception e) {
            throw new ErroAtualizacaoEquipamentoException("Erro ao atualizar equipamento com ID: " + id);
        }
    }

    @Transactional
    public void deletar(Long id) {
        try {
            if (!equipamentoRepository.existsById(id)) {
                throw new EntityNotFoundException("Equipamento não encontrado com o ID: " + id);
            }
            equipamentoRepository.deleteById(id);
        } catch (Exception e) {
            throw new ErroExclusaoEquipamentoException("Erro ao excluir equipamento com ID: " + id);
        }
    }

}
