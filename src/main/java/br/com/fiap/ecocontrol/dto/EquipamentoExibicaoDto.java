package br.com.fiap.ecocontrol.dto;

import br.com.fiap.ecocontrol.model.Alerta;
import br.com.fiap.ecocontrol.model.Equipamento;
import br.com.fiap.ecocontrol.model.LeituraConsumo;
import br.com.fiap.ecocontrol.model.Setor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

public record EquipamentoExibicaoDto(
        Long idEquipamento,
        String deEquipamento,
        String tipo,
        Double consumoMaximo,
        String status,
        SetorExibicaoDto setor
) {

    public EquipamentoExibicaoDto(Equipamento equipamento) {
        this(
                equipamento.getIdEquipamento(),
                equipamento.getDeEquipamento(),
                equipamento.getTipo(),
                equipamento.getConsumoMaximo(),
                equipamento.getStatus(),
                new SetorExibicaoDto(equipamento.getSetor())
        );
    }
}